package com.kma.myapplication.ui.managermark

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
import com.kma.myapplication.data.model.ListMarkItem
import com.kma.myapplication.data.model.MarkItem
import com.kma.myapplication.data.model.UserX
import com.kma.myapplication.databinding.FragmentManagerMarkExamBinding
import com.kma.myapplication.databinding.ItemMarkBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils
import com.kma.myapplication.utils.Utils.setSafeMenuClickListener

class MarkExamFragment:AbsBaseFragment<FragmentManagerMarkExamBinding>(), onCLickMark,onClickBottomSheetMark{
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelMark: ViewModelMarkFragment
    lateinit var adapterMark: AdapterMark
    var listMark = listOf<ListMarkItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_manager_mark_exam
    }

    override fun initView() {
        viewModelMark = ViewModelMarkFragment(Application())

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
                    showButtonSheetAdd(MarkItem())
                }
            }
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterMark = AdapterMark(this)
        upData()
    }
    private fun upData() {
        viewModelMark.getListMark()
        viewModelMark.listMarkItem.observe(viewLifecycleOwner) {
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
                        listMark = body as List<ListMarkItem>
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
        viewModelMark.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterMark.deleteMark(viewModelMark.itemMark)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelMark.value_creat.observe(viewLifecycleOwner) {
            adapterMark.creatMark(it)
            Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelMark.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterMark.updateMark(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
        viewModelMark.Mark2.observe(viewLifecycleOwner) {
            Log.d("TAG", "onItemActionClick: ...........")
            showButtonSheetAdd(it)
        }
        viewModelMark.Mark3.observe(viewLifecycleOwner) {
            showButtonSheetUpdate(it)
        }
        viewModelMark.Mark.observe(this) {
            val bottomSheetActionDialog =
                MarkBottomSheetDialogFragment(this, "Mark", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }
    private fun setAudioRecycleView() {
        adapterMark.getData(listMark)

        binding.rcvStaff.adapter = adapterMark
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(id: Int, binding: ItemMarkBinding) {
        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelMark.getItemMark2(id)

                        }

                        1 -> {
                            viewModelMark.getItemMark3(id)

                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelMark.deleteMark(viewModelMark.itemMark.id)
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

    override fun clickItem(id: Int, binding: ItemMarkBinding) {
        viewModelMark.getItemMark(id)
    }
    private fun showButtonSheetAdd(Mark: MarkItem) {
        val bottomSheetActionDialog =
            MarkBottomSheetDialogFragment(this, "AddMark", Mark, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            MarkBottomSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(Mark: MarkItem) {
        val bottomSheetActionDialog =
            MarkBottomSheetDialogFragment(this, "UpdateMark", Mark, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            MarkBottomSheetDialogFragment.TAG
        )
    }
    override fun onClickText(text: String, item: ListMarkItem) {
        when (text) {
            "updateMark" -> {
                viewModelMark.updateMark(viewModelMark.itemMark.id, item)
            }

            "addMark" -> {
                viewModelMark.creatMark(item)
            }
        }
    }
}