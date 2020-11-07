package com.example.android.convunit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //lateinit var -> I promise to later initialize things
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var lengthFragment: LengthFragment
    private lateinit var speedFragment: Speed_Fragment
    private lateinit var aboutFragment: About_Fragment
    private lateinit var weightFragment: Weight_Fragment
    private lateinit var forceFragment: Force_Fragment
    private lateinit var temperatureFragment: Temperature_Fragment
    private lateinit var pressureFragment: Pressure_Fragment
    private lateinit var areaFragment: Area_Fragment
    private lateinit var volumeFragment: Volume_Fragment




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState() //we tell our toggle that it is ready to be used

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //when our drawer opens the logo changes to left arrow



        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.length -> //
                {
                    Toast.makeText(applicationContext, "Length", Toast.LENGTH_SHORT) .show()
                    lengthFragment= LengthFragment()
                    addFragmentToActivity(lengthFragment)
                }
                R.id.speed -> //
                {
                    Toast.makeText(applicationContext, "Speed", Toast.LENGTH_SHORT).show()
                    speedFragment= Speed_Fragment()
                    addFragmentToActivity(speedFragment)
                }
                R.id.weight -> //
                {
                    Toast.makeText(applicationContext, "Weight", Toast.LENGTH_SHORT).show()
                    weightFragment= Weight_Fragment()
                    addFragmentToActivity(weightFragment)
                }
                R.id.temperature -> //
                {
                    Toast.makeText(applicationContext, "Temperature", Toast.LENGTH_SHORT).show()
                    temperatureFragment= Temperature_Fragment()
                    addFragmentToActivity(temperatureFragment)
                }
                R.id.pressure -> //
                {
                    Toast.makeText(applicationContext, "Pressure", Toast.LENGTH_SHORT).show()
                    pressureFragment= Pressure_Fragment()
                    addFragmentToActivity(pressureFragment)
                }
                R.id.force -> //
                {
                    Toast.makeText(applicationContext, "Force", Toast.LENGTH_SHORT).show()
                    forceFragment= Force_Fragment()
                    addFragmentToActivity(forceFragment)
                }
                R.id.area ->//
                {
                    Toast.makeText(applicationContext, "Area", Toast.LENGTH_SHORT).show()
                    areaFragment= Area_Fragment()
                    addFragmentToActivity(areaFragment)
                }
                R.id.volume ->
                {
                    Toast.makeText(applicationContext, "Volume", Toast.LENGTH_SHORT).show()
                    volumeFragment= Volume_Fragment()
                    addFragmentToActivity(volumeFragment)
                }
                R.id.about ->
                    {
                        //Toast.makeText(applicationContext, "Volume", Toast.LENGTH_SHORT).show()
                        aboutFragment= About_Fragment()
                        addFragmentToActivity(aboutFragment)
                    }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun addFragmentToActivity(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment,fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
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

