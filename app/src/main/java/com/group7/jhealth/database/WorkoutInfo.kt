package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class WorkoutInfo() : Parcelable, RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var weightAmount: Int = 0
    var numberOfReps: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        weightAmount = parcel.readInt()
        numberOfReps = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(weightAmount)
        parcel.writeInt(numberOfReps)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WorkoutInfo> {
        override fun createFromParcel(parcel: Parcel): WorkoutInfo {
            return WorkoutInfo(parcel)
        }

        override fun newArray(size: Int): Array<WorkoutInfo?> {
            return arrayOfNulls(size)
        }
    }
}