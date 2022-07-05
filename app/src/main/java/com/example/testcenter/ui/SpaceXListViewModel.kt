package com.example.testcenter.ui

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.testcenter.SpaceX.SpaceXApp
import com.example.testcenter.SpaceX.data.remote.space.SpaceXEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class SpaceXListViewModel(application: Application) : AndroidViewModel(application) {

    val spaceXLiveData = MutableLiveData<List<SpaceXEntity>>()
    private val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun fetchSpaceXList(requireContext: Context) {

        compositeDisposable.add(
            SpaceXApp.getSpaceApi().getSpaceXList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    val k = 0
                    spaceXLiveData.value = changeSpaceXData(it) as List<SpaceXEntity> /* = java.util.ArrayList<com.example.testcenter.SpaceX.data.remote.space.SpaceXEntity> */
                }, {
                    Toast.makeText(requireContext, "тРОЛЛИНГ", Toast.LENGTH_LONG).show()
                })
        )
    }

    fun dateYear(dateUtc: String): String {
        return  "${dateUtc[0]}${dateUtc[1]}${dateUtc[2]}${dateUtc[3]}"
    }

    private fun changeSpaceXData(arrayList: List<SpaceXEntity>) =
        arrayList.filter{
            (it.dateYear(it.date_utc) >= "2021") ?: false
        }.sortedByDescending { it.date_utc }


}