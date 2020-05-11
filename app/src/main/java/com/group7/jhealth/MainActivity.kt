package com.group7.jhealth

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
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


/**
 * Main Activity class
 * menu & navigation to all the fragments this app has to offer
 * login form and properties
 * database connection
 * @constructor Creates an empty group.
 *
 */
class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener,
    DrinkCupSizeDialog.DrinkCupSizeDialogListener,
    OnIntakeLongClickListener, WaterTrackerFragment.WaterTrackerFragmentListener, LoginFormFragment.LoginFormFragmentListener {

    private lateinit var preferences: SharedPreferences
    private lateinit var preferencesEditor: SharedPreferences.Editor
    private lateinit var realm: Realm
    private var isInMenu = false
    private lateinit var loginFormFragment: LoginFormFragment

    /**
     * Perform initialization of all fragments.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.
     *     <b><i>Note: Otherwise it is null.</i></b>
     *
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
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

        triggerHourlyNotification()
    }

    /**
     * Trigger hourly notifications.
     *
     */
    private fun triggerHourlyNotification() {
        /*
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar[Calendar.HOUR_OF_DAY] = 6
        calendar[Calendar.MINUTE] = 14
        calendar[Calendar.SECOND] = 1
        */
        val notifyIntent = Intent(this, BroadcastReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(this, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, /*calendar.timeInMillis*/ System.currentTimeMillis(),
            AlarmManager.INTERVAL_HOUR, pendingIntent
        )
    }

    /**
     * based on the chosen
     * @param fragment
     * the fragment manager navigates to the desired fragment
     */
    private fun show(fragment: Fragment) {
        val fragmentManager = supportFragmentManager

        findViewById<FrameLayout>(R.id.nav_host_fragment).removeAllViews()
        //workaround for the problem of 2 fragments being on top of each other

        fragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    /**
     *Inflate the menu
     *  this adds items to the action bar if it is present.
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed
     *         if you return false it will not be shown.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    /**
     * based on the option the user clicks -> redirects you to that page
     * @param clickedItemId represents the ID of the item
     * options to choose from:
     * Diet Monitoring
     * Sleep Monitoring
     * Water Tracking
     * Workout Planning
     */
    override fun onClickListener(clickedItemId: Int) {
        when (clickedItemId) {
            R.id.dietMonitoringTextView -> {
                val dietMonitoringFragment = DietMonitoringFragment()
                show(dietMonitoringFragment)
            }
            R.id.sleepMonitoringTextView -> {
                val sleepMonitoringFragment = SleepMonitoringFragment()
                show(sleepMonitoringFragment)
            }
            R.id.waterTrackerTextView -> {
                val waterTrackerFragment = WaterTrackerFragment()
                waterTrackerFragment.updateIntakeHistory(realm.where<WaterIntake>().findAll())
                waterTrackerFragment.setWaterIntakeTargetBarMax(calculateWaterConsumption())
                show(waterTrackerFragment)
            }
            R.id.workoutPlanTextView -> {
                val workoutPlanFragment = WorkoutPlanFragment()
                show(workoutPlanFragment)
            }
        }
    }

    /**
     * based on the ID of the item chosen
     * @param item represents the menu options the user can choose from:
     * settings(preferences) and update user info (login form)
     * @return  boolean Return false to allow normal menu processing to proceed, true to consume it here.
     *  @see #onCreateOptionsMenu
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_settings -> {
                val preferencesFragment = PreferencesFragment()
                show(preferencesFragment)
            }
            R.id.action_update_user_info -> {
                loginFormFragment = LoginFormFragment()
                show(loginFormFragment)
            }
        }
        isInMenu = true
        return super.onOptionsItemSelected(item)
    }

    /**
     *takes in the cup size that is chosen
     *@param chosenSize cup size
     */
    override fun drinkCupSizeDialogListener(chosenSize: Int) {
        realm.beginTransaction()
        val userInfo = realm.where<UserInfo>().findFirst()
        userInfo?.drinkCupSize = chosenSize
        realm.commitTransaction()
    }

    /**
     * action when button for water intake clicked
     */
    override fun onLongClickWaterIntakeRecyclerViewItem(intake: WaterIntake) {
        Log.e("Long clicked", intake.intakeAmount.toString() + " " + intake.time)
    }

    /**
     * action when button for adding a cup is clicked
     */
    override fun onAddDrinkingCupButtonClicked() {
        DrinkCupSizeDialog().show(supportFragmentManager, "")
    }

    /**
     * updates the Database with the selected water intake
     */
    override fun addWaterIntakeToDatabase() {
        realm.beginTransaction()
        val waterIntake: WaterIntake =
            realm.createObject<WaterIntake>((realm.where<WaterIntake>().findAll().size) + 1)
        waterIntake.intakeAmount = realm.where<UserInfo>().findFirst()?.drinkCupSize!!
        waterIntake.time = Calendar.getInstance().time
        waterIntake.iconId = getCupIcon(waterIntake.intakeAmount)
        realm.commitTransaction()
    }

    /**
     * Chooses the correct cup icon based on user input
     * @param intakeAmount selected amount of water intake
     * @return the appropriate cup size based on the intake amount
     */
    private fun getCupIcon(intakeAmount: Int): Int {
        return when (intakeAmount) {
            50, 100, 150 -> R.drawable.ic_tea_cup
            200, 250, 300 -> R.drawable.ic_water_glass
            350, 400, 450 -> R.drawable.ic_small_water_bottle
            500, 550, 600 -> R.drawable.ic_water_bottle
            650, 700, 750, 800, 1000, 1500 -> R.drawable.ic_large_water_bottle
            else -> R.drawable.ic_custom_cup
        }
    }

    /**
     * navigation between fragments (Menu, Home)
     * using the Back button
     */
    override fun onBackPressed() {
        if (isInMenu) {
            isInMenu = false
            val homeFragment = HomeFragment()
            show(homeFragment)
        } else
            super.onBackPressed()
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }

    /**
     * updates Realm with user's input
     * name
     * age
     * weight
     * wakeupTime
     * sleepTime
     * workoutDuration
     */
    override fun updateUserInfoDatabase(
        name: String,
        age: Int,
        gender: String,
        weight: Int,
        wakeUpTime: Date,
        sleepTime: Date,
        workoutDuration: Int
    ) {
        realm.beginTransaction()

        val user: UserInfo? = if (realm.where<UserInfo>().findFirst() != null) {
            realm.where<UserInfo>().findFirst()
        } else {
            realm.createObject<UserInfo>((realm.where<UserInfo>().findAll().size) + 1)
            realm.where<UserInfo>().findFirst()
        }
        user?.name = name
        user?.age = age
        user?.gender = gender
        user?.weight = weight
        user?.wakeUpTime = wakeUpTime
        user?.sleepTime = sleepTime
        user?.workoutDuration = workoutDuration
        realm.commitTransaction()
    }

    /**
     * updates user interface of LoginFormFragment
     */
    override fun updateUserInfoUI() {
        displayUserInfo()
    }

    /**
     * gathers user data from Realm
     * name
     * age
     * weight
     * wakeupTime
     * sleepTime
     */
    private fun displayUserInfo() {
        if (realm.where<UserInfo>().findFirst() != null) {
            val user: UserInfo? = realm.where<UserInfo>().findFirst()

            loginFormFragment.displayUserInfo(
                user!!.name,
                user.age,
                user.gender,
                user.weight,
                user.wakeUpTime!!,
                user.sleepTime!!,
                user.workoutDuration
            )
        }
    }

    /**
     * calculates daily water consumption target
     */
    private fun calculateWaterConsumption(): Double {
        var consumptionInLitre = 0.0

        if (realm.where<UserInfo>().findFirst() != null) {
            val user: UserInfo? = realm.where<UserInfo>().findFirst()

            consumptionInLitre = (user!!.weight / 23) + (WATER_NEED_FOR_ONE_MINUTE_EXERCISE * user.workoutDuration)

            if (user.gender == KEY_GENDER_MALE)
                consumptionInLitre += 1    //Men need more water than woman.
        }
        /*Age is not that important*/
        return consumptionInLitre
    }
}