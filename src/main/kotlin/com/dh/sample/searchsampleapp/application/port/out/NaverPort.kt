package com.dh.sample.searchsampleapp.application.port.out

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult
import com.dh.sample.searchsampleapp.domain.NaverPayload

interface NaverPort {
    fun searchBlogByNaver(naverPayload: NaverPayload): SearchResult
}