package com.dh.sample.searchsampleapp.application.port.out

import com.dh.sample.searchsampleapp.adapter.out.persistence.entity.EntityKeyword

interface H2KeywordPort {
    fun getCntByKeyword(keyword: String): EntityKeyword

    fun getTopList(size : Int): List<EntityKeyword>
}