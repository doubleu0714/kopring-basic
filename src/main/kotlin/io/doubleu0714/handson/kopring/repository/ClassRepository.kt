package io.doubleu0714.handson.kopring.repository

import io.doubleu0714.handson.kopring.repository.entity.Class
import org.springframework.data.repository.CrudRepository

interface ClassRepository : CrudRepository<Class, Long>