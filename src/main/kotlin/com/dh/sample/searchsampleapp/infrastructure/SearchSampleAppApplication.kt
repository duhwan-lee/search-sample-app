package com.dh.sample.searchsampleapp.infrastructure

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SearchSampleAppApplication

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun main(args: Array<String>) {
    runApplication<SearchSampleAppApplication>(*args)
}
