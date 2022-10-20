package com.ahmedroid.domain.entity

enum class CategoryFilter : Filter {
    SPORTS,
    HEALTH,
    BUSINESS;

    override val filterKey: String
        get() = this.name
}