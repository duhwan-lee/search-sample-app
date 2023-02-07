package com.dh.sample.searchsampleapp.application.port.`in`

import com.dh.sample.searchsampleapp.domain.KeywordRank
import org.springframework.scheduling.annotation.Async

interface SearchKeywordUseCase {
    @Async
    fun asyncAddCntSearchKeyword(keyword: String)

    //for concurrency test code
    //fun addCntSearchKeyword(keyword: String):EntityKeyword

    fun getKeywordTopList():List<KeywordRank>
}