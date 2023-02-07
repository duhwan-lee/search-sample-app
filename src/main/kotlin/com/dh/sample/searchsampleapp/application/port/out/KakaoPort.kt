package com.dh.sample.searchsampleapp.application.port.out

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult
import com.dh.sample.searchsampleapp.domain.KakaoPayload

interface KakaoPort {
    fun searchBlogByKakao(kakaoPayload: KakaoPayload): SearchResult
}