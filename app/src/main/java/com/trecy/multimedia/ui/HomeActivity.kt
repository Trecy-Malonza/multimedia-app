package com.trecy.multimedia.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.trecy.multimedia.R
import com.trecy.multimedia.databinding.ActivityHomeBinding
import com.trecy.multimedia.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        }

    override fun onResume() {
        super.onResume()
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.home ->{
                    loadFragment(HomeFragment())
                }
                R.id.games ->{
                    loadFragment(GamesFragment())
                }
                R.id.profile->{
                    loadFragment(ProfileFragment())

                }
                R.id.settings ->{
                    val settingsFragment= SettingsFragment()
                    loadFragment(settingsFragment)

                }
                else -> {
                    loadFragment(HomeFragment())
                }

            }

        }
    }
    fun loadFragment(fragment: Fragment): Boolean{
        val fragmentTransaction =supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fcvHome,fragment)
        fragmentTransaction.commit()
        return true

    }
    }
