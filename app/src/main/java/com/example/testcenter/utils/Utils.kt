package com.example.testcenter.utils

object Utils {
    fun getUtcDate(dateUtc: String): String {
        return "${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }

    fun dateYear(dateUtc: String?): String {
        return  "${dateUtc?.get(0)}${dateUtc?.get(1)}${dateUtc?.get(2)}${dateUtc?.get(3)}"
    }


    fun makeSuccessLabel(success: Boolean?) =
        when(success) {
            true ->  "Status: Success"
            false -> "Status: Not Success"
            else -> "Status: Undefined"
        }


}