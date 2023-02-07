package com.dh.sample.searchsampleapp.domain

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SortBy

data class NaverPayload(
    val query: String,
    val sort: String, val start: Int, val display: Int
) {
    companion object {
        fun from(searchRequest: SearchRequest): NaverPayload {
            val query = searchRequest.keyword
            val sort = searchRequest.sort?.let {
                if (it == SortBy.accuracy) "sim" else "date"
            } ?: "sim"
            //Memo : 아래 기본값 세팅은 adapter에서 일괄처리해 줄수도 있음.
            val page = searchRequest.page ?: 1
            val size = searchRequest.size ?: 10

            return NaverPayload(query, sort, page, size)
        }
    }
}