package io.doubleu0714.handson.kopring.presentation

import io.doubleu0714.handson.kopring.service.ClassService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/classes")
class ClassController(
    private val classService: ClassService,
) {
    @PostMapping
    fun createClass(@RequestBody request: CreateClassRequest): CreateClassResponse =
        classService.createClass(request = request)
}

data class CreateClassRequest(
    val mentorId: Long,
    val name: String,
)

data class CreateClassResponse(
    val classId: Long,
)
