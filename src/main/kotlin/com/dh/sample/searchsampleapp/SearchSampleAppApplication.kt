package com.dh.sample.searchsampleapp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync(proxyTargetClass = true)
@EnableFeignClients
class SearchSampleAppApplication

inline fun <reified T> logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

fun main(args: Array<String>) {
    runApplication<SearchSampleAppApplication>(*args)
}
