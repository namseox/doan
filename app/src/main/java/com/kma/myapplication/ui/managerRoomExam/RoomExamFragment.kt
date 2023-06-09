package com.kma.myapplication.ui.managerRoomExam

import ListActionPopup
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.R
import com.kma.myapplication.data.model.ListRoomItem
import com.kma.myapplication.data.model.RoomItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.FragmentManagerRoomExamBinding
import com.kma.myapplication.databinding.ItemRoomBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils
import com.kma.myapplication.utils.Utils.setSafeMenuClickListener

class RoomExamFragment:AbsBaseFragment<FragmentManagerRoomExamBinding>(),onCLickRoom,onClickBottomSheetRoom {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelRoom: ViewModelRoomFragment
    lateinit var adapterRoom: AdapterRoom
    var listRoom = listOf<ListRoomItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_manager_room_exam
    }

    override fun initView() {
        viewModelRoom = ViewModelRoomFragment(Application())

        handler.postDelayed(runnable, 1000)
        binding.topAppBar.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.appBar.addLiftOnScrollListener { _, backgroundColor ->
            requireActivity().window.statusBarColor = backgroundColor
        }
        binding.topAppBar.setSafeMenuClickListener {
            when(it!!.itemId){
                R.id.ic_add ->{
                    showButtonSheetAdd(RoomItem())
                }
            }
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterRoom = AdapterRoom(this)
        upData()
    }
    private fun upData() {
        viewModelRoom.getListRoom()
        viewModelRoom.listRoomItem.observe(viewLifecycleOwner) {
            it?.let {
                if (it.loadingStatus == LoadingStatus.Loading) {
                    binding.cpiLoading.visibility = View.VISIBLE
                    binding.tvStatus.visibility = View.GONE
                }
                if (it.loadingStatus == LoadingStatus.Success) {
                    val body = (it as DataResponse.DataSuccess).body
                    if (body!!.isNotEmpty()) {
                        binding.cpiLoading.visibility = View.GONE
                        binding.tvStatus.visibility = View.GONE
                        listRoom = body as List<ListRoomItem>
                        handler.postDelayed(Runnable {
                            setAudioRecycleView()
                        }, 200)
                    } else {
                        binding.tvStatus.visibility = View.VISIBLE
                        binding.tvStatus.text = "Không có sách"
                    }
                }
                if (it.loadingStatus == LoadingStatus.Error) {
                    binding.tvStatus.visibility = View.VISIBLE
                    binding.cpiLoading.visibility = View.GONE
                    binding.tvStatus.text = "ERROR"

                }
            }
        }
        viewModelRoom.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterRoom.deleteRoom(viewModelRoom.itemRoom)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelRoom.value_creat.observe(viewLifecycleOwner) {
            adapterRoom.creatRoom(it)
            Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelRoom.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterRoom.updateRoom(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
        viewModelRoom.Room2.observe(viewLifecycleOwner) {
            Log.d("TAG", "onItemActionClick: ...........")
            showButtonSheetAdd(it)
        }
        viewModelRoom.Room3.observe(viewLifecycleOwner) {
            showButtonSheetUpdate(it)
        }
        viewModelRoom.Room.observe(this) {
            val bottomSheetActionDialog =
                RoomBottomSheetDialogFragment(this, "Room", it,viewModelRoom.itemListRoom, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }
    private fun setAudioRecycleView() {
        adapterRoom.getData(listRoom)

        binding.rcvStaff.adapter = adapterRoom
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(itemListRoom: ListRoomItem, binding: ItemRoomBinding) {
        viewModelRoom.itemListRoom = itemListRoom
        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelRoom.getItemRoom2(itemListRoom.id)

                        }

                        1 -> {
                            viewModelRoom.getItemRoom3(itemListRoom.id)

                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelRoom.deleteRoom(viewModelRoom.itemRoom.id)
                                        } catch (e: Exception) {
                                            Toast.makeText(
                                                requireContext(),
                                                "Xoá không thành công",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }

                                    }
                                })
                            myFileDialog.show(childFragmentManager, "myfileDialog")

                        }

                    }
                }
            })
    }
    private fun showButtonSheetAdd(Room: RoomItem) {
        val bottomSheetActionDialog =
            RoomBottomSheetDialogFragment(this, "AddRoom", Room,viewModelRoom.itemListRoom, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            RoomBottomSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(Room: RoomItem) {
        val bottomSheetActionDialog =
            RoomBottomSheetDialogFragment(this, "UpdateRoom", Room,viewModelRoom.itemListRoom, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            RoomBottomSheetDialogFragment.TAG
        )
    }

    override fun clickItem(itemListRoom: ListRoomItem, binding: ItemRoomBinding) {
        viewModelRoom.itemListRoom = itemListRoom
        viewModelRoom.getItemRoom(itemListRoom.id)
    }

    override fun onClickText(text: String, item: ListRoomItem) {
        when (text) {
            "updateRoom" -> {
                viewModelRoom.updateRoom(viewModelRoom.itemRoom.id, item)
            }

            "addRoom" -> {
                viewModelRoom.creatRoom(item)
            }
        }
    }
}