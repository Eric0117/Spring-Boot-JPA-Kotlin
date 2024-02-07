package com.eric.jpa.domain

import com.eric.jpa.domain.base.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "company")
class Company(
    name: String,
): BaseEntity() {

    var name: String = name
        protected set

    // Relations
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "company")
    protected val mutableEmployees: MutableList<Person> = mutableListOf()
    val employees: List<Person> get() = mutableEmployees.toList()
}