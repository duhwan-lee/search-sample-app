package com.dh.sample.searchsampleapp.adapter.`in`.rest

import com.dh.sample.searchsampleapp.infrastructure.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SearchController {

    val log = logger<SearchController>()

    @GetMapping("/search/blog")
    fun getSearchBlog(){

    }

    @GetMapping("/list/keyword")
    fun getKeywordList(){}
}