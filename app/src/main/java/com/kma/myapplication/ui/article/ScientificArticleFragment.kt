package com.kma.myapplication.ui.article

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
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ClassItem
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.databinding.FragmentScientificArticleBinding
import com.kma.myapplication.databinding.ItemArticleBinding
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.book.AdapterBook
import com.kma.myapplication.ui.book.BookBottonSheetDialogFragment
import com.kma.myapplication.ui.book.ViewModelBookFragment
import com.kma.myapplication.ui.dialog.DeleteDialog
import com.kma.myapplication.ui.staff.StaffBottonSheetDialogFragment
import com.kma.myapplication.ui.staff.onClickBottomSheet
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils
import com.kma.myapplication.utils.Utils.setSafeMenuClickListener

class ScientificArticleFragment: AbsBaseFragment<FragmentScientificArticleBinding>(),onCLickArticle,onClickBottomSheetArticle {
    private val listActionPopup by lazy { ListActionPopup(requireActivity()) }
    lateinit var viewModelArticle: ViewModelArticleFragment
    lateinit var adapterArticle: AdapterArticle
    var listArticle = listOf<ListArticleItem>()
    val handler = Handler(Looper.myLooper()!!)
    override fun getLayout(): Int {
        return R.layout.fragment_scientific_article
    }

    override fun initView() {
        viewModelArticle = ViewModelArticleFragment(Application())

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
                    showButtonSheetAdd(ArticleItem())
                }
            }
        }
    }
    private val runnable = Runnable {
        runView()
    }
    private fun runView() {
        adapterArticle = AdapterArticle(this)
        upData()
    }
    private fun upData() {
        viewModelArticle.getListBook()
        viewModelArticle.listArticleItem.observe(viewLifecycleOwner) {
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
                        listArticle = body as List<ListArticleItem>
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
        viewModelArticle.value_delete.observe(viewLifecycleOwner) {
            it?.let {
                if (it == 1) {
                    adapterArticle.deleteArticle(viewModelArticle.itemBook)
                    Toast.makeText(requireContext(), "Xoá thành công", Toast.LENGTH_SHORT).show()
                }

            }
        }
        viewModelArticle.value_creat.observe(viewLifecycleOwner) {
                adapterArticle.creatBook(it)
                Toast.makeText(requireContext(), "Tạo thành công", Toast.LENGTH_SHORT).show()

        }
        viewModelArticle.value_updtae.observe(viewLifecycleOwner) {
            Log.d("TAG", "upData000000: "+it)
            adapterArticle.updateBook(it)
            Toast.makeText(requireContext(), "Cập nhật thành công", Toast.LENGTH_SHORT)
                .show()

        }
    }
    private fun setAudioRecycleView() {
        adapterArticle.getData(listArticle)

        binding.rcvStaff.adapter = adapterArticle
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcvStaff.layoutManager = manager
    }
    override fun click3Dot(id: Int, binding: ItemArticleBinding) {

        listActionPopup.showPopup(
            binding.iv3dot,
            Utils.actions,
            object : ActionAdapter.OnActionClickListener {
                override fun onItemActionClick(position: Int) {
                    when (position) {
                        0 -> {
                            viewModelArticle.getItemBook2(id)
                            viewModelArticle.article2.observe(viewLifecycleOwner) {
                                Log.d("TAG", "onItemActionClick: ...........")
                                showButtonSheetAdd(it)
                            }
                        }

                        1 -> {
                            viewModelArticle.getItemBook2(id)
                            viewModelArticle.article2.observe(viewLifecycleOwner) {
                                showButtonSheetUpdate(it)
                            }
                        }

                        else -> {
                            var myFileDialog =
                                DeleteDialog.create(object : DeleteDialog.IListener {
                                    override fun delete() {
                                        try {
                                            viewModelArticle.deleteBook(viewModelArticle.itemBook.id)
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
    private fun showButtonSheetAdd(book: ArticleItem) {
        val bottomSheetActionDialog =
            ArticleBottomSheetDialogFragment(this, "AddArticle", book, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            BookBottonSheetDialogFragment.TAG
        )
    }
    private fun showButtonSheetUpdate(book: ArticleItem) {
        val bottomSheetActionDialog =
            ArticleBottomSheetDialogFragment(this, "UpdateArticle", book, requireContext())
        bottomSheetActionDialog.show(
            requireActivity().supportFragmentManager,
            BookBottonSheetDialogFragment.TAG
        )
    }
    override fun clickItem(id: Int, binding: ItemArticleBinding) {
        viewModelArticle.getItemBook(id)
        viewModelArticle.article.observe(this) {
            val bottomSheetActionDialog =
                ArticleBottomSheetDialogFragment(this, "Article", it, requireContext())
            SharedPreferenceUtils.getInstance(requireContext()).setObjModel(it)
            bottomSheetActionDialog.show(
                requireActivity().supportFragmentManager,
                StaffBottonSheetDialogFragment.TAG
            )
        }
    }

    override fun onClickText(text: String, item: ListArticleItem) {
        when (text) {
            "updateArticle" -> {
                viewModelArticle.updateBook(viewModelArticle.itemBook.id, item)
            }

            "addArticle" -> {
                viewModelArticle.creatBook(item)
            }
        }
    }
}