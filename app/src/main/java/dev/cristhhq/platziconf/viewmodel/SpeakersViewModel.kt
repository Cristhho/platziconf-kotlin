package dev.cristhhq.platziconf.viewmodel

import androidx.lifecycle.MutableLiveData
import dev.cristhhq.platziconf.model.Speaker
import dev.cristhhq.platziconf.network.Callback
import dev.cristhhq.platziconf.network.FirestoreService
import java.lang.Exception

class SpeakersViewModel {
    val firestoreService = FirestoreService()
    var listSpeakers: MutableLiveData<List<Speaker>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh() {
        getSpeakersFromFirebase()
    }

    fun getSpeakersFromFirebase() {
        firestoreService.getSpeakers(object: Callback<List<Speaker>> {
            override fun onSuccess(result: List<Speaker>?) {
                listSpeakers.postValue(result)
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }

    fun processFinished() {
        isLoading.value = true
    }
}