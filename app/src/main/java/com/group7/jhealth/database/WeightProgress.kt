package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class WeightProgress() : Parcelable, RealmObject(){
    @PrimaryKey
    var id: Int = 0
    var weightAmount: Int = 0
    var time: Date = Date()

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        weightAmount = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(weightAmount)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WeightProgress> {
        override fun createFromParcel(parcel: Parcel): WeightProgress {
            return WeightProgress(parcel)
        }

        override fun newArray(size: Int): Array<WeightProgress?> {
            return arrayOfNulls(size)
        }
    }
}
