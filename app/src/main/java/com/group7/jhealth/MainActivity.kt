package com.group7.jhealth

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.group7.jhealth.fragments.HomeFragment
import io.realm.Realm
import io.realm.RealmConfiguration

class MainActivity : AppCompatActivity(), HomeFragment.HomeFragmentListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var fragmentManager: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

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
}
