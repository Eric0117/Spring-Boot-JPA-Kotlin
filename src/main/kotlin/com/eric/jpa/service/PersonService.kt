package com.eric.jpa.service

import com.eric.jpa.Gender
import com.eric.jpa.domain.Person
import com.eric.jpa.repository.PersonRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PersonService(
    private val personRepository: PersonRepository,
) {
    @Transactional
    fun create(): Person {
        val mem = Person(
            name = "Bennie Evans",
            gender = Gender.MALE,
            company = null
        )
        return personRepository.save(mem)
    }
}