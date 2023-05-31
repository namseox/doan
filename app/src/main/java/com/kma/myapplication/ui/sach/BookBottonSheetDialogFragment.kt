package com.kma.myapplication.ui.sach

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
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.FragmentBottomSheetDialogBookBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class BookBottonSheetDialogFragment(
    onClick: onClickBottomSheetBook,
    var status: String,
    var itemBook: Book,
    var ct: Context
) :
    BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogBookBinding
    var a : List<StaffItem>? = SharedPreferenceUtils.getInstance(ct).getObjModel<List<StaffItem>>()

    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "BookBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state =
            BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogBookBinding.inflate(layoutInflater, container, false)
        for (i in a!!) {
            listAuthor.add(i.name)
        }

        var aa = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        var positionAuthor_1 = listAuthor.indexOf(itemBook.users[0].name)
        var positionAuthor_2 = -1
        try {
            positionAuthor_2 = listAuthor.indexOf(itemBook.users[1].name)
        } catch (e: Exception) {
            null
        }
        with(binding.spAuthor1) {
            adapter = aa
            setSelection(positionAuthor_1, false)
            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor2) {
            adapter = aa
            if (positionAuthor_2 != -1){
                setSelection(positionAuthor_2, false)
            }

            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor1B) {
            adapter = aa
            setSelection(positionAuthor_1, false)
            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor2B) {
            adapter = aa
            if (positionAuthor_2 != -1){
                setSelection(positionAuthor_2, false)
            }

            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.bookModel = itemBook
        when (status) {
            "UpdateStaff" -> {

                binding.standardBottomSheet.visibility = View.VISIBLE

            }

            "Staff" -> {
                binding.standardBottomSheetA.visibility = View.VISIBLE
            }

            "AddStaff" -> {
                binding.standardBottomSheetB.visibility = View.VISIBLE
            }
        }
        binding.btnCancel.setOnClickListener {
            destroy()
        }
//        binding.btnUpdate.setOnClickListener {
//            var staff = UserX(
//                "",
//                "11/11/2000",
//                binding.tvIdStaff.text.toString(),
//                "",
//                "",
//                "",
//                binding.spDepartment.selectedItem.toString().toInt(),
//                binding.tvEmailStaff.text.toString(),
//                0,
//                binding.tvSalaryStaff.text.toString().toInt(),
//                binding.tvNameStaff.text.toString(),
//                binding.tvStepSalary.text.toString().toInt(),
//                "1",
//                binding.spPositionStaff.selectedItem.toString(),
//                "",
//                0,
//                0,
//                ""
//            )
//            onClick.onClickText("updateStaff", staff)
//            destroy()
//        }
//        binding.btnAdd.setOnClickListener {
//            var staff = UserX(
//                "",
//                "11/11/2000",
//                binding.tvIdStaffb.text.toString(),
//                "",
//                "",
//                "",
//                binding.spDepartmentb.selectedItem.toString().toInt(),
//                binding.tvEmailStaffb.text.toString(),
//                0,
//                binding.tvSalaryStaffb.text.toString().toInt(),
//                binding.tvNameStaffb.text.toString(),
//                binding.tvStepSalaryb.text.toString().toInt(),
//                "1",
//                binding.spPositionStaffb.selectedItem.toString(),
//                "",
//                0,
//                0,
//                ""
//            )
//            onClick.onClickText("addStaff", staff)
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

interface onClickBottomSheetBook {
    fun onClickText(text: String, bookItem: ListBookItem)
}
