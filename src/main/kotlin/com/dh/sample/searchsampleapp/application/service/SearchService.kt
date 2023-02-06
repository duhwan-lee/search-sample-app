package com.dh.sample.searchsampleapp.application.service

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchRequest
import com.dh.sample.searchsampleapp.application.port.`in`.SearchBlogUseCase
import com.dh.sample.searchsampleapp.application.port.out.KakaoPort
import com.dh.sample.searchsampleapp.application.port.out.NaverPort
import com.dh.sample.searchsampleapp.domain.KakaoPayload
import com.dh.sample.searchsampleapp.domain.NaverPayload
import com.dh.sample.searchsampleapp.domain.SearchBlog
import org.springframework.stereotype.Service

@Service
class SearchService(private val kakaoPort: KakaoPort, private val naverPort: NaverPort) :
    SearchBlogUseCase {
    //    val log = logger<SearchService>()
    override fun searchBlogBy(searchRequest: SearchRequest): List<SearchBlog> {
        //Memo 특정 상황에 따라 카카오와 네이버가 변경되도록 고도화할 수 있다.
        val sumIfCondition = true
        return if (sumIfCondition) {
            val kakaoPayload = KakaoPayload.from(searchRequest)
            kakaoPort.searchBlogByKakao(kakaoPayload)
        } else {
            val naverPayload = NaverPayload.from(searchRequest)
            naverPort.searchBlogByNaver(naverPayload)
        }
    }
}