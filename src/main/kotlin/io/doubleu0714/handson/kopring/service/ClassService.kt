package io.doubleu0714.handson.kopring.service

import io.doubleu0714.handson.kopring.configuration.Tx
import io.doubleu0714.handson.kopring.presentation.CreateClassRequest
import io.doubleu0714.handson.kopring.presentation.CreateClassResponse
import io.doubleu0714.handson.kopring.repository.ClassRepository
import io.doubleu0714.handson.kopring.repository.MentorRepository
import io.doubleu0714.handson.kopring.repository.entity.Class
import io.doubleu0714.handson.kopring.repository.entity.Mentor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClassService(
    private val mentorRepository: MentorRepository,
    private val classRepository: ClassRepository,
    private val classDataService: ClassDataService,
) {
    @Transactional
    fun createClass(request: CreateClassRequest): CreateClassResponse =
        mentorRepository.findByIdOrNull(request.mentorId)?.let {
            val cls = classRepository.save(
                Class(
                    mentor = it,
                    name = request.name,
                ),
            )
            check(cls.id != null) { "Class ID should not be null" }
            CreateClassResponse(classId = cls.id)
        } ?: throw IllegalArgumentException("Mentor not found")

    @Transactional
    fun requiresNewTest() {
        mentorRepository.save(Mentor(name = "test"))
        classDataService.saveMentor()
        throw IllegalStateException("Test Exception")
    }

    @Transactional
    fun requiresNewTest2() {
        mentorRepository.save(Mentor(name = "test"))
        Tx.requiresNew {
            mentorRepository.save(Mentor(name = "requiresNew Test2"))
        }
        throw IllegalStateException("Test Exception")
    }
}