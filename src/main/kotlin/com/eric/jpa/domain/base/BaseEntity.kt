package com.eric.jpa.domain.base

import com.fasterxml.uuid.Generators
import jakarta.persistence.*
import org.hibernate.proxy.HibernateProxy
import org.springframework.data.domain.Persistable
import java.io.Serializable
import java.util.*

@MappedSuperclass
abstract class BaseEntity : Persistable<UUID> {
    @Id
    @Column(columnDefinition = "uuid")
    private val id: UUID = Generators.timeBasedGenerator().generate()

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