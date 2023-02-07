package com.dh.sample.searchsampleapp.infrastructure

import com.dh.sample.searchsampleapp.domain.exception.SearchBlogException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(SearchBlogException::class)
    fun apiExceptionHandler(e: SearchBlogException): ResponseEntity<String> {
        return ResponseEntity<String>(e.message, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}