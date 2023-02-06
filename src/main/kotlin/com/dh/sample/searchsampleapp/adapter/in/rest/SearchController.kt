package com.dh.sample.searchsampleapp.adapter.`in`.rest

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.application.port.`in`.SearchBlogUseCase
import com.dh.sample.searchsampleapp.domain.SearchBlog
import com.dh.sample.searchsampleapp.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(private val searchBlogUseCase: SearchBlogUseCase) {

    val log = logger<SearchController>()

    @GetMapping("/search/blog")
    fun getSearchBlog(searchRequest: SearchRequest): ResponseEntity<List<SearchBlog>> {
        log.info("$searchRequest")
        val listSearchBlog = searchBlogUseCase.searchBlogBy(searchRequest)
        return ResponseEntity(listSearchBlog, HttpStatus.OK)
    }

    @GetMapping("/list/keyword")
    fun getKeywordList() {
    }
}