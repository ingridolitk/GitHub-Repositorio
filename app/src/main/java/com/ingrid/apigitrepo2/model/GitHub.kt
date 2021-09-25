package com.ingrid.apigitrepo2.model

data class Item(
    val id: Int,
    val name: String,
    val owner: Owner,
    val description: String,
    val language: String,
    val created_at: String,
    val stargazers_count: Int,
    val open_issues_count: Int
)

data class Owner(val login: String, val avatar_url: String)