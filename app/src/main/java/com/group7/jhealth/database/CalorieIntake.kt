package com.group7.jhealth.database

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class CalorieIntake() : Parcelable, RealmObject(){
    @PrimaryKey
    var id: Int = 0
    var calorie: Int = 0
    var time: Date = Date()
    var foodName: String=""

    constructor(parcel: Parcel) : this() {
        id = parcel.readInt()
        calorie = parcel.readInt()
        foodName = parcel.readString().toString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeInt(calorie)
        parcel.writeString(foodName)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CalorieIntake> {
        override fun createFromParcel(parcel: Parcel): CalorieIntake {
            return CalorieIntake(parcel)
        }

        override fun newArray(size: Int): Array<CalorieIntake?> {
            return arrayOfNulls(size)
        }
    }
}