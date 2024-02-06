package com.eric.jpa.domain

import com.eric.jpa.domain.base.BaseEntity
import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "member")
class Member(
    username: String
): BaseEntity() {

    var username: String = username
        protected set
}