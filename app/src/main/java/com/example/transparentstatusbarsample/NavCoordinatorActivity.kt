package com.example.transparentstatusbarsample

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_nav_coordinator.*
import kotlinx.android.synthetic.main.app_bar_nav.*
import kotlinx.android.synthetic.main.nav_header.view.*

class NavCoordinatorActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var mToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_coordinator)
        setSupportActionBar(toolbar)

        mToggle = ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(mToggle)
        mToggle.syncState()

        navigationView.setNavigationItemSelectedListener(this)

        updateHeader(navigationView.getHeaderView(0))
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_favorite -> {
            }
            R.id.nav_fingerprint -> {
            }
            R.id.nav_setting -> {
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun updateHeader(rootView: View) {
        Glide.with(rootView.nav_header_image.context)
                .load(Utils.URL_IMAGE_DEFAULT)
                .apply(RequestOptions.circleCropTransform())
                .into(rootView.nav_header_image)
        rootView.nav_header_email.text = "thaik59uet@gmail.com"
        rootView.nav_header_name.text = "Thaihn2"
    }
}
