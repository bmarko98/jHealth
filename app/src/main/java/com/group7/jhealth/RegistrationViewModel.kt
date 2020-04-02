package com.group7.jhealth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class RegistrationViewModel : ViewModel() {

    enum class RegistrationState {
        COLLECT_PROFILE_DATA,
        REGISTRATION_COMPLETED
    }

    private val registrationState = MutableLiveData<RegistrationState>(RegistrationState.COLLECT_PROFILE_DATA)
    private lateinit var realm: Realm

    fun collectProfileData(name: String, age: Int, gender: String, weight: Int, wakeUpTime: Date, sleepTime: Date) {
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val user: UserInfo = realm.createObject<UserInfo>((realm.where<UserInfo>().findAll().size) + 1)
        user.name = name
        user.age = age
        user.gender = gender
        user.weight = weight
        user.wakeUpTime = wakeUpTime
        user.sleepTime = sleepTime
        realm.commitTransaction()
    }

    fun userCancelledRegistration(): Boolean {
        //TODO: If the user cancels this, just use average values without asking any info
        // Clear existing registration data
        registrationState.value = RegistrationState.COLLECT_PROFILE_DATA
        return true
    }
}