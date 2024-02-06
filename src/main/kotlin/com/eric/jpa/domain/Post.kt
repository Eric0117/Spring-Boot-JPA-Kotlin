package com.eric.jpa.domain

import com.eric.jpa.domain.base.BaseEntity
import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "post")
class Post (
    title: String,
    content: String,
    writer: Member,
): BaseEntity() {
    @Column(nullable = false)
    var title: String = title
        protected set

    @Column(nullable = false, columnDefinition = "TEXT")
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var writer: Member = writer
        protected set
}