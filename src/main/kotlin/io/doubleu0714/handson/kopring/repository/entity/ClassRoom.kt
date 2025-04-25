package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class ClassRoom private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    val cls: Class,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    val mentee: Mentee,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClassRoom) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    companion object {
        operator fun invoke(cls: Class, mentee: Mentee): ClassRoom = ClassRoom(
            cls = cls,
            mentee = mentee,
        )
    }
}