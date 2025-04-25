package io.doubleu0714.handson.kopring.repository

import io.doubleu0714.handson.kopring.repository.entity.TestA
import io.doubleu0714.handson.kopring.repository.entity.TestAChild
import io.doubleu0714.handson.kopring.repository.entity.TestB
import io.doubleu0714.handson.kopring.repository.entity.TestBChild
import jakarta.persistence.EntityManager
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class OneToManyRepositoryTest @Autowired constructor(
    private val testARepository: TestARepository,
    private val testBRepository: TestBRepository,
    private val entityManager: EntityManager,
) {
    @Test
    fun `TestA에 children 10개를 추가하고 조회한다`() {
        // given
        val testA = TestA(
            name = "testA",
            testAChildren = mutableListOf()
        )
        repeat(10) { i ->
            testA.testAChildren.add(
                TestAChild(
                    name = "child_$i"
                )
            )
        }

        // when
        var savedTestA = testARepository.save(testA)
        entityManager.flush()
        entityManager.clear()
        savedTestA = testARepository.findByIdOrNull(savedTestA.id!!)
            ?: error("TestA not found")
        savedTestA.testAChildren.add(
            TestAChild(
                name = "child_1000"
            )
        )
        testARepository.save(savedTestA)
        entityManager.flush()
        entityManager.clear()
    }

    @Test
    fun `TestB에 children 10개를 추가하고 조회한다`() {
        // given
        val testB = TestB(
            name = "testB",
            testBChildren = mutableListOf()
        )
        repeat(10) { i ->
            testB.testBChildren.add(
                TestBChild(
                    name = "child_$i",
                    testB = testB
                )
            )
        }

        // when
        var savedTestB = testBRepository.save(testB)
        entityManager.flush()
        entityManager.clear()
        savedTestB = testBRepository.findByIdOrNull(savedTestB.id!!)
            ?: error("TestB not found")
        savedTestB.testBChildren.add(
            TestBChild(
                name = "child_1000",
                testB = savedTestB
            )
        )
        testBRepository.save(savedTestB)
        entityManager.flush()
        entityManager.clear()
    }
}