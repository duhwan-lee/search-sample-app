package com.dh.sample.searchsampleapp.domain

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SortBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NaverPayloadTest {

    @Test
    fun testFromNaver() {
        val keyword = "keyword naver"
        val emptySortPayload = NaverPayload.from(SearchRequest(keyword, null, 0, 0))
        val accuracyPayload = NaverPayload.from(SearchRequest(keyword, SortBy.valueOf("accuracy"), 0, 0))
        val recencyPayload = NaverPayload.from(SearchRequest(keyword, SortBy.valueOf("recency"), 0, 0))
        assertEquals("sim", emptySortPayload.sort)
        assertEquals("sim", accuracyPayload.sort)
        assertEquals("date", recencyPayload.sort)
    }
}