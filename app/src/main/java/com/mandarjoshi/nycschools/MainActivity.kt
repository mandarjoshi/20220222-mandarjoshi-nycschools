package com.mandarjoshi.nycschools

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //giving toolbar control to nav graph(app_graph.xml)
        //"label" set for fragment will be set as toolbar title
        // also back button will be set for child fragments (School Scores)
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment)?.findNavController()?.let { navController ->
            val appBarConfiguration = AppBarConfiguration(navController.graph)
            findViewById<MaterialToolbar>(R.id.toolbar)
                .setupWithNavController(navController, appBarConfiguration)
        }
    }
}
