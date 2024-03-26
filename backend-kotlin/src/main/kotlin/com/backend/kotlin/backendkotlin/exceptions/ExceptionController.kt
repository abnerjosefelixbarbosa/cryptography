package com.backend.kotlin.backendkotlin.exceptions

import jakarta.persistence.EntityNotFoundException
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.security.NoSuchAlgorithmException
import java.time.LocalDateTime
import java.util.function.Consumer


@RestControllerAdvice
class ExceptionController {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): Map<String, String?> {
        val errors: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.getDefaultMessage()
            errors[fieldName] = errorMessage
        })
        return errors
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleException(e: RuntimeException, request: HttpServletRequest): ResponseEntity<ExceptionDetails> {
        val exceptionDetails = ExceptionDetails(
            LocalDateTime.now(), 400,
            e.message!!,
            request.requestURI
        )
        return ResponseEntity.status(400).body(exceptionDetails)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleException(e: EntityNotFoundException, request: HttpServletRequest): ResponseEntity<ExceptionDetails> {
        val exceptionDetails = ExceptionDetails(
            LocalDateTime.now(), 400,
            e.message!!,
            request.requestURI
        )
        return ResponseEntity.status(404).body(exceptionDetails)
    }

    @ExceptionHandler(NoSuchAlgorithmException::class)
    fun handleException(e: NoSuchAlgorithmException, request: HttpServletRequest): ResponseEntity<ExceptionDetails> {
        val exceptionDetails = ExceptionDetails(
            LocalDateTime.now(), 400,
            e.message!!,
            request.requestURI
        )
        return ResponseEntity.status(400).body(exceptionDetails)
    }
}