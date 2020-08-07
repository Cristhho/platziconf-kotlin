package dev.cristhhq.platziconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import dev.cristhhq.platziconf.model.Conference
import dev.cristhhq.platziconf.model.Speaker

const val CONFERENCES = "conferences"
const val SPEAKERS = "speakers"

class FirestoreService {
    val firestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firestore.firestoreSettings = settings
    }

    fun getSpeakers(callback: Callback<List<Speaker>>) {
        firestore.collection(SPEAKERS).orderBy("category").get()
            .addOnSuccessListener { result ->
                for(doc in result) {
                    val list = result.toObjects(Speaker::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }

    fun getSchedule(callback: Callback<List<Conference>>) {
        firestore.collection(CONFERENCES).get()
            .addOnSuccessListener { result ->
                for(doc in result) {
                    val list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
            .addOnFailureListener { exception -> callback.onFailed(exception) }
    }
}