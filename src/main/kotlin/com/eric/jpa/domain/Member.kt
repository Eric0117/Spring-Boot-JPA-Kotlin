package com.eric.jpa.domain

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "member")
class Member(

    @Id
    val uid: UUID = Generators.timeBasedGenerator().generate(),


    val username: String,
)