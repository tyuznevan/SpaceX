package com.example.testcenter.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testcenter.SpaceX.SpaceXApp
import com.example.testcenter.SpaceX.data.remote.space.SpaceXEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SpaceXListViewModel(application: Application) : AndroidViewModel(application) {

    val spaceXLiveData = MutableLiveData<ArrayList<SpaceXEntity>>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchSpaceXList() {

        compositeDisposable.add(
            SpaceXApp.getSpaceApi().getSpaceXList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val k = 0
                    //changeSpaceXData(it)
                    spaceXLiveData.value = it
                }, {
                    val m = 0

                })
        )
    }

//    private fun changeSpaceXData(arrayList: ArrayList<SpaceXEntity>) {
//        arrayList.filter{
//            it.date_utc?.get(0)?.equals("1") ?: true
//        }
//    }

}