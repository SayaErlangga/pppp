package com.example.tugas8

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.tugaspertemuan8p3b.DataPassListener
import com.example.tugaspertemuan8p3b.DataPassListenerLogin
import com.example.tugaspertemuan8p3b.R
import com.example.tugaspertemuan8p3b.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), DataPassListener, DataPassListenerLogin {
    lateinit var viewPager2: ViewPager2
    var registerUsername = ""
    var registerPassword = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){

            viewPager.adapter = TabAdapter(this@MainActivity)
            viewPager2 = viewPager

            TabLayoutMediator(tabLayout, viewPager) {
                    tab, position->
                tab.text = when(position) {
                    0 -> "Register"
                    1 -> "Login"
                    else -> ""
                }
            }.attach()

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDataPass(username: String, password: String) {
        registerUsername = username
        registerPassword = password

        viewPager2.setCurrentItem(1)
    }

    override fun onDataPassLogin(username: String, password: String) {
        if (registerUsername == username && registerPassword == password) {
            Toast.makeText(this, "Login Berhasil", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login Gagal", Toast.LENGTH_SHORT).show()
        }
    }
}