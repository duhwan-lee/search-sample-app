package com.dh.sample.searchsampleapp.domain

import org.springframework.boot.configurationprocessor.json.JSONObject
import java.time.*
import java.time.format.DateTimeFormatter

data class SearchBlog(
    val blogName: String,
    val blogUrl: String,
    val postTitle: String,
    val postContent: String,
    val postCreateAt: Long
) {
    companion object {
        fun fromKakao(json: JSONObject): SearchBlog {
            val blogName = json.getString("blogname")
            val blogUrl = json.getString("url")
            val postTitle = json.getString("title")
            val postContent = json.getString("contents")
            val postCreateAt = Instant.parse(json.getString("datetime")).epochSecond
            return SearchBlog(blogName, blogUrl, postTitle, postContent, postCreateAt)
        }

        fun fromNaver(json: JSONObject): SearchBlog {
            val blogName = json.getString("bloggername")
            val blogUrl = json.getString("link")
            val postTitle = json.getString("title")
            val postContent = json.getString("description")
            val postCreateAt = LocalDate.parse(
                json.getString("postdate"),
                DateTimeFormatter.ofPattern("yyyyMMdd")
            ).toEpochSecond(LocalTime.NOON, ZoneOffset.MIN)
            return SearchBlog(blogName, blogUrl, postTitle, postContent, postCreateAt)
        }
    }


}