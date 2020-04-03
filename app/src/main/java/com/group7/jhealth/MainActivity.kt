package com.group7.jhealth

import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.group7.jhealth.*
import com.group7.jhealth.fragments.HomeFragment
import com.group7.jhealth.fragments.LoginFormFragment
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import java.util.*

class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener, LoginFormFragment.LoginFormFragmentListener {

    private lateinit var preferences: SharedPreferences
    private lateinit var preferencesEditor: SharedPreferences.Editor
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        preferences = this.getSharedPreferences(SHARED_PREF_FILE, MODE_PRIVATE)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_diet_monitoring,
                R.id.nav_sleep_monitoring,
                R.id.nav_water_tracker,
                R.id.nav_workout_plan
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //File(this.filesDir.path).deleteRecursively()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name(REALM_CONFIG_FILE_NAME).build()
        Realm.setDefaultConfiguration(config)

        if (preferences.getBoolean(KEY_PREF_IS_FIRST_LAUNCH, true)) {
            preferencesEditor = preferences.edit()
            preferencesEditor.putBoolean(KEY_PREF_IS_FIRST_LAUNCH, false)
            preferencesEditor.apply()
            findNavController(R.id.nav_host_fragment).navigate(R.id.action_global_navigate_to_login_form_fragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClickListener(clickedItemId: Int) {
        val navController = findNavController(R.id.nav_host_fragment)

        when (clickedItemId) {
            R.id.dietMonitoringTextView ->
                navController.navigate(R.id.action_global_navigate_to_diet_monitoring_fragment)
            R.id.sleepMonitoringTextView ->
                navController.navigate(R.id.action_global_navigate_to_sleep_monitoring_fragment)
            R.id.waterTrackerTextView ->
                navController.navigate(R.id.action_global_navigate_to_water_tracker_fragment)
            R.id.workoutPlanTextView ->
                navController.navigate(R.id.action_global_navigate_to_workout_plan_fragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        when (item.itemId) {
            R.id.action_settings -> navController.navigate(R.id.action_global_navigate_to_preferences_fragment)
            R.id.action_update_user_info -> navController.navigate(R.id.action_global_navigate_to_login_form_fragment)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSubmitLoginForm(name: String, age: Int, gender: String, weight: Int, wakeUpTime: Date, sleepTime: Date) {
        realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        val user: UserInfo = realm.createObject<UserInfo>((realm.where<UserInfo>().findAll().size) + 1)
        user.name = name
        user.age = age
        user.gender = gender
        user.weight = weight
        user.wakeUpTime = wakeUpTime
        user.sleepTime = sleepTime
        realm.commitTransaction()
    }
}