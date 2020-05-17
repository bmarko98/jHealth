package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

/**
 * Water Intake class
 * contains the properties for when processing the user's intake
 * variables are initialized
 */
open class WaterIntake() : Parcelable, RealmObject(){
    @PrimaryKey
    var id: Int = 0
    var intakeAmount: Int = 0
    var time: Date = Date()
    var iconId: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        intakeAmount = parcel.readInt()
        iconId = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(intakeAmount)
        parcel.writeInt(iconId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WaterIntake> {
        override fun createFromParcel(parcel: Parcel): WaterIntake {
            return WaterIntake(parcel)
        }

        override fun newArray(size: Int): Array<WaterIntake?> {
            return arrayOfNulls(size)
        }
    }
}

