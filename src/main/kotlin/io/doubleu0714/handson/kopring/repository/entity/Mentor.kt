package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Mentor private constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    name: String,
    @OneToMany(mappedBy = "mentor", cascade = [CascadeType.MERGE], fetch = FetchType.LAZY)
    val classes: MutableList<Class>,
) {
    var name: String = name
        private set

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

    fun add(command: CreateClassCommand) {
        classes.add(
            Class(
                name = command.name,
                mentor = this,
            )
        )
    }

    fun modify(command: ModifyCommand) {
        this.name = command.name
    }

    data class ModifyClassCommand(val classId: Long, val name: String)

    data class ModifyCommand(val name: String)

    data class CreateClassCommand(val name: String)

    companion object {
        operator fun invoke(name: String): Mentor = Mentor(
            name = name,
            classes = mutableListOf(),
        )
    }
}