package com.ahmedroid.domain.entity

enum class CountryFilter() : Filter {
    AE,
    US,
    JO;

    override val filterKey: String
        get() = this.name
}