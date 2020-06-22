package com.briangalarza.mascotas.view.tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet
import com.briangalarza.mascotas.view.tab.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayout


class Pet : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet)


        val bundle = intent.getParcelableExtra<Pet>("Bundle")


        val sectionsPagerAdapter =
            SectionsPagerAdapter(
                this,
                supportFragmentManager
            ,bundle!!)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)






    }
}