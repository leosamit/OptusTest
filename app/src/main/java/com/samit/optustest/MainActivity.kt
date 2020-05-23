package com.samit.optustest

import android.os.Bundle
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.samit.optustest.ui.userinfo.UserInfoFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
      /*  supportActionBar?.title = ""
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                UserInfoFragment(),
                resources.getString(R.string.user_info_fragment)
            )
            .commit()*/
    }
    override fun onSupportNavigateUp(): Boolean = findNavController(R.id.nav_host_fragment).navigateUp()
}
