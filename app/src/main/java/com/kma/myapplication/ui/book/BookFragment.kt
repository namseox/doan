package com.kma.myapplication.ui.book

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
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.databinding.FragmentBookBinding
import com.kma.myapplication.databinding.ItemBookBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.ui.staff.StaffDialog
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils.actions

class BookFragment : AbsBaseFragment<FragmentBookBinding>(), onCLickBook, onClickBottomSheetBook {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelBook: ViewModelBookFragment
    lateinit var adapterBook: AdapterBook
    var listBook = listOf<ListBookItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_book
    }

    override fun initView() {
        viewModelBook = ViewModelBookFragment(Application())

        handler.postDelayed(runnable, 1000)
        binding.toolbar.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private val runnable = Runnable {
        runView()
    }

    private fun runView() {
        adapterBook = AdapterBook(this)
        upData()
    }

    private fun upData() {
        viewModelBook.getListBook()
        viewModelBook.listBookItem.observe(viewLifecycleOwner) {
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
                        listBook = body as List<ListBookItem>
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
        viewModelBook.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterBook.deleteBook(viewModelBook.itemBook)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelBook.value_creat.observe(viewLifecycleOwner) {
                if (it.success) {
                    adapterBook.creatBook(it)
                    Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()
                }

        }
        viewModelBook.value_updtae.observe(viewLifecycleOwner) {
                Log.d("TAG", "upData000000: "+it)
                    adapterBook.updateBook(it)
                    Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                        .show()

        }
    }

    private fun setAudioRecycleView() {
        adapterBook.getData(listBook)

        binding.rcvStaff.adapter = adapterBook
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }

    override fun click3Dot(idBook: Int, binding: ItemBookBinding) {
        listActionPopup.showPopup(
            binding.iv3dot,
            actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                        when (position) {
                            0 -> {
                                viewModelBook.getItemBook2(idBook)
                                viewModelBook.book2.observe(viewLifecycleOwner) {
                                    Log.d("TAG", "onItemActionClick: ...........")
                                    showButtonSheetAdd(it)
                                }
                            }

                            1 -> {
                                viewModelBook.getItemBook3(idBook)
                                viewModelBook.book3.observe(viewLifecycleOwner) {
                                    showButtonSheetUpdate(it)
                                }
                            }

                            else -> {
                                var myFileDialog =
                                    StaffDialog.create(object : StaffDialog.IListener {
                                        override fun delete() {
                                            try {
                                                viewModelBook.deleteBook(viewModelBook.itemBook.id)
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

    private fun showButtonSheetUpdate(book: Book) {
        val bottomSheetActionDialog =
            BookBottonSheetDialogFragment(this, "UpdateBook", book, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            BookBottonSheetDialogFragment.TAG
        )
    }

    private fun showButtonSheetAdd(book: Book) {
        val bottomSheetActionDialog =
            BookBottonSheetDialogFragment(this, "AddBook", book, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            BookBottonSheetDialogFragment.TAG
        )
    }

    override fun clickItem(idBook: Int, binding: ItemBookBinding) {
        viewModelBook.getItemBook(idBook)
        viewModelBook.book.observe(this) {
            val bottomSheetActionDialog =
                BookBottonSheetDialogFragment(this, "Book", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }

    override fun onClickText(text: String, bookItem: ListBookItem) {
        when (text) {
            "updateBook" -> {
                viewModelBook.updateBook(viewModelBook.itemBook.id, bookItem)
            }

            "addBook" -> {
                viewModelBook.creatBook(bookItem)
            }
        }
    }
}