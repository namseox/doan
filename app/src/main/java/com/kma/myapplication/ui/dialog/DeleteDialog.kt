package com.kma.myapplication.ui.dialog

import android.os.Bundle
import com.kma.myapplication.R
import com.kma.myapplication.databinding.MyFileDialogBinding
import com.kma.myapplication.ui.base.dialog.BaseDialog
import com.kma.myapplication.ui.base.dialog.BaseListener

class DeleteDialog(): BaseDialog<MyFileDialogBinding, DeleteDialog.IListener>() {

    interface IListener : BaseListener {
        fun delete()
    }
    override fun initViewModel() {
        setStyle(STYLE_NORMAL, R.style.Theme_App_Dialog_FullScreen)
    }

    override fun initView() {
        binding.tvDelete.setOnClickListener(){
            listener?.delete()
            dismiss()
        }
        binding.tvCancel.setOnClickListener(){
            dismiss()
        }
    }

    override fun getLayout(): Int = R.layout.my_file_dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    companion object {
        fun create(listener: IListener): DeleteDialog {
            val dialog = DeleteDialog()
            dialog.listener = listener
            return dialog
        }
    }
}