package com.example.testcenter.ui.spaceXList

import android.app.Application
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.AndroidViewModel
import com.example.testcenter.data.api.SpaceXRetrofit
import com.example.testcenter.data.entities.CrewEntity
import com.example.testcenter.data.entities.SpaceXEntity
import com.example.testcenter.ui.missionsAdapter.MissionsListAdapter
import com.example.testcenter.utils.SingleLiveEvent
import com.example.testcenter.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SpaceXListViewModel(application: Application) : AndroidViewModel(application) {

    val spaceXLiveData = SingleLiveEvent<List<SpaceXEntity>>()
    val navigateToCrewScreen = SingleLiveEvent<Bundle>()
    var crewData: ArrayList<CrewEntity>? = null
    private val year = "2021"

    var rotationState = false

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    val adapter = MissionsListAdapter { onClick(it) }

    private fun onClick(position: Int) {
        fetchCrewList(position)
    }

    fun fetchSpaceXList() {
        compositeDisposable.add(
            SpaceXRetrofit.getSpaceApi().getSpaceXList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    spaceXLiveData.value =
                        changeSpaceXData(it)
                }, {

                })
        )
    }

    private fun changeSpaceXData(data: List<SpaceXEntity>) =
        data.filter {
            (Utils.dateYear(it.date_utc) >= year) ?: false
        }.sortedByDescending { it.date_utc }

    private fun fetchCrewList(position: Int) {
        if (crewData == null) {
            compositeDisposable.add(
                SpaceXRetrofit.getSpaceApi().getCrewList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        crewData = it
                        navigateToCrewScreen.value = makeArguments(position)
                    }, {
                    })
            )
        } else {
            navigateToCrewScreen.value = makeArguments(position)
        }
    }


    private fun makeArguments(position: Int): Bundle {
        return bundleOf(
            LOGO_KEY to spaceXLiveData.value?.get(position)?.links?.patch?.large,
            NAME_KEY to spaceXLiveData.value?.get(position)?.name,
            FLIGHT_KEY to
                    spaceXLiveData.value?.get(position)?.cores?.get(0)?.flight,
            SUCCESS_KEY to Utils.makeSuccessLabel(spaceXLiveData.value?.get(position)?.success),
            DATE_KEY to Utils.getUtcHMDate(spaceXLiveData.value?.get(position)?.date_utc.toString()),
            DETAILS_KEY to spaceXLiveData.value?.get(position)?.details,
            CREWNAME_KEY to Utils.getCrewName(spaceXLiveData.value?.get(position)?.crew, crewData),
            CREWSTATUS_KEY to Utils.getCrewStatus(spaceXLiveData.value?.get(position)?.crew, crewData),
            CREWAGENCY_KEY to Utils.getCrewAgency(spaceXLiveData.value?.get(position)?.crew, crewData)
        )
    }

    companion object {
        const val LOGO_KEY = "LOGO_KEY"
        const val NAME_KEY = "NAME_KEY"
        const val FLIGHT_KEY = "FLIGHT_KEY"
        const val SUCCESS_KEY = "SUCCESS_KEY"
        const val DATE_KEY = "DATE_KEY"
        const val DETAILS_KEY = "DETAILS_KEY"
        const val CREWNAME_KEY = "CREWNAME_KEY"
        const val CREWSTATUS_KEY = "CREWSTATUS_KEY"
        const val CREWAGENCY_KEY = "CREWAGENCY_KEY"
    }

}