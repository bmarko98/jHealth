package com.group7.jhealth.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * User Info Class
 * contains the variables for user's input
 * variables are initialized
 */

open class UserInfo(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var age: Int = 0,
    var gender: String = "",
    var weight: Int = 0,
    var wakeUpTime: Date? = Date(),
    var sleepTime: Date? = Date(),
    var drinkCupSize: Int = 250,
    var workoutDuration: Int = 0
) : RealmObject()