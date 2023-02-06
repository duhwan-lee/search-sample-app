package com.dh.sample.searchsampleapp.adapter.out.http

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "NaverClient", url = "https://openapi.naver.com")
interface NaverClient {
    @GetMapping("/v1/search/blog.json")
    fun searchBlog(
        @RequestHeader("X-Naver-Client-Id") clientKey: String,
        @RequestHeader("X-Naver-Client-Secret") clientSecret: String,
        @RequestParam query: String,
        @RequestParam sort: String,
        @RequestParam start: Int?,
        @RequestParam display: Int?
    ): ResponseEntity<String>

}