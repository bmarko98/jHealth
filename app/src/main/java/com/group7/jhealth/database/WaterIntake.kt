package com.group7.jhealth.database

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class WaterIntake(
    @PrimaryKey var id: Int = 0,
    var intakeAmount: Int = 0,
    var time: Date = Date(),
    var iconId: Int = 0
) : RealmObject()

