package com.dh.sample.searchsampleapp.adapter.`in`.data

import jakarta.validation.Validation
import jakarta.validation.Validator
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class SearchRequestTest {
    private val validator: Validator = Validation.buildDefaultValidatorFactory().validator

    /*validation annotation의 대한 테스트*/
    @Test
    fun validationParam() {
        val emptyRequest = SearchRequest("", SortBy.accuracy, 0, 0)
        val emptyValidate = validator.validate(emptyRequest)
        assertThat(emptyValidate).hasSize(1)

        val request = SearchRequest("not empty", SortBy.accuracy, 0, 0)
        val validate = validator.validate(request)
        assertThat(validate).hasSize(0)
    }
}