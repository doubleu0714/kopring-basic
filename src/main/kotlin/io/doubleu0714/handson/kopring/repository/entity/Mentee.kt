package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Mentee private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    @OneToMany(mappedBy = "mentee", fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST])
    val classRooms: MutableList<ClassRoom>,
) {
    var name: String = name
        private set

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Mentee) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

    override fun toString(): String {
        return "Mentee(id=$id, name='$name')"
    }

    companion object {
        operator fun invoke(name: String): Mentee = Mentee(
            name = name,
            classRooms = mutableListOf(),
        )
    }
}