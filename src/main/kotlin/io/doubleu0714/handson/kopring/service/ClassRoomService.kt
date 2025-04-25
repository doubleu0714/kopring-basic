package io.doubleu0714.handson.kopring.service

import io.doubleu0714.handson.kopring.presentation.RegisterMenteeRequest
import io.doubleu0714.handson.kopring.presentation.RegisterMenteeResponse
import io.doubleu0714.handson.kopring.presentation.UnregisterMenteeResponse
import io.doubleu0714.handson.kopring.repository.ClassRepository
import io.doubleu0714.handson.kopring.repository.ClassRoomRepository
import io.doubleu0714.handson.kopring.repository.MenteeRepository
import io.doubleu0714.handson.kopring.repository.entity.ClassRoom
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClassRoomService(
    private val menteeRepository: MenteeRepository,
    private val classRepository: ClassRepository,
    private val classRoomRepository: ClassRoomRepository
) {
    @Transactional
    fun registerMentee(request: RegisterMenteeRequest): RegisterMenteeResponse {
        val mentee = menteeRepository.findByIdOrNull(request.menteeId)
            ?: throw IllegalArgumentException("Mentee not found")
        val classRoom = classRepository.findByIdOrNull(request.classId)
            ?: throw IllegalArgumentException("Class not found")
        val classRoomEntity = classRoomRepository.save(
            ClassRoom(
                mentee = mentee,
                cls = classRoom
            )
        )
        return RegisterMenteeResponse(classRoomId = classRoomEntity.id!!)
    }

    @Transactional
    fun unregisterMentee(classRoomId: Long, menteeId: Long): UnregisterMenteeResponse {
        val classRoom = classRoomRepository.findByIdOrNull(classRoomId)
            ?: throw IllegalArgumentException("ClassRoom not found")
        if (classRoom.mentee.id != menteeId) {
            throw IllegalArgumentException("Mentee ID does not match")
        }
        classRoomRepository.delete(classRoom)
        return UnregisterMenteeResponse(classRoomId = classRoom.id!!)
    }
}