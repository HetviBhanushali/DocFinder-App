package com.example.docfinderv2

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseMsgService: FirebaseMessagingService() {

//    override fun onRegistered(installationId: String) { //onNewToken
//        super.onRegistered(installationId)
//        Log.d("FirebaseMsgService", "Refreshed Token: $installationId")  //keep it updated by user profile so to identify multiple profiles
//    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("FirebaseMsgService", "Message Received: ${message.notification?.body}")
    }
}