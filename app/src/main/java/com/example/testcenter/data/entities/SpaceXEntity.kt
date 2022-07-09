package com.example.testcenter.space

data class SpaceXEntity(
    val links: LinksEntity?,
    val name: String?,
    val cores: List<CoresEntity>?,
    val success: Boolean?,
    val date_utc: String?,
    val crew: ArrayList<String>?,
    val details: String?
    )

    {
        //в object utils
    fun dateYear(dateUtc: String?): String {
        return  "${dateUtc?.get(0)}${dateUtc?.get(1)}${dateUtc?.get(2)}${dateUtc?.get(3)}"
    }

}

data class LinksEntity(val patch: LinksPatchEntity?)

data class LinksPatchEntity(val small: String?,
                            val large: String?)

data class CoresEntity(val flight: Int?)



