package com.example.android.convunit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
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
    private lateinit var homeFragment: Home_Fragment
    private lateinit var hello: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState() //we tell our toggle that it is ready to be used

        supportActionBar?.setDisplayHomeAsUpEnabled(true) //when our drawer opens the logo changes to left arrow

        hello = findViewById(R.id.hello)
        hello.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, "Length", Toast.LENGTH_SHORT).show()
            lengthFragment = LengthFragment()
            addFragmentToActivity(lengthFragment)
        })


        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.length -> //
                {
                    Toast.makeText(applicationContext, "Length", Toast.LENGTH_SHORT).show()
                    lengthFragment = LengthFragment()
                    addFragmentToActivity(lengthFragment)
                }
                R.id.speed -> //
                {
                    Toast.makeText(applicationContext, "Speed", Toast.LENGTH_SHORT).show()
                    speedFragment = Speed_Fragment()
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
                    aboutFragment = About_Fragment()
                    addFragmentToActivity(aboutFragment)
                }
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.list_actionbar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.homeIcon -> {
                Toast.makeText(this, "Yo boii", Toast.LENGTH_SHORT).show()
                mainFrame.visibility = View.INVISIBLE
                homeLayout.visibility = View.VISIBLE

                true
            }
            R.id.aboutUs -> {
                homeLayout.visibility = View.INVISIBLE
                mainFrame.visibility = View.VISIBLE
                homeFragment = Home_Fragment()
                addFragmentToActivity(homeFragment)
                Toast.makeText(this, "About Us", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    /*private fun addFragmentToActivity(fragment: Fragment)
    {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment,fragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }*/
    fun addFragmentToActivity(fragment: Fragment) {
        homeLayout.visibility = View.INVISIBLE
        mainFrame.visibility = View.VISIBLE
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFrame, fragment)
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
