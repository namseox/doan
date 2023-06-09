package com.kma.myapplication.ui.staff

import ActionAdapter
import ListActionPopup
import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus
import com.kma.myapplication.R
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.data.model.StaffItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.data.model.Year
import com.kma.myapplication.databinding.FragmentStaffBinding
import com.kma.myapplication.databinding.ItemStaffBinding

import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils.actions
import com.kma.myapplication.utils.Utils.setSafeMenuClickListener
import java.util.ArrayList

class StaffFragment : AbsBaseFragment<FragmentStaffBinding>(), onCLick, onClickBottomSheet {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelStaff: ViewModelStaffFragment
    lateinit var adapterStaff: AdapterStaff
    var listStaff = listOf<StaffItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int = R.layout.fragment_staff


    override fun initView() {
        viewModelStaff = ViewModelStaffFragment(Application())

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
                    showButtonSheetAdd(UserX())
                }
            }
        }
    }

    private val runnable = Runnable {
        runView()
    }

    private fun runView() {
        adapterStaff = AdapterStaff(this)
        upData()
    }


    private fun upData() {
        viewModelStaff.getListStaff()
        viewModelStaff.listStaffItem.observe(viewLifecycleOwner) {
            it?.let {
                if (it.loadingStatus == LoadingStatus.Loading) {
                    binding.cpiLoading.visibility = VISIBLE
                    binding.tvStatus.visibility = GONE
                }
                if (it.loadingStatus == LoadingStatus.Success) {
                    val body = (it as DataResponse.DataSuccess).body
                    if (body!!.isNotEmpty()) {
                        binding.cpiLoading.visibility = GONE
                        binding.tvStatus.visibility = GONE
                        listStaff = body as List<StaffItem>
                        var a: Staff = Staff(listStaff as ArrayList<StaffItem>)
                        SharedPreferenceUtils.getInstance(requireContext()).setObjModel(a)
                        handler.postDelayed(Runnable {
                            setAudioRecycleView()
                        }, 200)
                    } else {
                        binding.tvStatus.visibility = VISIBLE
                        binding.tvStatus.text = "Không có nhân viên"
                    }
                }
                if (it.loadingStatus == LoadingStatus.Error) {
                    binding.tvStatus.visibility = VISIBLE
                    binding.cpiLoading.visibility = GONE
                    binding.tvStatus.text = "ERROR"

                }
            }
        }
        viewModelStaff.value_delete.observe(this) {
            it?.let {
                if (it == 1) {
                    adapterStaff.deleteStaff(viewModelStaff.itemStaff)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelStaff.value_creat.observe(this) {
            it?.let {
                if (it.success) {
                    viewModelStaff.checkUpdate = true
                    viewModelStaff.getListStaff()
                    Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()
                }
            }
        }
        viewModelStaff.value_updtae.observe(this) {
            it?.let {
                Log.d("TAG", "upData00: ")
                adapterStaff.updateStaff(it)
                Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        viewModelStaff.getItemUser.observe(this) {
            it?.let {
                val bottomSheetActionDialog = StaffBottonSheetDialogFragment(this, "Staff", it)
                bottomSheetActionDialog.show(
                    requireActivity().supportFragmentManager,
                    StaffBottonSheetDialogFragment.TAG
                )
            }
        }
        viewModelStaff.getItemUser2.observe(this) {
            it?.let {
                showButtonSheetAdd(it)

            }}
        viewModelStaff.getItemUser3.observe(this) {
            it?.let {
                showButtonSheetUpdate(it)
            }
        }
    }

    private fun setAudioRecycleView() {
        if (viewModelStaff.checkUpdate) {
            adapterStaff.getData(true, listStaff)
            viewModelStaff.checkUpdate = false
        } else {
            adapterStaff.getData(false, listStaff)
        }
        binding.rcvStaff.adapter = adapterStaff
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }


    override fun click3Dot(staff: StaffItem, binding: ItemStaffBinding, year_id: Int) {
        listActionPopup.showPopup(
            binding.iv3dot,
            actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    viewModelStaff.itemStaff = staff
                    when (position) {
                        0 -> {
                            viewModelStaff.getStaff2(staff.id, year_id)
                        }

                        1 -> {
                            viewModelStaff.getStaff3(staff.id, year_id)
                        }

                        else -> {
                            var myFileDialog = DeleteDialog.create(object : DeleteDialog.IListener {
                                override fun delete() {
                                    try {
                                        viewModelStaff.deleteStaff(staff.id)
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

    private fun showButtonSheetAdd(staff: UserX) {
        val bottomSheetActionDialog = StaffBottonSheetDialogFragment(this, "AddStaff", staff)
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            StaffBottonSheetDialogFragment.TAG
        )
    }

    private fun showButtonSheetUpdate(staff: UserX) {
        val bottomSheetActionDialog = StaffBottonSheetDialogFragment(this, "UpdateStaff", staff)
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            StaffBottonSheetDialogFragment.TAG
        )

    }

    override fun clickItem(staff: StaffItem, binding: ItemStaffBinding, year_id: Int) {
        viewModelStaff.itemStaff = staff
        viewModelStaff.getStaff(staff.id, year_id)
    }

    override fun onClickText(text: String, staff: UserX) {
        when (text) {
            "updateStaff" -> {
                viewModelStaff.updateStaff(viewModelStaff.itemStaff.id, staff)
            }

            "addStaff" -> {
                viewModelStaff.creatStaff(staff)
            }
        }

    }


}


