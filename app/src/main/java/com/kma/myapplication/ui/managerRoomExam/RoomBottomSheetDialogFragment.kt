package com.kma.myapplication.ui.managerRoomRoom

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
import com.kma.myapplication.data.model.RoomItem
import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogRoomBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class RoomBottomSheetDialogFragment(
    var onClickl: onClickBottomSheetRoom,
    var status: String,
    var itemRoom: RoomItem,
    var itemListRoom: ListRoomItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogRoomBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeRoom = arrayListOf<String>("Tự luận", "Trắc nghiệm", "Vấn đáp", "Tiểu luận")
    var listHocKy = arrayListOf<String>(
        "Học kỳ 1",
        "Học Kỳ 2"
    )
    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "RoomBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogRoomBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var spTypeRoom = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeRoom)
        spTypeRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spUser = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        spUser.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var spHocKy = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listHocKy)
        spHocKy.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = listAuthor.indexOf(itemListRoom.user.name)

//        with(binding.spFormRoom) {
//            adapter = spTypeRoom
//            setSelection(0, false)
//            onItemSelectedListener = this@RoomBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
//        with(binding.spFormRoomb) {
//            adapter = spTypeRoom
//            setSelection(itemRoom., false)
//            onItemSelectedListener = this@RoomBottomSheetDialogFragment
//            prompt = "Chọn tác giả"
//            gravity = Gravity.CENTER
//        }
        with(binding.spUser) {
            adapter = spUser
            setSelection(0, false)
            onItemSelectedListener = this@RoomBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUserb) {
            adapter = spUser
            setSelection(positionAuthor, false)
            onItemSelectedListener = this@RoomBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        with(binding.spSemester) {
            adapter = spHocKy
            setSelection(0, false)
            onItemSelectedListener = this@RoomBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spSemesterb) {
            adapter = spHocKy
            setSelection(itemRoom.semester, false)
            onItemSelectedListener = this@RoomBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.roomModel = itemRoom
        when (status) {
            "UpdateRoom" -> {
                binding.clRoomb.visibility = View.VISIBLE
            }

            "Room" -> {
                binding.clRooma.visibility = View.VISIBLE
            }

            "AddRoom" -> {
                binding.clRoom.visibility = View.VISIBLE
            }
        }
//        binding.btnUpdate.setOnClickListener {
//            var itemList = ListRoomItem(
//
//            )
//
//            onClickl.onClickText("updateRoom", itemList)
//            destroy()
//        }
//        binding.btnAdd.setOnClickListener {
//            var itemList = ListRoomItem(
//                binding.tvIdRoom.text.toString(),
//                binding.tvEndDate.text.toString(),
//                "1",
//                ""
//
//            )
//
//            onClickl.onClickText("addRoom", itemList)
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

interface onClickBottomSheetRoom {
    fun onClickText(text: String, item: ListRoomItem)
}
