package com.dh.sample.searchsampleapp.adapter.out.http

import com.dh.sample.searchsampleapp.application.port.out.KakaoPort
import com.dh.sample.searchsampleapp.application.port.out.NaverPort
import com.dh.sample.searchsampleapp.domain.KakaoPayload
import com.dh.sample.searchsampleapp.domain.NaverPayload
import com.dh.sample.searchsampleapp.domain.SearchBlog
import com.dh.sample.searchsampleapp.domain.exception.SearchBlogException
import com.dh.sample.searchsampleapp.infrastructure.Adapter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.http.HttpStatus

@Adapter
class SearchAdapter(
    private val kakaoClient: KakaoClient,
    private val naverClient: NaverClient,
    @Value("\${sample.search.client.kakao.api-key}") private val kakaoApiKey: String,
    @Value("\${sample.search.client.naver.client-key}") private val naverClientKey: String,
    @Value("\${sample.search.client.naver.client-secret}") private val naverClientSecret: String,
) : KakaoPort, NaverPort {

//    val log = logger<SearchAdapter>()
    override fun searchBlogByKakao(kakaoPayload: KakaoPayload): List<SearchBlog> {
        val response = kakaoClient.searchBlog(
            "KakaoAK $kakaoApiKey",
            kakaoPayload.query,
            kakaoPayload.sort,
            kakaoPayload.page,
            kakaoPayload.size
        )
        if (response.statusCode == HttpStatus.OK) {
            response.body?.let {
                JSONObject(it).let { jsonObject ->
                    val documents = jsonObject.optJSONArray("documents")
                    val resultList = ArrayList<SearchBlog>()
                    for (i in 0 until documents.length()) {
                        resultList.add(SearchBlog.fromKakao(documents.getJSONObject(i)))
                    }
                    return resultList
                }
            }
        }

        throw SearchBlogException()
    }

    override fun searchBlogByNaver(naverPayload: NaverPayload): List<SearchBlog> {
        val response = naverClient.searchBlog(
            naverClientKey,
            naverClientSecret,
            naverPayload.query,
            naverPayload.sort,
            naverPayload.start,
            naverPayload.display
        )
        if (response.statusCode == HttpStatus.OK) {
            response.body?.let {
                JSONObject(it).let { jsonObject ->

                    val items = jsonObject.optJSONArray("items")
                    val resultList = ArrayList<SearchBlog>()
                    for (i in 0 until items.length()) {
                        resultList.add(SearchBlog.fromNaver(items.getJSONObject(i)))
                    }
                    return resultList
                }
            }
        }

        throw SearchBlogException()
    }
}