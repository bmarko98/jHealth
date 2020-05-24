package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * User Info Class
 * contains the variables for user's input
 * variables are initialized
 */

open class UserInfo() : RealmObject(), Parcelable {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var age: Int = 0
    var gender: String = ""
    var weight: Int = 0
    var wakeUpTime: Date? = Date()
    var sleepTime: Date? = Date()
    var drinkCupSize: Int = 250
    var workoutDuration: Int = 0
    var isTakingMed: Boolean = false

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        name = parcel.readString().toString()
        age = parcel.readInt()
        gender = parcel.readString().toString()
        weight = parcel.readInt()
        drinkCupSize = parcel.readInt()
        workoutDuration = parcel.readInt()
        isTakingMed = parcel.readByte() != 0.toByte()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(age)
        parcel.writeString(gender)
        parcel.writeInt(weight)
        parcel.writeInt(drinkCupSize)
        parcel.writeInt(workoutDuration)
        parcel.writeByte(if (isTakingMed) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserInfo> {
        override fun createFromParcel(parcel: Parcel): UserInfo {
            return UserInfo(parcel)
        }

        override fun newArray(size: Int): Array<UserInfo?> {
            return arrayOfNulls(size)
        }
    }
}