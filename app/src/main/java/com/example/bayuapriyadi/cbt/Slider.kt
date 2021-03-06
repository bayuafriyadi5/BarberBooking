package com.example.bayuapriyadi.cbt

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.bayuapriyadi.cbt.R.id.SaveHairbtn
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_slider.*
import kotlinx.android.synthetic.main.fragment_slider.*
import kotlinx.android.synthetic.main.fragment_slider.view.*

class Slider : AppCompatActivity() {



    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */



    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slider)


        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter




    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_slider, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 5
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {



        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {


            val rootView = inflater.inflate(R.layout.fragment_slider, container, false)

            rootView.SaveHairbtn.setOnClickListener{
                val progressDialog = ProgressDialog(context,
                        R.style.Theme_MaterialComponents_Light_Dialog)
                progressDialog.isIndeterminate = true
                progressDialog.setMessage("Prossessss...")
                progressDialog.show()
                val intent = Intent(activity, Dashboard::class.java)
                startActivity(intent)
            }

            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 1){
                rootView.tv.text = "Swipe for Choose Your Hairstyle"
                rootView.image_iv.setImageResource(R.drawable.hair1)
                rootView.hairname.text = "Textured Haircut"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 2){
                rootView.tv.text = "Swipe for Choose Your Hairstyle"
                rootView.image_iv.setImageResource(R.drawable.hair2)
                rootView.hairname.text = "Side Part Hairstyle"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 3){
                rootView.tv.text = "Swipe for Choose Your Hairstyle"
                rootView.image_iv.setImageResource(R.drawable.hair3)
                rootView.hairname.text = "Short Textured Quiff Haircut + Mid Fade"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 4){
                rootView.tv.text = "Swipe for Choose Your Hairstyle"
                rootView.image_iv.setImageResource(R.drawable.hair4)
                rootView.hairname.text = "Low Fade Haircut + Side Part"
            }
            if (arguments!!.getInt(ARG_SECTION_NUMBER) == 5){
                rootView.tv.text = "Swipe for Choose Your Hairstyle"
                rootView.image_iv.setImageResource(R.drawable.hair5)
                rootView.hairname.text = "High Fade Quiff Hairstyle"
            }
            return rootView


        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"


            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }

        }

    }


}
