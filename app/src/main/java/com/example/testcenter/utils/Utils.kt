package com.example.testcenter.utils

object Utils {
    fun getUtcDate(dateUtc: String): String {
        return "${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }
}