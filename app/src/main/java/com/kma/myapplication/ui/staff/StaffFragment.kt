package com.kma.myapplication.ui.staff

import ActionAdapter
import ListActionPopup
import android.app.Application
import android.os.Handler
import android.os.Looper
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
import com.kma.myapplication.databinding.FragmentStaffBinding
import com.kma.myapplication.databinding.ItemStaffBinding

import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.buttonsheet.BottonSheetDialogFragment
import com.kma.myapplication.ui.buttonsheet.onClickBottomSheet

class StaffFragment : AbsBaseFragment<FragmentStaffBinding>(), onCLick, onClickBottomSheet {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelStaff: ViewModelStaff
    lateinit var adapterStaff: AdapterStaff
    var listStaff = listOf<StaffItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int = R.layout.fragment_staff

    override fun initView() {
        viewModelStaff = ViewModelStaff(Application())

        handler.postDelayed(runnable, 1000)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
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
                        handler.postDelayed(Runnable { setAudioRecycleView() }, 200)
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
    }

    private fun setAudioRecycleView() {
        adapterStaff.getData(listStaff)
        binding.rcvStaff.adapter = adapterStaff
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    private val actions = arrayListOf<String>("Add","Edit","Delete")

    override fun click3Dot(staff: StaffItem, binding: ItemStaffBinding) {
        listActionPopup.showPopup(binding.iv3dot,actions,object :ActionAdapter.OnActionClickListener{
            override fun onItemActionClick(position: Int) {
                when(position){
                    0->{
                        showButtonSheetAdd(staff)
                    }
                    1->{
                        showButtonSheetUpdate(staff)
                    }
                    else->{
                        var myFileDialog = StaffDialog.create(object : StaffDialog.IListener {
                            override fun delete() {

                                Toast.makeText(requireContext(),"Xoá thành công",Toast.LENGTH_SHORT).show()
                            }
                        })
                        myFileDialog.show(childFragmentManager, "myfileDialog")

                    }
                }
            }

        })
    }

    private fun showButtonSheetAdd(staff: StaffItem) {
        val bottomSheetActionDialog = BottonSheetDialogFragment(this,"AddStaff",staff)
        bottomSheetActionDialog.show(requireActivity().supportFragmentManager, BottonSheetDialogFragment.TAG)
    }

    private fun showButtonSheetUpdate(staff: StaffItem) {
        val bottomSheetActionDialog = BottonSheetDialogFragment(this,"UpdateStaff",staff)
        bottomSheetActionDialog.show(requireActivity().supportFragmentManager, BottonSheetDialogFragment.TAG)

    }

    override fun clickItem(staff: StaffItem, binding: ItemStaffBinding) {
        val bottomSheetActionDialog = BottonSheetDialogFragment(this,"Staff",staff)
        bottomSheetActionDialog.show(requireActivity().supportFragmentManager, BottonSheetDialogFragment.TAG)
    }

    override fun onClickText(text: String) {
        when(text){
            "updateStaff"->{

            }
            "addStaff"->{

            }
        }

    }


}


