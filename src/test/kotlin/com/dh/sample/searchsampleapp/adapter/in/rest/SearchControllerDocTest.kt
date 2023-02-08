package com.dh.sample.searchsampleapp.adapter.`in`.rest

import com.dh.sample.searchsampleapp.adapter.out.persistence.entity.EntityKeyword
import com.dh.sample.searchsampleapp.adapter.out.persistence.repository.KeywordRepository
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.*
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import org.springframework.restdocs.request.RequestDocumentation.queryParameters
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
class SearchControllerDocTest(
    private val mockMvc: MockMvc,
    private val keywordRepository: KeywordRepository
) {

    @Test
    fun testSearchBlog() {
        mockMvc.perform(
            get("/search/blog")
                .param("keyword", "토트넘")
                .param("sort", "accuracy")
                .param("size", "1")
                .param("page", "1")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "search_blog",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    queryParameters(
                        parameterWithName("keyword").description("[필수] 검색어"),
                        parameterWithName("sort").description("[선택] accurac(정확도-기본값), recency(최신순)"),
                        parameterWithName("size").description("[선택] 검색결과 갯수 (기본값 1)"),
                        parameterWithName("page").description("[선택] 페이지 넘버 (기본값 1)")
                    ),
                    responseFields(
                        fieldWithPath("hasNext").type(JsonFieldType.BOOLEAN)
                            .description("다음 페이지 존재 여부"),
                        fieldWithPath("blogs").type(JsonFieldType.ARRAY).description("검색결과 목록"),
                        fieldWithPath("blogs[].blogName").type(JsonFieldType.STRING)
                            .description("블로그명"),
                        fieldWithPath("blogs[].blogUrl").type(JsonFieldType.STRING)
                            .description("블로그 url"),
                        fieldWithPath("blogs[].postTitle").type(JsonFieldType.STRING)
                            .description("블로그 포스트 제목"),
                        fieldWithPath("blogs[].postContent").type(JsonFieldType.STRING)
                            .description("블로그 포스트 간략 설명/본문"),
                        fieldWithPath("blogs[].postCreateAt").type(JsonFieldType.NUMBER)
                            .description("블로그 포스트 생성 시간"),
                    )
                )
            )
    }

    @Test
    fun testKeywordList() {
        keywordRepository.save(EntityKeyword("토트넘", 1))

        mockMvc.perform(
            get("/list/keyword")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andDo(
                document(
                    "keyword_list",
                    preprocessResponse(prettyPrint()),
                    responseFields(
                        fieldWithPath("[]").type(JsonFieldType.ARRAY).description("키워드 결과 목록"),
                        fieldWithPath("[].rank").type(JsonFieldType.NUMBER).description("검색 순위"),
                        fieldWithPath("[].keyword").type(JsonFieldType.STRING).description("검색어"),
                        fieldWithPath("[].count").type(JsonFieldType.NUMBER).description("검색횟수"),
                    )
                )
            )
    }
}