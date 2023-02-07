package com.dh.sample.searchsampleapp.domain

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SortBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class KakaoPayloadTest {
    @Test
    fun testFromKakao() {
        val keyword = "keyword kakao"
        val emptySortPayload = KakaoPayload.from(SearchRequest(keyword, null, 0, 0))
        val accuracyPayload = KakaoPayload.from(SearchRequest(keyword, SortBy.valueOf("accuracy"), 0, 0))
        val recencyPayload = KakaoPayload.from(SearchRequest(keyword, SortBy.valueOf("recency"), 0, 0))
        assertEquals("accuracy", emptySortPayload.sort)
        assertEquals("accuracy", accuracyPayload.sort)
        assertEquals("recency", recencyPayload.sort)
    }
}