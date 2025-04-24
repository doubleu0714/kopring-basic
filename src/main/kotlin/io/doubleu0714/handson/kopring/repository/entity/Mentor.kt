package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Mentor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @OneToMany(mappedBy = "mentor", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val classes: MutableList<Class>,
) {

    override fun toString(): String {
        return "Mentor(id=$id, name='$name', classes=$classes)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Mentor) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}