package com.dh.sample.searchsampleapp.adapter.`in`.rest

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult
import com.dh.sample.searchsampleapp.application.port.`in`.SearchBlogUseCase
import com.dh.sample.searchsampleapp.application.port.`in`.SearchKeywordUseCase
import com.dh.sample.searchsampleapp.domain.KeywordRank
import com.dh.sample.searchsampleapp.logger
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController(
    private val searchBlogUseCase: SearchBlogUseCase,
    private val searchKeywordUseCase: SearchKeywordUseCase
) {

    val log = logger<SearchController>()

    @GetMapping("/search/blog")
    fun getSearchBlog(@Valid searchRequest: SearchRequest): ResponseEntity<SearchResult> {
        log.info("$searchRequest")
        searchKeywordUseCase.asyncAddCntSearchKeyword(searchRequest.keyword)
        val searchResult = searchBlogUseCase.searchBlogBy(searchRequest)
        return ResponseEntity(searchResult, HttpStatus.OK)
    }

    @GetMapping("/list/keyword")
    fun getKeywordList(): ResponseEntity<List<KeywordRank>> {
        val list = searchKeywordUseCase.getKeywordTopList()
        return ResponseEntity(list, HttpStatus.OK)
    }

    /*
    //for concurrency test code
    @GetMapping("/test")
    fun jpaTest(@RequestParam("keyword") keyword: String): ResponseEntity<EntityKeyword> {
        val entity = searchKeywordUseCase.addCntSearchKeyword(keyword)
        return ResponseEntity(entity, HttpStatus.OK)
    }*/
}