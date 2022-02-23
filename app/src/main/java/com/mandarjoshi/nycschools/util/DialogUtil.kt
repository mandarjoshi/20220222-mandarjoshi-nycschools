package com.mandarjoshi.nycschools.util

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.mandarjoshi.nycschools.R

object DialogUtil {
    fun getSimpleErrorDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.error_title))
            .setMessage(context.getString(R.string.error_desc))
            .setNeutralButton(context.getString(R.string.button_ok)) { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }

    fun getNoDataDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.error_title))
            .setMessage(context.getString(R.string.error_no_data))
            .setNeutralButton(context.getString(R.string.button_ok)) { dialog, _ -> dialog.dismiss() }
        return builder.create()
    }
}
