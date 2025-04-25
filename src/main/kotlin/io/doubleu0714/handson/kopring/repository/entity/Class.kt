package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
class Class private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    val mentor: Mentor,
    @OneToMany(mappedBy = "cls", fetch = FetchType.LAZY)
    val classRooms: MutableList<ClassRoom>,
) {
    var name: String = name
        private set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Class) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Class(id=$id, name='$name', mentor.id=${mentor.id} )"
    }

    companion object {
        operator fun invoke(name: String, mentor: Mentor): Class = Class(
            name = name,
            mentor = mentor,
            classRooms = mutableListOf(),
        )
    }
}