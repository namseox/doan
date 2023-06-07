package com.kma.myapplication.ui.managerClass

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
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.databinding.FragmentManagerClassBinding
import com.kma.myapplication.databinding.ItemClassBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils

class ClassFragment:AbsBaseFragment<FragmentManagerClassBinding>(),onCLickClass,onClickBottomSheetClass {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelClass: ViewModelClassFragment
    lateinit var adapterClass: AdapterClass
    var listClass = listOf<ListClassItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_manager_class
    }

    override fun initView() {
        viewModelClass = ViewModelClassFragment(Application())

        handler.postDelayed(runnable, 1000)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterClass = AdapterClass(this)
        upData()
    }
    private fun upData() {
        viewModelClass.getListClass()
        viewModelClass.listClasItem.observe(viewLifecycleOwner) {
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
                        listClass = body as List<ListClassItem>
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
        viewModelClass.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterClass.deleteClass(viewModelClass.itemClass)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelClass.value_creat.observe(viewLifecycleOwner) {
            adapterClass.creatClass(it)
            Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelClass.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterClass.updateClass(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
    }
    private fun setAudioRecycleView() {
        adapterClass.getData(listClass)

        binding.rcvStaff.adapter = adapterClass
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(id: Int, binding: ItemClassBinding) {
        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelClass.getItemClass2(id)
                            viewModelClass.clas2.observe(viewLifecycleOwner) {
                                Log.d("TAG", "onItemActionClick: ...........")
                                showButtonSheetAdd(it)
                            }
                        }

                        1 -> {
                            viewModelClass.getItemClass2(id)
                            viewModelClass.clas3.observe(viewLifecycleOwner) {
                                showButtonSheetUpdate(it)
                            }
                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelClass.deleteClass(viewModelClass.itemClass.id)
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
    private fun showButtonSheetAdd(Class: ClassItem) {
        val bottomSheetActionDialog =
            ClassBottomSheetDialogFragment(this, "AddClass", Class, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            ClassBottomSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(Class: ClassItem) {
        val bottomSheetActionDialog =
            ClassBottomSheetDialogFragment(this, "UpdateClass", Class, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            ClassBottomSheetDialogFragment.TAG
        )
    }
    override fun clickItem(id: Int, binding: ItemClassBinding) {
        viewModelClass.getItemClass(id)
        viewModelClass.clas.observe(this) {
            val bottomSheetActionDialog =
                ClassBottomSheetDialogFragment(this, "Class", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }

    override fun onClickText(text: String, item: ListClassItem) {
        when (text) {
            "updateClass" -> {
                viewModelClass.updateClass(viewModelClass.itemClass.id, item)
            }

            "addClass" -> {
                viewModelClass.creatClass(item)
            }
        }
    }
}
