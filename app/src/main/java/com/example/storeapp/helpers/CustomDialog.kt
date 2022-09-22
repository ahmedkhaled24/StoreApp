package com.example.storeapp.helpers

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.storeapp.R
import com.google.android.material.button.MaterialButton

object CustomDialog {
    private var okayBtnClickListener: ((Boolean) -> Unit)? = null

    private fun broadcastOkayBtnListener() {
        okayBtnClickListener?.let {
            it(true)
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun showSuccessDialog(
        context: Context,
        layoutInflater: LayoutInflater,
        errorMsg: String,
        successOrError: Int,
        localOkayBtnListener: ((Boolean) -> Unit)? = null
    ) {
        okayBtnClickListener = localOkayBtnListener
        val dialogBuilder: AlertDialog.Builder =
            AlertDialog.Builder(context, R.style.FullScreenAlertDialogTheme)
        val dialog: Dialog
        val dialogView: View = layoutInflater.inflate(R.layout.dialog_error_layout, null)
        val dialogImage: ImageView = dialogView.findViewById(R.id.dialog_image)
        val dialogContent: TextView = dialogView.findViewById(R.id.error_dialog_content)
        val btnOk: MaterialButton = dialogView.findViewById(R.id.btn_error_dialog_ok)
        if (dialogView.parent != null) {
            (dialogView.parent as ViewGroup).removeView(dialogView)
        }
        when (successOrError) {
            1 -> {
                dialogImage.setImageDrawable(context.resources.getDrawable(R.drawable.ic_success_dialog))
            }
            -1 -> {
                dialogImage.setImageDrawable(context.resources.getDrawable(R.drawable.ic_error))
            }
        }
        dialogBuilder.setView(dialogView).setCancelable(false)
        dialog = dialogBuilder.create()
        dialog.window?.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.rounded_dialog))
        dialogContent.text = errorMsg
        dialog.show()
        btnOk.setOnClickListener {
            dialog.dismiss()
            broadcastOkayBtnListener()
        }
    }
}