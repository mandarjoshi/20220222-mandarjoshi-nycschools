package com.mandarjoshi.nycschools.ui

import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import com.mandarjoshi.nycschools.R


open class BaseFragment: Fragment() {
    protected open fun showProgressBar(parentView: View) {
        parentView.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.VISIBLE
    }

    protected open fun hideProgressBar(parentView: View) {
        parentView.findViewById<ProgressBar>(R.id.progress_bar).visibility = View.GONE
    }
}
