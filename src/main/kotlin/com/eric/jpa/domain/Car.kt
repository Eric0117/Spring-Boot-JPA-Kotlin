package com.eric.jpa.domain

import com.eric.jpa.domain.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "car")
class Car(
    type: String,
    color: String,
    price: Long,
    person: Person,
): BaseEntity() {

    var type: String = type
        protected set

    var color: String = color
        protected set

    var price: Long = price
        protected set


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var person: Person = person
        protected set
}