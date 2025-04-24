package io.doubleu0714.handson.kopring.repository.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany

@Entity
data class Class(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    @ManyToOne
    @JoinColumn(name = "mentor_id")
    val mentor: Mentor,
    @OneToMany(mappedBy = "cls")
    val mentees: MutableList<Mentee>,
)