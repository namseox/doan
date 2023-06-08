package com.kma.myapplication.ui.article

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
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogArticleBinding
import com.kma.myapplication.databinding.FragmentBottomSheetDialogBookBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class ArticleBottomSheetDialogFragment(
    var onClickl: onClickBottomSheetArticle,
    var status: String,
    var itemArticle: ArticleItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogArticleBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeAricle = arrayListOf<String>("Tạp chí", "Hôi nghị")
    var listType = arrayListOf<String>(
        "Tạp chí Nature, AAAS",
        "Thuộc hệ thống ISI/Scopus(Q1)",
        "Thuộc hệ thống ISI/Scopus(Q2)",
        "Thuộc hệ thống ISI/Scopus(Q3)",
        "Thuộc hệ thống ISI/Scopus(Q4)",
        "Tạp chí quốc tế",
        "Tạp chí trong nước (điểm tối đa >=1)",
        "Tạp chí trong nước (điểm tối đa >=0.5)",
        "Tạp chí trong nước có chỉ số ISSN"
    )
    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "ArticleBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state = BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogArticleBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var aa = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeAricle)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var bb = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var cc = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listType)
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = listAuthor.indexOf(itemArticle.users[0].name)

        with(binding.spType) {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spTypeb) {
            adapter = aa
            setSelection(itemArticle.index_article, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUser) {
            adapter = bb
            setSelection(0, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spUserb) {
            adapter = bb
            setSelection(positionAuthor, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        with(binding.spTypeArticlescientific) {
            adapter = cc
            setSelection(0, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spTypeArticlescientificb) {
            adapter = cc
            setSelection(itemArticle.type, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.articleModel = itemArticle
        when (status) {
            "UpdateArticle" -> {
                binding.clArticleb.visibility = View.VISIBLE
            }

            "Article" -> {
                binding.clArticlea.visibility = View.VISIBLE
            }

            "AddArticle" -> {
                binding.clArticle.visibility = View.VISIBLE
            }
        }
        binding.btnUpdate.setOnClickListener {
            var itemList = ListArticleItem(
                binding.tvIdArticleb.text.toString(),
                0,
                listType.indexOf(binding.spTypeArticlescientificb.selectedItem.toString()),
                binding.tvNameArticleb.text.toString(),
                1,
                "1",
                "1",
                0,
                listTypeAricle.indexOf(binding.spTypeb.selectedItem.toString()),
                1
            )

            onClickl.onClickText("updateArticle", itemList)
            destroy()
        }
        binding.btnAdd.setOnClickListener {
            var itemList = ListArticleItem(
                binding.tvIdArticle.text.toString(),
                0,
                listType.indexOf(binding.spTypeArticlescientific.selectedItem.toString()),
                binding.tvNameArticle.text.toString(),
                1,
                "1",
                "1",
                0,
                listTypeAricle.indexOf(binding.spType.selectedItem.toString()),
                1
            )

            onClickl.onClickText("addArticle", itemList)
            destroy()
        }
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

interface onClickBottomSheetArticle {
    fun onClickText(text: String, item: ListArticleItem)
}
