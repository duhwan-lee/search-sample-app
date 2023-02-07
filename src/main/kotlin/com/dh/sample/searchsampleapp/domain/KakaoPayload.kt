package com.dh.sample.searchsampleapp.domain

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest

data class KakaoPayload(
    val query: String,
    val sort: String, val page: Int, val size: Int
) {
    companion object {
        fun from(searchRequest: SearchRequest): KakaoPayload {
            val query = searchRequest.keyword
            //Memo : 아래 기본값 세팅은 adapter에서 일괄처리해 줄수도 있음.
            val sort = searchRequest.sort?.name ?: "accuracy"
            val page = searchRequest.page ?: 1
            val size = searchRequest.size ?: 10

            return KakaoPayload(query, sort, page, size)
        }
    }
}