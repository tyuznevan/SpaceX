package com.example.testcenter.ui.spaceXList

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import com.example.testcenter.data.api.SpaceXRetrofit
import com.example.testcenter.data.entities.CrewEntity
import com.example.testcenter.space.SpaceXEntity
import com.example.testcenter.utils.SingleLiveEvent
import com.example.testcenter.utils.Utils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SpaceXListViewModel(application: Application) : AndroidViewModel(application) {

    val spaceXLiveData = SingleLiveEvent<List<SpaceXEntity>>()
    val navigateToCrewScreen = SingleLiveEvent<Bundle>()
    var crewData: ArrayList<CrewEntity>? = null
    val year = "2021"

    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
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
            (it.dateYear(it.date_utc) >= year) ?: false
        }.sortedByDescending { it.date_utc }

    fun fetchCrewList(position: Int) {
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


    fun makeArguments(position: Int): Bundle {
        //TODO
        val bundle = Bundle()
        bundle.putString(LOGO_KEY, spaceXLiveData.value?.get(position)?.links?.patch?.large)
        bundle.putString("Name", spaceXLiveData.value?.get(position)?.name)
        bundle.putString(
            "Flight",
            spaceXLiveData.value?.get(position)?.cores?.get(0)?.flight.toString()
        )
        bundle.putString("Success", spaceXLiveData.value?.get(position)?.success.toString())
        bundle.putString("Date", Utils.getUtcDate(spaceXLiveData.value?.get(position)?.date_utc.toString()))
        bundle.putString("Details", spaceXLiveData.value?.get(position)?.details)

        return bundle
    }
    companion object{
        val LOGO_KEY = "LOGO_KEY"
    }

}