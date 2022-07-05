package com.example.testcenter.SpaceX.data.remote.space

data class SpaceXEntity(val links: LinksEntity?,
                        val name: String?,
                        val cores: List<CoresEntity>?,
                        val success: Boolean?,
                        val date_utc: String?,
                        val id: String?) {



    fun dateYear(dateUtc: String?): String {
        return  "${dateUtc?.get(0)}${dateUtc?.get(1)}${dateUtc?.get(2)}${dateUtc?.get(3)}"
    }

}


data class LinksEntity(val patch: LinksPatchEntity?)

data class LinksPatchEntity(val small: String?,
                            val large: String?)

data class CoresEntity(val flight: Int?)

