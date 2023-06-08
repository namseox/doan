package com.kma.myapplication.ui.managerMark

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
import com.kma.myapplication.data.model.MarkItem
import com.kma.myapplication.data.model.ListMarkItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogMarkBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class MarkBottomSheetDialogFragment(
    var onClickl: onClickBottomSheetMark,
    var status: String,
    var itemMark: MarkItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogMarkBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeMark = arrayListOf<String>("Tự luận", "Trắc nghiệm","Vấn đáp","Tiểu luận")
    var listHocKy = arrayListOf<String>(
        "Học kỳ 1",
        "Học Kỳ 2"
    )
    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "MarkBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogMarkBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var spTypeMark = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeMark)
        spTypeMark.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spUser = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        spUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spHocKy = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listHocKy)
        spHocKy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = listAuthor.indexOf(itemMark.user.name)

//        with(binding.spFormMark) {
//            adapter = spTypeMark
//            setSelection(0, false)
//            onItemSelectedListener = this@MarkBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
//        with(binding.spFormMarkb) {
//            adapter = spTypeMark
//            setSelection(itemMark., false)
//            onItemSelectedListener = this@MarkBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
        with(binding.spUser) {
            adapter = spUser
            setSelection(0, false)
            onItemSelectedListener = this@MarkBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUserb) {
            adapter = spUser
            setSelection(positionAuthor, false)
            onItemSelectedListener = this@MarkBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        with(binding.spSemester) {
            adapter = spHocKy
            setSelection(0, false)
            onItemSelectedListener = this@MarkBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spSemesterb) {
            adapter = spHocKy
            setSelection(itemMark.semester, false)
            onItemSelectedListener = this@MarkBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.markModel = itemMark
        when (status) {
            "UpdateMark" -> {
                binding.clMarkb.visibility = View.VISIBLE
            }

            "Mark" -> {
                binding.clMarka.visibility = View.VISIBLE
            }

            "AddMark" -> {
                binding.clMark.visibility = View.VISIBLE
            }
        }
//        binding.btnUpdate.setOnClickListener {
//            var itemList = ListMarkItem(
//
//            )
//
//            onClickl.onClickText("updateMark", itemList)
//            destroy()
//        }
//        binding.btnAdd.setOnClickListener {
//            var itemList = ListMarkItem(
//                binding.tvIdMark.text.toString(),
//                binding.tvEndDate.text.toString(),
//                "1",
//                ""
//
//            )
//
//            onClickl.onClickText("addMark", itemList)
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

interface onClickBottomSheetMark {
    fun onClickText(text: String, item: ListMarkItem)
}
