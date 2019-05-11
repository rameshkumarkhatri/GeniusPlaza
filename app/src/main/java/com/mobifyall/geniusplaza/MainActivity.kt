package com.mobifyall.geniusplaza

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mobifyall.geniusplaza.fragments.UserListFragment
import com.mobifyall.geniusplaza.views.MainActivityView
import com.mobifyall.geniusplaza.views.UserListView

class MainActivity : AppCompatActivity(), MainActivityView {
    override fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frameLayout, fragment).commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        changeFragment(UserListFragment.newInstance())
    }
}
