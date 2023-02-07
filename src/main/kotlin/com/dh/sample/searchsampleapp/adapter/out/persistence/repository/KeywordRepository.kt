package com.dh.sample.searchsampleapp.adapter.out.persistence.repository

import com.dh.sample.searchsampleapp.adapter.out.persistence.entity.EntityKeyword
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface KeywordRepository : JpaRepository<EntityKeyword, Long?> {
    fun findByKeywordEquals(keyword: String): EntityKeyword

}