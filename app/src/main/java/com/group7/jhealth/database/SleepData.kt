package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SleepData(): Parcelable, RealmObject() {
    @PrimaryKey
    var id: Long = 0L
    var happySleepCtr: Int = 0
    var mehSleepCtr: Int = 0
    var sadSleepButton: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        happySleepCtr = parcel.readInt()
        mehSleepCtr = parcel.readInt()
        sadSleepButton = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(happySleepCtr)
        parcel.writeInt(mehSleepCtr)
        parcel.writeInt(sadSleepButton)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SleepData> {
        override fun createFromParcel(parcel: Parcel): SleepData {
            return SleepData(parcel)
        }

        override fun newArray(size: Int): Array<SleepData?> {
            return arrayOfNulls(size)
        }
    }
}