package com.eric.jpa.domain

import com.eric.jpa.Gender
import com.eric.jpa.domain.base.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "person")
class Person(
    name: String,
    gender: Gender,
    company: Company? = null,
): BaseEntity() {

    var name: String = name
        protected set

    @Enumerated(EnumType.STRING)
    var gender: Gender = gender
        protected set

    fun buyCar(car: Car) {
        mutableCars.add(car)
    }

    fun sellCar(carId: UUID) {
        mutableCars.removeIf { it.id == carId }
    }

    // Relations
    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    var profile: Profile? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "person")
    protected val mutableCars: MutableList<Car> = mutableListOf()
    val cars: List<Car> get() = mutableCars.toList()

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    var company: Company? = company
        protected set
}