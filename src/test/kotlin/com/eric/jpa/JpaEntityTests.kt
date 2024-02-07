package com.eric.jpa

import com.eric.jpa.domain.Car
import com.eric.jpa.domain.Person
import com.eric.jpa.repository.CarRepository
import com.eric.jpa.repository.CompanyRepository
import com.eric.jpa.repository.PersonRepository
import com.eric.jpa.repository.ProfileRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional
import java.util.*
import org.assertj.core.api.Assertions.assertThat

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
@Rollback(value = false)
class JpaEntityTests {
    @Autowired
    private lateinit var personRepository: PersonRepository

    @Autowired
    private lateinit var carRepository: CarRepository

    @Autowired
    private lateinit var companyRepository: CompanyRepository

    @Autowired
    private lateinit var profileRepository: ProfileRepository

    private var uuid: UUID? = null

    @BeforeEach
    fun beforeEach() {
        val people = Person(name = "Bennie Evans", gender = Gender.MALE, company = null)

        val res = personRepository.save(people)
        uuid = res.id
        println("INSERT FINISH")
    }

    @Test
    fun find_person() {
        val peoples = personRepository.findAll()

        Assertions.assertEquals(peoples.size, 1)
    }

    @Test
    fun add_car_to_person() {
        val person = personRepository.findById(uuid!!).get()
        val newCar = Car(
            type = "SUV", color = "blue", price = 5694, person = person
        )
        person.buyCar(newCar)

        val newPerson = personRepository.saveAndFlush(person)

        assertThat(newPerson.cars)
            .isNotEmpty()
            .first()
            .hasFieldOrPropertyWithValue("type", "SUV")
    }
}