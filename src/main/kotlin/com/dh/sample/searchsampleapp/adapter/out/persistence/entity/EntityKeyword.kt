package com.dh.sample.searchsampleapp.adapter.out.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "keyword")
class EntityKeyword(keyword: String, cnt: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null
    val keyword: String = keyword
    val cnt: Int = cnt
}