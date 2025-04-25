package io.doubleu0714.handson.kopring.service

import com.navercorp.fixturemonkey.FixtureMonkey
import com.navercorp.fixturemonkey.api.plugin.SimpleValueJqwikPlugin
import com.navercorp.fixturemonkey.kotlin.KotlinPlugin
import com.navercorp.fixturemonkey.kotlin.giveMeKotlinBuilder
import com.navercorp.fixturemonkey.kotlin.giveMeOne
import io.doubleu0714.handson.kopring.presentation.CreateClassRequest
import io.doubleu0714.handson.kopring.presentation.CreateClassResponse
import io.doubleu0714.handson.kopring.repository.ClassRepository
import io.doubleu0714.handson.kopring.repository.MentorRepository
import io.doubleu0714.handson.kopring.repository.entity.Class
import io.doubleu0714.handson.kopring.repository.entity.Mentor
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import net.jqwik.api.Arbitraries
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class ClassServiceTest {
    val fixtureMonkey = FixtureMonkey.builder()
        .plugin(KotlinPlugin())
        .plugin(SimpleValueJqwikPlugin())
        .build()

    @Test
    fun `등록에 성공한다`() {
        // given
        val mentorRepository = mockk<MentorRepository>(relaxed = true).apply {
            every { findByIdOrNull(any()) } returns fixtureMonkey.giveMeOne<Mentor>()
        }
        val classRepository = mockk<ClassRepository>(relaxed = true).apply {
            every { save(any()) } returns fixtureMonkey.giveMeKotlinBuilder<Class>()
                .set(Class::id, Arbitraries.longs().greaterOrEqual(1L))
                .sample()
        }

        val sut = ClassService(
            mentorRepository = mentorRepository,
            classRepository = classRepository,
        )

        // when
        val response: CreateClassResponse = sut.createClass(
            request = CreateClassRequest(
                name = "test",
                mentorId = 1L,
            ),
        )

        // then
        response.classId shouldNotBe null
        verify(exactly = 1) { classRepository.save(any()) }
    }

    @Test
    fun `멘토 조회에 실패하면 Exception이 발생한다`() {
        // given
        val mentorRepository = mockk<MentorRepository>(relaxed = true).apply {
            every { findByIdOrNull(any()) } returns null
        }

        val sut = ClassService(
            mentorRepository = mentorRepository,
            classRepository = mockk(relaxed = true),
        )

        // when
        val actual = shouldThrow<Throwable> {
            sut.createClass(
                request = CreateClassRequest(
                    name = "test",
                    mentorId = 1L,
                ),
            )
        }

        // then
        (actual is IllegalArgumentException).shouldBeTrue()
        actual.message shouldBe "Mentor not found"
    }
}