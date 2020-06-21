package com.briangalarza.mascotas.view.tab

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.briangalarza.mascotas.R
import com.briangalarza.mascotas.model.Pet


private val TAB_TITLES = arrayOf(
    R.string.photo,
    R.string.details
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, val pet: Pet) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        when (position){
            0 -> return PhotoFragment.newInstance(pet)
            1 -> return PhotoFragment.newInstance(pet)
            else -> return PhotoFragment.newInstance(pet)
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 2
    }
}