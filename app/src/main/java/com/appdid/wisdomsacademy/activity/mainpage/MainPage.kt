package com.appdid.wisdomsacademy.activity.mainpage

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.TextInputEditText
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import com.appdid.wisdomsacademy.Adapter.Adapter
import com.appdid.wisdomsacademy.Model.Pojo
import com.appdid.wisdomsacademy.R
import kotlinx.android.synthetic.main.activity_main_page.*
import kotlinx.android.synthetic.main.app_bar_main_page.*
import kotlinx.android.synthetic.main.content_main_page.*
import kotlinx.android.synthetic.main.flash_card_layout_back.*
import kotlinx.android.synthetic.main.flash_card_layout_front.*
import kotlinx.android.synthetic.main.student_details.*
import java.util.ArrayList

class MainPage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    companion object {
        val MOBILE_NO = "+919321302424"
    }

    lateinit var mStrStudentName : String
    lateinit var mStrStudentSchoolName : String
    lateinit var mStrContactNo : String
    lateinit var mStrStudentStd : String

    var list = ArrayList<Pojo>()
    var listmaths = ArrayList<Pojo>()

    var linearLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        setSupportActionBar(toolbar)

        list.add(Pojo(R.drawable.logesh, "Logesh.M","82 %"))
        list.add(Pojo(R.drawable.sarfarz, "Sarfaraz.M","82 %"))
        list.add(Pojo(R.drawable.priti, "Priti.S","82 %"))
        list.add(Pojo(R.drawable.manav, "Manav.L","82 %"))
        list.add(Pojo(R.drawable.shivangi, "Shivangi.S","82 %"))
        list.add(Pojo(R.drawable.vaishali, "Vaishali.B","78 %"))
        list.add(Pojo(R.drawable.shashi, "Shashi.V","76 %"))
        list.add(Pojo(R.drawable.nikita, "Nikita.J","76 %"))


        val adapter = Adapter(this@MainPage, list)

        linearLayoutManager = LinearLayoutManager(this@MainPage)

        linearLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL

        sssctopperrecyclerlist.setHasFixedSize(true)

        sssctopperrecyclerlist.layoutManager = linearLayoutManager

        sssctopperrecyclerlist.adapter = adapter





        listmaths.add(Pojo(R.drawable.logoo, "Virat Kohli","70 %"))
        listmaths.add(Pojo(R.drawable.logoo, "Ms dhoni","80 %"))
        listmaths.add(Pojo(R.drawable.logoo, "Virat Kohli","90 %"))
        listmaths.add(Pojo(R.drawable.logoo, "Virat Kohli","50 %"))
        listmaths.add(Pojo(R.drawable.logoo, "Virat Kohli","70 %"))
        listmaths.add(Pojo(R.drawable.logoo, "Virat Kohli","70 %"))

        val adaptermaths = Adapter(this@MainPage, listmaths)

        linearLayoutManager = LinearLayoutManager(this@MainPage)

        linearLayoutManager!!.orientation = LinearLayoutManager.HORIZONTAL

        sscmathstopper.setHasFixedSize(true)

        sscmathstopper.layoutManager = linearLayoutManager

        sscmathstopper.adapter = adaptermaths



        val blink_animation = AnimationUtils.loadAnimation(this@MainPage,R.anim.apply_blink)

        apply_here_text.startAnimation(blink_animation)

        admission_open_text.startAnimation(blink_animation)


        apply_here_text.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this@MainPage)
            val mView = layoutInflater.inflate(R.layout.student_details,null)
            val mStudentName = mView.findViewById<TextInputEditText>(R.id.student_name)
            val mStudentContact = mView.findViewById<TextInputEditText>(R.id.student_contact_no)
            val mStudentSchoolName = mView.findViewById<TextInputEditText>(R.id.student_school_name)
            val mStudentStd = mView.findViewById<TextInputEditText>(R.id.student_std)
            val mSubmit = mView.findViewById<Button>(R.id.student_submit_button)
            val mClose = mView.findViewById<ImageButton>(R.id.student_details_close_button)

            mSubmit.setOnClickListener {
                if(TextUtils.isEmpty(mStudentName.text.toString()))
                {
                    student_name.error = " Please enter name."
                    return@setOnClickListener
                }
                if(TextUtils.isEmpty(mStudentContact.text.toString()))
                {
                    student_contact_no.error = " Please enter contact no."
                    return@setOnClickListener
                }
                if(TextUtils.isEmpty(mStudentSchoolName.text.toString()))
                {
                    student_school_name.error = " Please enter school name."
                    return@setOnClickListener
                }
                if(TextUtils.isEmpty(mStudentStd.text.toString()))
                {
                    student_std.error = " Please enter your class std."
                    return@setOnClickListener
                }

                mStrStudentName  = mStudentName.text.toString()
                mStrContactNo = mStudentContact.text.toString()
                mStrStudentSchoolName = mStudentSchoolName.text.toString()
                mStrStudentStd = mStudentStd.text.toString()


//                Toast.makeText(applicationContext, "Student Details From Wisdom's Academy," +
//                            "Student Name $mStrStudentName" +
//                            "Student Contact $mStrContactNo" +
//                            "Student School Name $mStrStudentStd" +
//                            "Student Standard (STD) $mStrStudentStd",Toast.LENGTH_LONG
//                    ).show()

                val isWAinstalled = appInstalledOrNot("com.whatsapp")

                if(isWAinstalled)
                {
                    submitMessageWaFunction(mStrStudentName,mStrContactNo,mStrStudentSchoolName,mStrStudentStd)
//                    Toast.makeText(applicationContext, "Student Details From Wisdom's Academy," +
//                            "Student Name $mStrStudentName" +
//                            "Student Contact $mStrContactNo" +
//                            "Student School Name $mStrStudentStd" +
//                            "Student Standard (STD) $mStrStudentStd",Toast.LENGTH_LONG
//                    ).show()
                }
                else
                {
                    Toast.makeText(applicationContext,"WhatsApp Is Not installed..", Toast.LENGTH_LONG).show()
                }

            }

            mBuilder.setView(mView)
            mBuilder.setCancelable(false)

            val dialog = mBuilder.create()
            dialog.show()

            mClose.setOnClickListener {
                dialog.dismiss()
            }


        }


        fab.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:9321302424")
            startActivity(intent)
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun appInstalledOrNot(s: String): Boolean {
        val pm = packageManager
        var app_installed : Boolean = false
        try {
            pm.getPackageInfo(s, PackageManager.GET_ACTIVITIES)
            app_installed = true
        }catch (exp : PackageManager.NameNotFoundException)
        {
            app_installed = false
        }
        return app_installed
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_page, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    fun submitMessageWaFunction(mStrStudentName: String, mStrContactNo: String, mStrStudentSchoolName: String, mStrStudentStd: String) {
        val intent = Intent("android.intent.action.MAIN")
        intent.action = Intent.ACTION_VIEW
        intent.`package` = "com.whatsapp"
        val textMessage =
            "Student Name :-  $mStrStudentName +\n" +
                    "Student Contact :-  $mStrContactNo +\n" +
                    "Student School Name :- $mStrStudentSchoolName +\n" +
                    "Student Standard (STD):- $mStrStudentStd +\n"

        val url = "https://api.whatsapp.com/send?phone=$MOBILE_NO&text=$textMessage"
        intent.data = Uri.parse(url)
        startActivity(intent)

    }
}
