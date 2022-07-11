package com.example.testcenter.data.api

import com.example.testcenter.data.entities.CrewEntity
import com.example.testcenter.data.entities.SpaceXEntity
import io.reactivex.Single
import retrofit2.http.GET


interface SpaceXApi {

    @GET("launches")
    fun getSpaceXList(): Single<ArrayList<SpaceXEntity>>

    @GET("crew")
    fun getCrewList(): Single<ArrayList<CrewEntity>>

}