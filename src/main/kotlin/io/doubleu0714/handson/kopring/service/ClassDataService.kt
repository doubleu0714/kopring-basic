package io.doubleu0714.handson.kopring.service

import io.doubleu0714.handson.kopring.repository.ClassRepository
import io.doubleu0714.handson.kopring.repository.MentorRepository
import io.doubleu0714.handson.kopring.repository.entity.Mentor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Service
class ClassDataService(
    private val mentorRepository: MentorRepository,
    private val classRepository: ClassRepository,
) {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun saveMentor() {
        mentorRepository.save(Mentor(name = "requiresNew Test1"))
    }
}