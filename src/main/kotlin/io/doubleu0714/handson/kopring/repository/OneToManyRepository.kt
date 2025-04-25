package io.doubleu0714.handson.kopring.repository

import io.doubleu0714.handson.kopring.repository.entity.TestA
import io.doubleu0714.handson.kopring.repository.entity.TestB
import org.springframework.data.repository.CrudRepository

interface TestARepository : CrudRepository<TestA, Long>
interface TestBRepository : CrudRepository<TestB, Long>
