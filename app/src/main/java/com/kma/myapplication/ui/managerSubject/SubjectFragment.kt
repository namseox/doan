package com.kma.myapplication.ui.managerSubject

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
import com.kma.myapplication.data.model.SubjectItem
import com.kma.myapplication.data.model.ListSubjectItem
import com.kma.myapplication.databinding.FragmentManagerSubjectBinding
import com.kma.myapplication.databinding.ItemSubjectBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.managerSubject.AdapterSubject
import com.kma.myapplication.ui.managerSubject.SubjectBottomSheetDialogFragment
import com.kma.myapplication.ui.managerSubject.ViewModelSubjectFragment
import com.kma.myapplication.ui.managerSubject.onCLickSubject
import com.kma.myapplication.ui.managerSubject.onClickBottomSheetSubject
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils
import com.kma.myapplication.utils.Utils.setSafeMenuClickListener

class SubjectFragment:AbsBaseFragment<FragmentManagerSubjectBinding>(), onCLickSubject,
    onClickBottomSheetSubject {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelSubject: ViewModelSubjectFragment
    lateinit var adapterSubject: AdapterSubject
    var listSubject = listOf<ListSubjectItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_manager_subject
    }

    override fun initView() {
        viewModelSubject = ViewModelSubjectFragment(Application())

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
                    showButtonSheetAdd(SubjectItem())
                }
            }
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterSubject = AdapterSubject(this)
        upData()
    }
    private fun upData() {
        viewModelSubject.getListSubject()
        viewModelSubject.listSubjectItem.observe(viewLifecycleOwner) {
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
                        listSubject = body as List<ListSubjectItem>
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
        viewModelSubject.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterSubject.deleteSubject(viewModelSubject.itemSubject)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelSubject.value_creat.observe(viewLifecycleOwner) {
            adapterSubject.creatSubject(it)
            Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelSubject.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterSubject.updateSubject(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
        viewModelSubject.Subject2.observe(viewLifecycleOwner) {
            Log.d("TAG", "onItemActionClick: ...........")
            showButtonSheetAdd(it)
        }
        viewModelSubject.Subject3.observe(viewLifecycleOwner) {
            showButtonSheetUpdate(it)
        }
        viewModelSubject.Subject.observe(this) {
            val bottomSheetActionDialog =
                SubjectBottomSheetDialogFragment(this, "Subject", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }
    private fun setAudioRecycleView() {
        adapterSubject.getData(listSubject)

        binding.rcvStaff.adapter = adapterSubject
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(id: Int, binding: ItemSubjectBinding) {
        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelSubject.getItemSubject2(id)

                        }

                        1 -> {
                            viewModelSubject.getItemSubject3(id)

                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelSubject.deleteSubject(viewModelSubject.itemSubject.id)
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
    private fun showButtonSheetAdd(Subject: SubjectItem) {
        val bottomSheetActionDialog =
            SubjectBottomSheetDialogFragment(this, "AddSubject", Subject, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            SubjectBottomSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(Subject: SubjectItem) {
        val bottomSheetActionDialog =
            SubjectBottomSheetDialogFragment(this, "UpdateSubject", Subject, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            SubjectBottomSheetDialogFragment.TAG
        )
    }
    override fun clickItem(id: Int, binding: ItemSubjectBinding) {
        viewModelSubject.getItemSubject(id)
    }

    override fun onClickText(text: String, item: ListSubjectItem) {
        when (text) {
            "updateSubject" -> {
                viewModelSubject.updateSubject(viewModelSubject.itemSubject.id, item)
            }

            "addSubject" -> {
                viewModelSubject.creatSubject(item)
            }
        }
    }

}