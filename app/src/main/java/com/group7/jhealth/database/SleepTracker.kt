package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SleepTracker(): Parcelable, RealmObject() {
    @PrimaryKey
    var sleepID: Long = 0L
    var startTimeMilli: Long = 0L
    var endTimeMilli: Long = 0L
    var sleepQuality: Int = -1

    constructor(parcel: Parcel) : this() {
        sleepID = parcel.readLong()
        startTimeMilli = parcel.readLong() //System.currentTimeMillis()
        endTimeMilli = parcel.readLong() //startTimeMilli
        sleepQuality = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(sleepID)
        parcel.writeLong(sleepID)
        parcel.writeLong(sleepID)
        parcel.writeInt(sleepQuality)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SleepTracker> {
        override fun createFromParcel(parcel: Parcel): SleepTracker {
            return SleepTracker(parcel)
        }

        override fun newArray(size: Int): Array<SleepTracker?> {
            return arrayOfNulls(size)
        }
    }

}