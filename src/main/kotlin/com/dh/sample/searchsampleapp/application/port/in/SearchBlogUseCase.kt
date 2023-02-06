package com.dh.sample.searchsampleapp.application.port.`in`

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.domain.SearchBlog

interface SearchBlogUseCase {
    fun searchBlogBy(searchRequest: SearchRequest) : List<SearchBlog>
}