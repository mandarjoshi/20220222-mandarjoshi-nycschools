package com.mandarjoshi.nycschools

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
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

    override fun onCreateView(
        parent: View?,
        name: String,
        context: Context,
        attrs: AttributeSet
    ): View? {

        return super.onCreateView(parent, name, context, attrs)
    }
}
