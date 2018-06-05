package ru.iteye.androidlivecourseapp.data.database.firebasedatabase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import ru.iteye.androidlivecourseapp.repositories.listeners.TaskDatabaseListener


class FirebaseDatabase {

    companion object {
        val TAG = "*** FirebaseDatabase"
    }

    var db = FirebaseFirestore.getInstance()

    fun write(collection: String, data: Map<String, Any>, listener: TaskDatabaseListener) {
        // Add a new document with a generated ID
        db.collection(collection)
                .add(data)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, " -> query: DocumentSnapshot added with ID: " + documentReference.id)
                    listener.onWriteSuccess(documentReference.id.toString())
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, " -> query: Error adding document", e)
                    listener.onError(e)
                }
    }

    fun read(collection: String, listener: TaskDatabaseListener){
        db.collection(collection)
                .get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.d(TAG, document.id + " => " + document.data)
                            val result = HashMap<String, Any>()
                            result[document.id] = document.data
                            listener.onReadSuccess(result)
                        }
                    } else {
                        Log.w(TAG, "Error getting documents.", task.exception)
                        listener.onError(task.exception)
                    }
                }
    }



}