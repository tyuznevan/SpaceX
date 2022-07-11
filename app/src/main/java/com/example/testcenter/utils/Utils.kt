package com.example.testcenter.utils

import com.example.testcenter.data.entities.CrewEntity

object Utils {
    fun getUtcDate(dateUtc: String): String {
        return "${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }

    fun dateYear(dateUtc: String?): String {
        return "${dateUtc?.get(0)}${dateUtc?.get(1)}${dateUtc?.get(2)}${dateUtc?.get(3)}"
    }


    fun makeSuccessLabel(success: Boolean?) =
        when (success) {
            true -> "Status: Success"
            false -> "Status: Not Success"
            else -> "Status: Undefined"
        }

    fun getUtcHMDate(dateUtc: String): String {
        return "${dateUtc[11]}${dateUtc[12]}-${dateUtc[14]}${dateUtc[15]} ${dateUtc[8]}${dateUtc[9]}-${dateUtc[5]}${dateUtc[6]}-${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }

    fun getCrewAgency(
        crewList: ArrayList<String>?,
        crewData: ArrayList<CrewEntity>?
    ): String {
        val i: Int = 0
        var res: String = ""
        crewList?.map {
            res += " ${findCrewAgencyById(it, crewData)}\n"
        }
        return res
    }

    private fun findCrewAgencyById(idString: String, crewData: ArrayList<CrewEntity>?): String {
        var result: String = ""
        crewData?.map {
            if (idString == it.id) {
                result = it.agency.toString()
            }
        }
        return result
    }

    fun getCrewStatus(
        crewList: ArrayList<String>?,
        crewData: ArrayList<CrewEntity>?
    ): String {
        val i: Int = 0
        var res: String = ""
        crewList?.map {
            res += " ${findCrewStatusById(it, crewData)}\n"
        }
        return res
    }

    private fun findCrewStatusById(idString: String, crewData: ArrayList<CrewEntity>?): String {
        var result: String = ""
        crewData?.map {
            if (idString == it.id) {
                result = it.status.toString()
            }
        }
        return result
    }

    fun getCrewName(
        crewList: ArrayList<String>?,
        crewData: ArrayList<CrewEntity>?
    ): String {
        val i: Int = 0
        var res: String = ""
        crewList?.map {
            res += "${findCrewNameById(it, crewData)}\n"
        }
        return res
    }

    private fun findCrewNameById(idString: String, crewData: ArrayList<CrewEntity>?): String {
        var result: String = ""
        crewData?.map {
            if (idString == it.id) {
                result = it.name.toString()
            }
        }
        return result
    }

}