package com.dh.sample.searchsampleapp.adapter.`in`.data

import jakarta.validation.constraints.NotBlank


data class SearchRequest(
    @field:NotBlank val keyword: String,
    val sort: SortBy?,
    val page: Int?,
    val size: Int?
)
