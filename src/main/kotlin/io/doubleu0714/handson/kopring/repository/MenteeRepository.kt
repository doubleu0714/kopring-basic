package io.doubleu0714.handson.kopring.repository

import io.doubleu0714.handson.kopring.repository.entity.Mentee
import org.springframework.data.repository.CrudRepository

interface MenteeRepository : CrudRepository<Mentee, Long>