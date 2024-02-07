package com.eric.jpa

import com.eric.jpa.domain.Person
import com.eric.jpa.domain.QPerson
import com.eric.jpa.repository.PersonRepository
import com.querydsl.jpa.impl.JPAQuery
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(SpringExtension::class)
@Transactional
@SpringBootTest
class QueryDslTest {

    @Autowired
    private lateinit var personRepository: PersonRepository

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    //https://beaniejoy.tistory.com/84

    @BeforeEach
    fun beforeEach() {
        val people = listOf(
            Person(name = "Bennie Evans", gender = Gender.MALE, company = null),
            Person(name = "Edwardo Goff", gender = Gender.MALE, company = null),
            Person(name = "Lynn Evans", gender = Gender.MALE, company = null)
        )

        personRepository.saveAll(people)
    }

    @Test
    fun find_test() {
        val query: JPAQuery<Person> = JPAQuery<Person>(entityManager)
        val qPerson = QPerson("m")

        val personList: List<Person> = query.from(qPerson)
            .where(qPerson.name.like("%Evans%"))
            .fetch()

        assertEquals(personList.size, 2)
    }
}