package io.doubleu0714.handson.kopring.presentation

import io.doubleu0714.handson.kopring.service.ClassRoomService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/classrooms")
class ClassRoomController(
    private val classRoomService: ClassRoomService
) {
    @PostMapping
    fun registerMentee(@RequestBody request: RegisterMenteeRequest): RegisterMenteeResponse =
        classRoomService.registerMentee(request = request)

    @DeleteMapping("/{classRoomId}/mentees/{menteeId}")
    fun unregisterMentee(@PathVariable classRoomId: Long, @PathVariable menteeId: Long): UnregisterMenteeResponse =
        classRoomService.unregisterMentee(classRoomId = classRoomId, menteeId = menteeId)
}

data class RegisterMenteeResponse(
    val classRoomId: Long,
)

data class UnregisterMenteeResponse(
    val classRoomId: Long,
)

data class RegisterMenteeRequest(
    val classId: Long,
    val menteeId: Long,
)
