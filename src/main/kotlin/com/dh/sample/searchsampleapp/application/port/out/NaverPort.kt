package com.dh.sample.searchsampleapp.application.port.out

import com.dh.sample.searchsampleapp.domain.NaverPayload
import com.dh.sample.searchsampleapp.domain.SearchBlog

interface NaverPort {
    fun searchBlogByNaver(naverPayload: NaverPayload): List<SearchBlog>
}