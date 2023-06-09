package com.kma.myapplication.ui.staff


import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.FragmentBottomSheetDialogStaffBinding


class StaffBottonSheetDialogFragment(
    var onClick: onClickBottomSheet,
    var status: String,
    var staff: UserX
) :
    BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogStaffBinding
    var departmet_it = arrayOf("Khoa điện tử viễn thông","Khoa mật mã","Phòng đào tạo","Khoa an toàn thông tin","Khoa công nghệ thông tin")
    var position_staff = arrayOf("Trưởng khoa","Phó khoa","Giảng viên")
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "StaffBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state =
            BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogStaffBinding.inflate(layoutInflater, container, false)

        binding.staffModel = staff

        var aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, departmet_it)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var bb = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, position_staff)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var id_department = departmet_it.indexOf(staff.department_id.toString())
        var position_staff_v = position_staff.indexOf(staff.position.toString())
        with(binding.spDepartment) {
            adapter = aa
            setSelection(id_department, false)
            onItemSelectedListener = this@StaffBottonSheetDialogFragment
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER
        }
        with(binding.spDepartmentb) {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@StaffBottonSheetDialogFragment
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER
        }
        with(binding.spPositionStaffb) {
            adapter = bb
            setSelection(0, false)
            onItemSelectedListener = this@StaffBottonSheetDialogFragment
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER
        }
        with(binding.spPositionStaff) {
            adapter = bb
            setSelection(position_staff_v, false)
            onItemSelectedListener = this@StaffBottonSheetDialogFragment
            prompt = "Select your favourite language"
            gravity = Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        when (status) {
            "UpdateStaff" -> {
                binding.clStaff.visibility = VISIBLE

            }

            "Staff" -> {
                binding.clStaffa.visibility = VISIBLE
            }

            "AddStaff" -> {
                binding.clStaffb.visibility = VISIBLE
            }
        }
        binding.btnCancel.setOnClickListener {
            destroy()
        }
        binding.btnUpdate.setOnClickListener {
            var staff = UserX(
                "",
                "11/11/2000",
                binding.tvIdStaff.text.toString(),
                "",
                departmet_it.indexOf(binding.spDepartment.selectedItem.toString()),
                binding.tvEmailStaff.text.toString(),
                0,
                binding.tvSalaryStaff.text.toString().toInt(),
                binding.tvNameStaff.text.toString(),
                binding.tvStepSalary.text.toString().toInt(),
                "1",
                position_staff.indexOf(binding.spPositionStaff.selectedItem.toString()).toString(),
                "",
                0,
                0
            )
            onClick.onClickText("updateStaff", staff)
            destroy()
        }
        binding.btnAdd.setOnClickListener {
            var staff = UserX(
                "",
                "11/11/2000",
                binding.tvIdStaffb.text.toString(),
                "",
                departmet_it.indexOf(binding.spDepartment.selectedItem.toString()),
                binding.tvEmailStaffb.text.toString(),
                0,
                binding.tvSalaryStaffb.text.toString().toInt(),
                binding.tvNameStaffb.text.toString(),
                binding.tvStepSalaryb.text.toString().toInt(),
                "1",
                position_staff.indexOf(binding.spPositionStaff.selectedItem.toString()).toString(),
                "",
                0,
                0
            )
            onClick.onClickText("addStaff", staff)
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

interface onClickBottomSheet {
    fun onClickText(text: String, staff: UserX)
}