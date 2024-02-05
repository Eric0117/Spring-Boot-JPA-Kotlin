package com.eric.jpa.domain

import jakarta.persistence.*

@Entity
@Table(name = "member_table")
class Member(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val uid: Long? = null,

    val username: String,
)