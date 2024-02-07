package com.eric.jpa.domain.base

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.Persistable
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = Generators.timeBasedGenerator().generate()

    @CreatedDate
    @Column(nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @LastModifiedDate
    @Column(nullable = false)
    var modifiedAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Transient
    private var _isNew = true

    @PostPersist
    @PostLoad
    protected fun load() {
        _isNew = false
    }

    override fun getId(): UUID = id

    override fun isNew(): Boolean = _isNew

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Serializable {
        return if (obj is HibernateProxy) {
            (obj.hibernateLazyInitializer.implementation as BaseEntity).id
        } else {
            (obj as BaseEntity).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)
}