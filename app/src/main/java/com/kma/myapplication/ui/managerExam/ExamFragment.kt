package com.kma.myapplication.ui.managerExam

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
import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.ExamItem
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.data.model.ListExamItem
import com.kma.myapplication.databinding.FragmentManagerExamBinding
import com.kma.myapplication.databinding.ItemExamBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.managerClass.AdapterClass
import com.kma.myapplication.ui.managerClass.ClassBottomSheetDialogFragment
import com.kma.myapplication.ui.managerClass.ViewModelClassFragment
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils

class ExamFragment:AbsBaseFragment<FragmentManagerExamBinding>(),onCLickExam,onClickBottomSheetExam {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelExam: ViewModelExamFragment
    lateinit var adapterExam: AdapterExam
    var listExam = listOf<ListExamItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_manager_exam
    }

    override fun initView() {
        viewModelExam = ViewModelExamFragment(Application())

        handler.postDelayed(runnable, 1000)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterExam = AdapterExam(this)
        upData()
    }
    private fun upData() {
        viewModelExam.getListExam()
        viewModelExam.listExamItem.observe(viewLifecycleOwner) {
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
                        listExam = body as List<ListExamItem>
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
        viewModelExam.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterExam.deleteExam(viewModelExam.itemExam)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelExam.value_creat.observe(viewLifecycleOwner) {
            adapterExam.creatExam(it)
            Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelExam.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterExam.updateExam(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
        viewModelExam.exam2.observe(viewLifecycleOwner) {
            Log.d("TAG", "onItemActionClick: ...........")
            showButtonSheetAdd(it)
        }
        viewModelExam.exam3.observe(viewLifecycleOwner) {
            showButtonSheetUpdate(it)
        }
        viewModelExam.exam.observe(this) {
            val bottomSheetActionDialog =
                ExamBottomSheetDialogFragment(this, "Exam", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }
    private fun setAudioRecycleView() {
        adapterExam.getData(listExam)

        binding.rcvStaff.adapter = adapterExam
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(id: Int, binding: ItemExamBinding) {
        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelExam.getItemExam2(id)

                        }

                        1 -> {
                            viewModelExam.getItemExam3(id)

                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelExam.deleteExam(viewModelExam.itemExam.id)
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
    private fun showButtonSheetAdd(Exam: ExamItem) {
        val bottomSheetActionDialog =
            ExamBottomSheetDialogFragment(this, "AddExam", Exam, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            ExamBottomSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(Exam: ExamItem) {
        val bottomSheetActionDialog =
            ExamBottomSheetDialogFragment(this, "UpdateExam", Exam, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            ExamBottomSheetDialogFragment.TAG
        )
    }
    override fun clickItem(id: Int, binding: ItemExamBinding) {
        viewModelExam.getItemExam(id)

    }

    override fun onClickText(text: String, item: ListExamItem) {
        when (text) {
            "updateExam" -> {
                viewModelExam.updateExam(viewModelExam.itemExam.id, item)
            }

            "addExam" -> {
                viewModelExam.creatExam(item)
            }
        }
    }
}