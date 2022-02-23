package com.mandarjoshi.nycschools.util

import android.content.Context
import androidx.appcompat.app.AlertDialog

object DialogUtil {
    fun getSimpleErrorDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
            .setMessage("Couldn't complete request. try again later.")
            .setNeutralButton("Ok") { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }

    fun getNoDataDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Error")
            .setMessage("No data found.")
            .setNeutralButton("Ok") { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }
}
