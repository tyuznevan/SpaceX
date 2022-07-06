package com.example.testcenter.SpaceX.data.remote.space

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET


interface SpaceXApi {

    @GET("launches")
    fun getSpaceXList(): Single<ArrayList<SpaceXEntity>>

    @GET("crew")
    fun getCrewList(): Single<ArrayList<CrewEntity>>

}