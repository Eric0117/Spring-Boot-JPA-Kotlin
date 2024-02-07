package com.eric.jpa.repository

import com.eric.jpa.domain.Person
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PersonRepository: JpaRepository<Person, UUID>