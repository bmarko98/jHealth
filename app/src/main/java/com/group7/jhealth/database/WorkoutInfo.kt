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

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
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
