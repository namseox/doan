package com.kma.myapplication.ui.managerExam

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
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogExamBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class ExamBottomSheetDialogFragment (
    var onClickl: onClickBottomSheetExam,
    var status: String,
    var itemExam: ExamItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogExamBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeExam = arrayListOf<String>("Tự luận", "Trắc nghiệm","Vấn đáp","Tiểu luận")
    var listHocKy = arrayListOf<String>(
        "Học kỳ 1",
        "Học Kỳ 2"
    )
    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "ExamBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogExamBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var spTypeExam = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeExam)
        spTypeExam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spUser = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        spUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spHocKy = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listHocKy)
        spHocKy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = listAuthor.indexOf(itemExam.user.name)

//        with(binding.spFormExam) {
//            adapter = spTypeExam
//            setSelection(0, false)
//            onItemSelectedListener = this@ExamBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
//        with(binding.spFormExamb) {
//            adapter = spTypeExam
//            setSelection(itemExam., false)
//            onItemSelectedListener = this@ExamBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
        with(binding.spUser) {
            adapter = spUser
            setSelection(0, false)
            onItemSelectedListener = this@ExamBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUserb) {
            adapter = spUser
            setSelection(positionAuthor, false)
            onItemSelectedListener = this@ExamBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        with(binding.spSemester) {
            adapter = spHocKy
            setSelection(0, false)
            onItemSelectedListener = this@ExamBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spSemesterb) {
            adapter = spHocKy
            setSelection(itemExam.semester, false)
            onItemSelectedListener = this@ExamBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.examModel = itemExam
        when (status) {
            "UpdateExam" -> {
                binding.clExamb.visibility = View.VISIBLE
            }

            "Exam" -> {
                binding.clExama.visibility = View.VISIBLE
            }

            "AddExam" -> {
                binding.clExam.visibility = View.VISIBLE
            }
        }
//        binding.btnUpdate.setOnClickListener {
//            var itemList = ListExamItem(
//
//            )
//
//            onClickl.onClickText("updateExam", itemList)
//            destroy()
//        }
//        binding.btnAdd.setOnClickListener {
//            var itemList = ListExamItem(
//                binding.tvIdExam.text.toString(),
//                binding.tvEndDate.text.toString(),
//                "1",
//                ""
//
//            )
//
//            onClickl.onClickText("addExam", itemList)
//            destroy()
//        }
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

interface onClickBottomSheetExam {
    fun onClickText(text: String, item: ListExamItem)
}
