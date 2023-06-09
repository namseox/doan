package com.kma.myapplication.ui.managerclass

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
import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogClassBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class ClassBottomSheetDialogFragment(
    var onClickl: onClickBottomSheetClass,
    var status: String,
    var itemClass: ClassItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogClassBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeExam = arrayListOf<String>("Tự luận", "Trắc nghiệm", "Vấn đáp")
    var listHocKy = arrayListOf<String>(
        "Học kỳ 1",
        "Học Kỳ 2"
    )
    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "ClassBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogClassBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var spTypeExam = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeExam)
        spTypeExam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spUser = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        spUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spHocKy = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listHocKy)
        spHocKy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = 0
        try {
            positionAuthor = listAuthor.indexOf(itemClass.user.name)
        } catch (e: Exception) {

        }
        with(binding.spFormExam) {
            adapter = spTypeExam
            setSelection(0, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spFormExamb) {
            adapter = spTypeExam
            setSelection(itemClass.form_exam, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUser) {
            adapter = spUser
            setSelection(0, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUserb) {
            adapter = spUser
            setSelection(positionAuthor, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        with(binding.spSemester) {
            adapter = spHocKy
            setSelection(0, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spSemesterb) {
            adapter = spHocKy
            setSelection(itemClass.semester, false)
            onItemSelectedListener = this@ClassBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID

        binding.classModel = itemClass
        when (status) {
            "UpdateClass" -> {
                binding.clClassb.visibility = View.VISIBLE
            }

            "Class" -> {
                binding.clClassa.visibility = View.VISIBLE
            }

            "AddClass" -> {
                binding.clClass.visibility = View.VISIBLE
            }
        }
//        binding.btnUpdate.setOnClickListener {
//            var itemList = ListClassItem(
//                binding.tvIdClass,
//
//            )
//
//            onClickl.onClickText("updateClass", itemList)
//            destroy()
//        }
//        binding.btnAdd.setOnClickListener {
//            var itemList = ListClassItem(
//                binding.tvIdClass.text.toString(),
//                binding.tvEndDate.text.toString(),
//                "1",
//                ""
//
//            )
//            onClickl.onClickText("addClass", itemList)
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

interface onClickBottomSheetClass {
    fun onClickText(text: String, item: ListClassItem)
}
