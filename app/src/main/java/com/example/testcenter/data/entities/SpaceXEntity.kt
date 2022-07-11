package com.example.testcenter.data.entities

data class SpaceXEntity(
    val links: LinksEntity?,
    val name: String?,
    val cores: List<CoresEntity>?,
    val success: Boolean?,
    val date_utc: String?,
    val crew: ArrayList<String>?,
    val details: String?
    )

data class LinksEntity(val patch: LinksPatchEntity?)

data class LinksPatchEntity(val small: String?,
                            val large: String?)

data class CoresEntity(val flight: Int?)



