package com.dh.sample.searchsampleapp.application.port.out

import com.dh.sample.searchsampleapp.domain.KakaoPayload
import com.dh.sample.searchsampleapp.domain.SearchBlog

interface KakaoPort {
    fun searchBlogByKakao(kakaoPayload: KakaoPayload): List<SearchBlog>
}