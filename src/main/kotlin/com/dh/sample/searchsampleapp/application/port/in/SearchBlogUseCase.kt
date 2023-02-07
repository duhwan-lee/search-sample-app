package com.dh.sample.searchsampleapp.application.port.`in`

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult

interface SearchBlogUseCase {
    fun searchBlogBy(searchRequest: SearchRequest) : SearchResult
}