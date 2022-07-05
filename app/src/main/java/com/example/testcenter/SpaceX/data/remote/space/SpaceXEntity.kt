package com.example.testcenter.SpaceX.data.remote.space

data class SpaceXEntity(val links: LinksEntity?,
                        val name: String?,
                        val cores: List<CoresEntity>?,
                        val success: Boolean?,
                        val date_utc: String?)



class LinksEntity(val patch: LinksPatchEntity?)

class LinksPatchEntity(val small: String?,
                            val large: String?)

class CoresEntity(val flight: Int?)


