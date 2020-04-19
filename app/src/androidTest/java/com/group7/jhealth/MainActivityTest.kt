package com.group7.jhealth

import android.view.View
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule @JvmField
    var mActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    @Test
    fun testLaunch() {
        var mActivity = mActivityTestRule.activity
        val view: View = mActivity.findViewById(R.id.nav_view)
        assertNotNull(view)
    }

}