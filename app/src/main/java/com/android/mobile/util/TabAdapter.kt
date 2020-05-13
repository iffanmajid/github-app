package com.android.mobile.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import java.util.*

/**
 * Created by Iffan Majid on 21/01/2019.
 */
class TabAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
        notifyDataSetChanged()
    }

    fun clear() {
        fragmentList.clear()
        titleList.clear()
        notifyDataSetChanged()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleList[position]
    }
}