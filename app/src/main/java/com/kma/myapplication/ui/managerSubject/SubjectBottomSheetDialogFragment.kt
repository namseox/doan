package com.kma.myapplication.ui.managerSubject

import android.R
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kma.myapplication.data.model.SubjectItem
import com.kma.myapplication.data.model.ListSubjectItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogSubjectBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class SubjectBottomSheetDialogFragment  (
    var onClickl: onClickBottomSheetSubject,
    var status: String,
    var itemSubject: SubjectItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogSubjectBinding
    var listTypeSubject = arrayListOf<String>("Tự luận", "Trắc nghiệm","Vấn đáp","Tiểu luận")
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "SubjectBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogSubjectBinding.inflate(layoutInflater, container, false)


//        var spTypeSubject = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeSubject)
//        spTypeSubject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//
//        with(binding.spIndexSubject) {
//            adapter = spTypeSubject
//            setSelection(0, false)
//            onItemSelectedListener = this@SubjectBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }


        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        if (itemSubject.name==""){

        }else{
            binding.subjectModel = itemSubject
        }

        when (status) {
            "UpdateSubject" -> {
                binding.clSubjectb.visibility = View.VISIBLE
            }

            "Subject" -> {
                binding.clSubjecta.visibility = View.VISIBLE
            }

            "AddSubject" -> {
                binding.clSubject.visibility = View.VISIBLE
            }
        }
        binding.btnUpdate.setOnClickListener {
            var itemList = ListSubjectItem(
                binding.tvIdSubjectb.text.toString(),
                "",
                0,
                binding.tvNameSubjectb.text.toString()
            )

            onClickl.onClickText("updateSubject", itemList)
            destroy()
        }
        binding.btnAdd.setOnClickListener {
            var itemList = ListSubjectItem(
                binding.tvIdSubject.text.toString(),
                binding.tvIndexSubject.text.toString(),
                0,
                binding.tvNameSubject.text.toString()
            )
            onClickl.onClickText("addSubject", itemList)
            destroy()
        }
        return binding.root
    }

    private fun destroy() {
        Handler(Looper.getMainLooper()).postDelayed({ dismiss() }, 200)
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 -> {

            }
            else -> {

            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }


}

interface onClickBottomSheetSubject {
    fun onClickText(text: String, item: ListSubjectItem)
}
