package com.dh.sample.searchsampleapp.adapter.out.persistence.repository

import com.dh.sample.searchsampleapp.adapter.out.persistence.entity.EntityKeyword
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface KeywordRepository : JpaRepository<EntityKeyword, Long?> {

    //fun findByKeywordEquals(keyword: String): EntityKeyword? //for concurrency test code

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select k from EntityKeyword k where k.keyword = :keyword")
    fun findWithKeywordForUpdate(keyword: String?): EntityKeyword?
}