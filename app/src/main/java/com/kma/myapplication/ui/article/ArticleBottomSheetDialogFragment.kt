package com.kma.myapplication.ui.article

import android.R
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.kma.myapplication.data.model.ArticleCreat
import com.kma.myapplication.data.model.ArticleItem
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogArticleBinding
import com.kma.myapplication.databinding.FragmentBottomSheetDialogBookBinding
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.SharedViewModel

class ArticleBottomSheetDialogFragment(
    var onClickl: onClickBottomSheetArticle,
    var status: String,
    var itemArticle: ArticleItem,
    var ct: Context
) : BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    var sharedViewModel = SharedViewModel.getInstance(ct)
    lateinit var binding: FragmentBottomSheetDialogArticleBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!
    var listTypeAricle = arrayListOf<String>("Tạp chí", "Hôi nghị")
    var listYear = sharedViewModel.arrayYear
    var listYearId = sharedViewModel.listYearGetId
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


        Log.d(TAG, "onCreateView: 11111")
        var aa = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listTypeAricle)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var bb = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        bb.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var cc = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listType)
        cc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        var positionAuthor = listAuthor.indexOf(itemArticle.users[0].name)
        var dd = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listYear)
        dd.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        with(binding.spYear) {
            adapter = dd
            setSelection(0, false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spYearb) {
            adapter = dd
            setSelection(listYear.indexOf(itemArticle.year.name), false)
            onItemSelectedListener = this@ArticleBottomSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }

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

        Log.d(TAG, "onCreateView000: "+itemArticle)


        if (itemArticle.name.isNullOrBlank()){
            Log.d(TAG, "onCreateView: 11111")
            itemArticle= ArticleItem()
        }else{

        }
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
            var id = 0

            var itemList = ArticleCreat(
                binding.tvNameArticleb.text.toString(),
                binding.tvIdArticleb.text.toString(),
                a.a[binding.spUserb.selectedItemId.toInt()].id.toString(),
                "1",
                listTypeAricle.indexOf(binding.spTypeb.selectedItem.toString()).toString(),
                "1",
                "1",
                listType.indexOf(binding.spTypeArticlescientificb.selectedItem).toString(),
                listYearId[binding.spYearb.selectedItemId.toInt()].id.toString()
            )

            onClickl.onClickText("updateArticle", itemList)
            destroy()
        }
        binding.btnAdd.setOnClickListener {

            var itemList = ArticleCreat(
                binding.tvNameArticle.text.toString(),
                binding.tvIdArticle.text.toString(),
                a.a[binding.spUser.selectedItemId.toInt()].id.toString(),
                "1",
                listTypeAricle.indexOf(binding.spType.selectedItem.toString()).toString(),
                "1",
                "1",
                listType.indexOf(binding.spTypeArticlescientific.selectedItem).toString(),
                listYearId[binding.spYear.selectedItemId.toInt()].id.toString()
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
    fun onClickText(text: String, item: ArticleCreat)
}
