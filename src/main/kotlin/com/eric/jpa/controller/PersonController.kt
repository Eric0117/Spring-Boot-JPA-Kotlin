package com.eric.jpa.controller

import com.eric.jpa.domain.Person
import com.eric.jpa.service.PersonService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService,
) {
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @PostMapping
    fun getPerson(): Person {
        return personService.create()
    }

}