package com.po.kemon.features.main

import android.graphics.Typeface
import android.os.Bundle
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.android.mobile.R
import com.android.mobile.features.base.BaseActivity
import com.android.mobile.features.main.repositories.RepositoriesFragment
import com.android.mobile.features.main.users.UsersFragment
import com.android.mobile.util.TabAdapter
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity() {

    companion object {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent().inject(this)

        // init tab adapter
        val tabAdapter = TabAdapter(supportFragmentManager)
        tabAdapter.apply {
            addFragment(UsersFragment(), "Users")
            addFragment(RepositoriesFragment(), "Repositories")
        }
        mainViewPager.adapter = tabAdapter
        mainTab.setupWithViewPager(mainViewPager)

        // init tabstyle
        for (i in 0 until mainTab.tabCount) {
            val tab = mainTab.getTabAt(i)
            if (tab != null) {
                val tabTextView = TextView(this)
                tab.customView = tabTextView
                tabTextView.layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                tabTextView.text = tab.text
                // First tab is the selected tab, so if i==0 then set BOLD typeface
                if (i == 0) {
                    tabTextView.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                    tabTextView.setTypeface(null, Typeface.BOLD)
                } else {
                    tabTextView.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                    tabTextView.setTypeface(null, Typeface.NORMAL)
                }
            }
        }

        mainTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab) {}

            override fun onTabUnselected(p0: TabLayout.Tab) {
                val text = (p0.customView as TextView?)!!
                text.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                text.setTypeface(null, Typeface.NORMAL)
            }

            override fun onTabSelected(p0: TabLayout.Tab) {
                val text = (p0.customView as TextView?)!!
                text.setTextColor(ContextCompat.getColor(this@MainActivity, R.color.white))
                text.setTypeface(null, Typeface.BOLD)
            }

        })
    }

    override fun layoutId() = R.layout.activity_main
}