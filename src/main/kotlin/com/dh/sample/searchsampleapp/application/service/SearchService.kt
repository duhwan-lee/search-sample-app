package com.dh.sample.searchsampleapp.application.service

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult
import com.dh.sample.searchsampleapp.application.port.`in`.SearchBlogUseCase
import com.dh.sample.searchsampleapp.application.port.`in`.SearchKeywordUseCase
import com.dh.sample.searchsampleapp.application.port.out.H2KeywordPort
import com.dh.sample.searchsampleapp.application.port.out.KakaoPort
import com.dh.sample.searchsampleapp.application.port.out.NaverPort
import com.dh.sample.searchsampleapp.domain.KakaoPayload
import com.dh.sample.searchsampleapp.domain.KeywordRank
import com.dh.sample.searchsampleapp.domain.NaverPayload
import com.dh.sample.searchsampleapp.domain.exception.SearchBlogException
import com.dh.sample.searchsampleapp.logger
import org.springframework.stereotype.Service

@Service
class SearchService(
    private val kakaoPort: KakaoPort,
    private val naverPort: NaverPort,
    private val h2KeywordPort: H2KeywordPort
) :
    SearchBlogUseCase, SearchKeywordUseCase {

    val log = logger<SearchService>()

    override fun searchBlogBy(searchRequest: SearchRequest): SearchResult {
        return try {
            val kakaoPayload = KakaoPayload.from(searchRequest)
            kakaoPort.searchBlogByKakao(kakaoPayload)
        } catch (e: SearchBlogException) {
            log.error(e.message)
            val naverPayload = NaverPayload.from(searchRequest)
            naverPort.searchBlogByNaver(naverPayload)
        }
    }

    override fun asyncAddCntSearchKeyword(keyword: String) {
        log.info("async saved $keyword")
        h2KeywordPort.addCntByKeyword(keyword)
    }

    /*
    //for concurrency test code
    override fun addCntSearchKeyword(keyword: String): EntityKeyword {
        log.info("async keyword save $keyword")
        return h2KeywordPort.addCntByKeyword(keyword);
    }*/

    override fun getKeywordTopList(): List<KeywordRank> {
        return h2KeywordPort.getTopList(10).mapIndexed { index, entityKeyword ->
            KeywordRank(index + 1, entityKeyword.keyword, entityKeyword.cnt)
        }
    }


}