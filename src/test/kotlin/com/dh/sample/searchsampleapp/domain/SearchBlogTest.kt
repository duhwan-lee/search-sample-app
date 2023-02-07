package com.dh.sample.searchsampleapp.domain

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.configurationprocessor.json.JSONObject

class SearchBlogTest {

    @Test
    fun testFromKakao() {
        val blogName = "kakaoBlog"
        val blogUrl = "https://kakao.blog"
        val postTitle = "blog kakao"
        val postContent = "I am Kakao"
        val postCreateAt = "2017-05-07T18:50:07.000+09:00"
        val jsonStr = "{\n" +
                "    \"title\": \"$postTitle\",    \n" +
                "    \"contents\": \"$postContent\",\n" +
                "    \"url\": \"$blogUrl\",\n" +
                "    \"blogname\": \"$blogName\",\n" +
                "    \"datetime\": \"$postCreateAt\"\n" +
                "    },"
        val json = JSONObject(jsonStr)
        val searchBlog = SearchBlog.fromKakao(json)
        assertEquals(blogName, searchBlog.blogName)
        assertEquals(blogUrl, searchBlog.blogUrl)
        assertEquals(postTitle, searchBlog.postTitle)
        assertEquals(postContent, searchBlog.postContent)
        assertTrue(searchBlog.postCreateAt > 0)
    }

    @Test
    fun testFromNaver() {
        val bloggername = "NaverBlog"
        val link = "https://naver.blog"
        val title = "blog naver"
        val description = "I am Naver"
        val postdate = "20161208"
        val jsonStr = "{\n" +
                "    \"title\": \"$title\",    \n" +
                "    \"description\": \"$description\",\n" +
                "    \"link\": \"$link\",\n" +
                "    \"bloggername\": \"$bloggername\",\n" +
                "    \"postdate\": \"$postdate\"\n" +
                "    },"
        val json = JSONObject(jsonStr)
        val searchBlog = SearchBlog.fromNaver(json)
        assertEquals(bloggername, searchBlog.blogName)
        assertEquals(link, searchBlog.blogUrl)
        assertEquals(title, searchBlog.postTitle)
        assertEquals(description, searchBlog.postContent)
        assertTrue(searchBlog.postCreateAt > 0)
    }
}