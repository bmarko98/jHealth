package com.group7.jhealth

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.group7.jhealth.database.UserInfo
import com.group7.jhealth.database.WaterIntake
import com.group7.jhealth.dialogs.DrinkCupSizeDialog
import com.group7.jhealth.fragments.*
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener, DrinkCupSizeDialog.DrinkCupSizeDialogListener,
    OnIntakeLongClickListener, WaterTrackerFragment.WaterTrackerFragmentListener {

    private lateinit var preferences: SharedPreferences
    private lateinit var preferencesEditor: SharedPreferences.Editor
    private lateinit var realm: Realm
    private var isInMenu = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = this.getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val homeFragment = HomeFragment()
                    show(homeFragment)
                }
                R.id.nav_diet_monitoring -> {
                    val dietMonitoringFragment = DietMonitoringFragment()
                    show(dietMonitoringFragment)
                }
                R.id.nav_sleep_monitoring -> {
                    val sleepMonitoringFragment = SleepMonitoringFragment()
                    show(sleepMonitoringFragment)
                }
                R.id.nav_water_tracker -> {
                    val waterTrackerFragment = WaterTrackerFragment()
                    waterTrackerFragment.updateIntakeHistory(realm.where<WaterIntake>().findAll())
                    show(waterTrackerFragment)
                }
                R.id.nav_workout_plan -> {
                    val workoutPlanFragment = WorkoutPlanFragment()
                    show(workoutPlanFragment)
                }
            }
            true
        }

        //File(this.filesDir.path).deleteRecursively()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name(REALM_CONFIG_FILE_NAME).build()
        Realm.setDefaultConfiguration(config)
        realm = Realm.getDefaultInstance()

        if (preferences.getBoolean(KEY_PREF_IS_FIRST_LAUNCH, true)) {
            preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(KEY_PREF_IS_FIRST_LAUNCH, false)
            preferencesEditor.apply()
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_navigate_to_login_form_fragment)
        }
    }

    private fun show(fragment: Fragment) {
        val fragmentManager = supportFragmentManager

        findViewById<FrameLayout>(R.id.nav_host_fragment).removeAllViews()
        //workaround for the problem of 2 fragments being on top of each other

        fragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onClickListener(clickedItemId: Int) {
        when (clickedItemId) {
            R.id.nav_home -> {
                val homeFragment = HomeFragment()
                show(homeFragment)
            }
            R.id.nav_diet_monitoring -> {
                val dietMonitoringFragment = DietMonitoringFragment()
                show(dietMonitoringFragment)
            }
            R.id.nav_sleep_monitoring -> {
                val sleepMonitoringFragment = SleepMonitoringFragment()
                show(sleepMonitoringFragment)
            }
            R.id.nav_water_tracker -> {
                val waterTrackerFragment = WaterTrackerFragment()
                waterTrackerFragment.updateIntakeHistory(realm.where<WaterIntake>().findAll())
                show(waterTrackerFragment)
            }
            R.id.nav_workout_plan -> {
                val workoutPlanFragment = WorkoutPlanFragment()
                show(workoutPlanFragment)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val preferencesFragment = PreferencesFragment()
                show(preferencesFragment)
            }
            R.id.action_update_user_info -> {
                val loginFormFragment = LoginFormFragment()
                show(loginFormFragment)
            }
        }
        isInMenu = true
        return super.onOptionsItemSelected(item)
    }

    override fun drinkCupSizeDialogListener(chosenSize: Int) {
        realm.beginTransaction()
        val userInfo = realm.where<UserInfo>().findFirst()
        userInfo?.drinkCupSize = chosenSize
        realm.commitTransaction()
    }

    override fun onLongClickWaterIntakeRecyclerViewItem(intake: WaterIntake) {
        Log.e("Long clicked", intake.intakeAmount.toString() + " " + intake.time)
    }

    override fun onAddDrinkingCupButtonClicked() {
        DrinkCupSizeDialog().show(supportFragmentManager, "")
    }

    override fun addWaterIntakeToDatabase() {
        realm.beginTransaction()
        val waterIntake: WaterIntake = realm.createObject<WaterIntake>((realm.where<WaterIntake>().findAll().size) + 1)
        waterIntake.intakeAmount = realm.where<UserInfo>().findFirst()?.drinkCupSize!!
        waterIntake.time = Calendar.getInstance().time
        waterIntake.iconId = getCupIcon(waterIntake.intakeAmount)
        realm.commitTransaction()
    }

    private fun getCupIcon(intakeAmount: Int): Int {
        return when (intakeAmount) {
            50, 100, 150 -> R.drawable.tea_cup_icon
            200, 250, 300 -> R.drawable.water_glass_icon
            350, 400, 450 -> R.drawable.small_water_bottle_icon
            500, 550, 600 -> R.drawable.water_bottle_icon
            650, 700, 750, 800, 1000, 1500 -> R.drawable.large_water_bottle_icon
            else -> R.drawable.custom_cup_icon
        }
    }

    override fun onBackPressed() {
        if (isInMenu) {
            isInMenu = false
            val homeFragment = HomeFragment()
            show(homeFragment)
        } else
            super.onBackPressed()
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}