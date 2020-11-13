package com.example.android.convunit

import android.media.MediaPlayer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.android.convunit.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    //lateinit var -> I promise to later initialize things
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var lengthFragment: LengthFragment
    private lateinit var speedFragment: SpeedFragment
    private lateinit var aboutFragment: About_Fragment
    private lateinit var weightFragment: WeightFragment
    private lateinit var forceFragment: Force_Fragment
    private lateinit var temperatureFragment: Temperature_Fragment
    private lateinit var pressureFragment: Pressure_Fragment
    private lateinit var areaFragment: Area_Fragment
    private lateinit var volumeFragment: Volume_Fragment
    private lateinit var homeFragment: Home_Fragment
    private lateinit var length: ExtendedFloatingActionButton
    private lateinit var speed: ExtendedFloatingActionButton
    private lateinit var weight: ExtendedFloatingActionButton
    private lateinit var force: ExtendedFloatingActionButton
    private lateinit var logon: MediaPlayer
    private lateinit var logoff: MediaPlayer
    //private lateinit var actionBar: ActionBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState() //we tell our toggle that it is ready to be used

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //when our drawer opens the logo changes to left arrow
        length = findViewById(R.id.length)
        speed = findViewById(R.id.speed)
        weight = findViewById(R.id.weight)
        force = findViewById(R.id.force)

        length.setOnClickListener {
            Toast.makeText(applicationContext, "Length", Toast.LENGTH_SHORT).show()
            supportActionBar?.title = "Length"
            lengthFragment = LengthFragment()
            addFragmentToActivity(lengthFragment)
        }

        speed.setOnClickListener {
            Toast.makeText(applicationContext, "Speed", Toast.LENGTH_SHORT).show()
            supportActionBar?.title = "Speed"
            speedFragment = SpeedFragment()
            addFragmentToActivity(speedFragment)
        }

        weight.setOnClickListener {
            Toast.makeText(applicationContext, "Weight", Toast.LENGTH_SHORT).show()
            supportActionBar?.title = "Weight"
            weightFragment = WeightFragment()
            addFragmentToActivity(weightFragment)
        }

        force.setOnClickListener {
            Toast.makeText(applicationContext, "Force", Toast.LENGTH_SHORT).show()
            supportActionBar?.title = "Force"
            forceFragment = Force_Fragment()
            addFragmentToActivity(forceFragment)
        }


        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.length -> //
                {

                    Toast.makeText(applicationContext, "Length", Toast.LENGTH_SHORT).show()
                    supportActionBar?.title = "Length"
                    lengthFragment = LengthFragment()
                    addFragmentToActivity(lengthFragment)
                }
                R.id.speed -> //
                {
                    Toast.makeText(applicationContext, "Speed", Toast.LENGTH_SHORT).show()
                    supportActionBar?.title = "Speed"
                    speedFragment = SpeedFragment()
                    addFragmentToActivity(speedFragment)
                }
                R.id.weight -> //
                {
                    Toast.makeText(applicationContext, "Weight", Toast.LENGTH_SHORT).show()
                    supportActionBar?.title = "Weight"
                    weightFragment = WeightFragment()
                    addFragmentToActivity(weightFragment)
                }
                R.id.temperature -> //
                {
                    Toast.makeText(applicationContext, "Temperature", Toast.LENGTH_SHORT).show()
                    temperatureFragment = Temperature_Fragment()
                    addFragmentToActivity(temperatureFragment)
                }
                R.id.pressure -> //
                {
                    Toast.makeText(applicationContext, "Pressure", Toast.LENGTH_SHORT).show()
                    pressureFragment = Pressure_Fragment()
                    addFragmentToActivity(pressureFragment)
                }
                R.id.force -> //
                {
                    Toast.makeText(applicationContext, "Force", Toast.LENGTH_SHORT).show()
                    forceFragment = Force_Fragment()
                    addFragmentToActivity(forceFragment)
                }
                R.id.area ->//
                {
                    Toast.makeText(applicationContext, "Area", Toast.LENGTH_SHORT).show()
                    areaFragment = Area_Fragment()
                    addFragmentToActivity(areaFragment)
                }
                R.id.volume -> {
                    Toast.makeText(applicationContext, "Volume", Toast.LENGTH_SHORT).show()
                    volumeFragment = Volume_Fragment()
                    addFragmentToActivity(volumeFragment)
                }
                R.id.about -> {
                    //Toast.makeText(applicationContext, "Volume", Toast.LENGTH_SHORT).show()
                    logon = MediaPlayer.create(this, R.raw.logon)
                    logon.start()
                    aboutFragment = About_Fragment()
                    addFragmentToActivity(aboutFragment)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun clearFragmentsFromContainer() {
        val fragments = supportFragmentManager.fragments
        if (fragments != null) {
            for (fragment in fragments) {
                supportFragmentManager.beginTransaction().remove(fragment).commit()
            }
        } else {
            Toast.makeText(this, "All frags cleared", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homeIcon -> {
                //Toast.makeText(this, "Yo boii", Toast.LENGTH_SHORT).show()
                logoff = MediaPlayer.create(this, R.raw.logoff)
                logoff.start()
                supportActionBar?.title = "Convit"
                mainframe.visibility = View.INVISIBLE
                homeLayout.visibility = View.VISIBLE
                clearFragmentsFromContainer()
                true
            }
            R.id.aboutUs -> {
                supportActionBar?.title = "Convit"
                homeLayout.visibility = View.INVISIBLE
                mainframe.visibility = View.VISIBLE
                aboutFragment = About_Fragment()
                addFragmentToActivity(aboutFragment)
                logon = MediaPlayer.create(this, R.raw.logon)
                logon.start()
                //Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    private fun addFragmentToActivity(fragment: Fragment) {
        homeLayout.visibility = View.INVISIBLE
        mainframe.visibility = View.VISIBLE
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainframe, fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onBackPressed() {

        if (drawer_layout.isDrawerOpen(GravityCompat.START))
        {
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else{
            super.onBackPressed()
        }
    }
}
