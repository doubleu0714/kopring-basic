package io.doubleu0714.handson.kopring.advice

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionAdvice {
    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleIllegalArgumentException(exception: IllegalArgumentException): CommonErrorResponse =
        CommonErrorResponse(
            code = "BAD_REQUEST",
            message = exception.message ?: "Bad Request"
        )

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(exception: Exception): CommonErrorResponse =
        CommonErrorResponse(
            code = "INTERNAL_SERVER_ERROR",
            message = exception.message ?: "Internal Server Error"
        )
}

data class CommonErrorResponse(
    val code: String,
    val message: String
)