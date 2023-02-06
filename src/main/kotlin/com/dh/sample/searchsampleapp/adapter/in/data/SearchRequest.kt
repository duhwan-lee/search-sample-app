package com.dh.sample.searchsampleapp.adapter.`in`.data


data class SearchRequest(
    val keyword: String,
    val sort: SortBy?,
    val page: Int?,
    val size: Int?
)
