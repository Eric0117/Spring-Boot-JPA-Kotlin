package com.eric.jpa.domain

import com.eric.jpa.domain.base.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "profile")
class Profile(
    nickname: String
): BaseEntity() {

    var nickname: String = nickname
        protected set

    // Relations
    @OneToOne(mappedBy = "profile", fetch = FetchType.LAZY)
    var owner: Person? = null
}