package com.dh.sample.searchsampleapp.adapter.out.http

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "KakaoClient", url = "https://dapi.kakao.com")
interface KakaoClient {
    @GetMapping("/v2/search/blog")
    fun searchBlog(
        @RequestHeader("Authorization") apiKey: String,
        @RequestParam query: String,
        @RequestParam sort: String,
        @RequestParam page: Int?,
        @RequestParam size: Int?,
    ): ResponseEntity<String>

}