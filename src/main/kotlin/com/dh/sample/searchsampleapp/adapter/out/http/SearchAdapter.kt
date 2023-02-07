package com.dh.sample.searchsampleapp.adapter.out.http

import com.dh.sample.searchsampleapp.adapter.`in`.data.SearchResult
import com.dh.sample.searchsampleapp.adapter.out.persistence.entity.EntityKeyword
import com.dh.sample.searchsampleapp.adapter.out.persistence.repository.KeywordRepository
import com.dh.sample.searchsampleapp.application.port.out.H2KeywordPort
import com.dh.sample.searchsampleapp.application.port.out.KakaoPort
import com.dh.sample.searchsampleapp.application.port.out.NaverPort
import com.dh.sample.searchsampleapp.domain.KakaoPayload
import com.dh.sample.searchsampleapp.domain.NaverPayload
import com.dh.sample.searchsampleapp.domain.SearchBlog
import com.dh.sample.searchsampleapp.domain.exception.SearchBlogException
import com.dh.sample.searchsampleapp.infrastructure.Adapter
import com.dh.sample.searchsampleapp.logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.configurationprocessor.json.JSONObject
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional

@Adapter
class SearchAdapter(
    private val kakaoClient: KakaoClient,
    private val naverClient: NaverClient,
    private val keywordRepository: KeywordRepository,
    @Value("\${sample.search.client.kakao.api-key}") private val kakaoApiKey: String,
    @Value("\${sample.search.client.naver.client-key}") private val naverClientKey: String,
    @Value("\${sample.search.client.naver.client-secret}") private val naverClientSecret: String,
) : KakaoPort, NaverPort, H2KeywordPort {

    val log = logger<SearchAdapter>()
    override fun searchBlogByKakao(kakaoPayload: KakaoPayload): SearchResult {
        val response = try {
            kakaoClient.searchBlog(
                "KakaoAK $kakaoApiKey",
                kakaoPayload.query,
                kakaoPayload.sort,
                kakaoPayload.page,
                kakaoPayload.size
            )
        } catch (e: Exception) {
            log.error(e.toString(), e.cause)
            throw SearchBlogException("Fail Kakao API Request")
        }

        if (response.statusCode == HttpStatus.OK) {
            response.body?.let {
                JSONObject(it).let { jsonObject ->
                    val isEnd = jsonObject.optJSONObject("meta").getBoolean("is_end")
                    val documents = jsonObject.optJSONArray("documents")
                    val resultList = ArrayList<SearchBlog>()
                    for (i in 0 until documents.length()) {
                        resultList.add(SearchBlog.fromKakao(documents.getJSONObject(i)))
                    }
                    return SearchResult(!isEnd, resultList)

                }
            }
        }

        throw SearchBlogException("Fail Kakao API Response Parse")
    }

    override fun searchBlogByNaver(naverPayload: NaverPayload): SearchResult {
        val response =
            try {
                naverClient.searchBlog(
                    naverClientKey,
                    naverClientSecret,
                    naverPayload.query,
                    naverPayload.sort,
                    naverPayload.start,
                    naverPayload.display
                )
            } catch (e: Exception) {
                log.error(e.toString(), e.cause)
                throw SearchBlogException("Fail Naver API Request")
            }
        if (response.statusCode == HttpStatus.OK) {
            response.body?.let {
                JSONObject(it).let { jsonObject ->
                    val channelObj = jsonObject.optJSONObject("channel")
                    val total = channelObj.getInt("total")
                    val start = channelObj.getInt("start")
                    val display = channelObj.getInt("display")
                    val hasNext = total > (start + display)
                    val items = jsonObject.optJSONArray("items")
                    val resultList = ArrayList<SearchBlog>()
                    for (i in 0 until items.length()) {
                        resultList.add(SearchBlog.fromNaver(items.getJSONObject(i)))
                    }
                    return SearchResult(hasNext, resultList)
                }
            }
        }

        throw SearchBlogException("Fail Naver API Response Parse")

    }

    /*
    //for concurrency test code
    @Transactional
    override fun addCntByKeyword(keyword: String): EntityKeyword {
        val entity =
            keywordRepository.findByKeywordEquals(keyword) ?: EntityKeyword(keyword, 0)
        entity.addCnt()
        return keywordRepository.save(entity)
    }*/

    @Transactional
    override fun addCntByKeyword(keyword: String): EntityKeyword {
        val entity =
            keywordRepository.findWithKeywordForUpdate(keyword) ?: EntityKeyword(keyword, 0)
        entity.addCnt()
        return keywordRepository.save(entity)
    }

    override fun getTopList(size: Int): List<EntityKeyword> {
        val result =
            keywordRepository.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "cnt")))
        return result.toList()
    }
}