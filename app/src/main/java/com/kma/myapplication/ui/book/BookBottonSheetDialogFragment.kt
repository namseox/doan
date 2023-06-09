package com.kma.myapplication.ui.book

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
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.Staff
import com.kma.myapplication.databinding.FragmentBottomSheetDialogBookBinding
import com.kma.myapplication.utils.SharedPreferenceUtils

class BookBottonSheetDialogFragment(
    var onClickl: onClickBottomSheetBook,
    var status: String,
    var itemBook: Book,
    var ct: Context
) :
    BottomSheetDialogFragment(), AdapterView.OnItemSelectedListener {
    lateinit var binding: FragmentBottomSheetDialogBookBinding
    var a: Staff = SharedPreferenceUtils.getInstance(ct).getObjModel()!!

    var listAuthor = ArrayList<String>()
    val NEW_SPINNER_ID = 1

    companion object {
        const val TAG = "BookBottomSheet"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (dialog as BottomSheetDialog).behavior.state =
            BottomSheetBehavior.STATE_EXPANDED
        binding = FragmentBottomSheetDialogBookBinding.inflate(layoutInflater, container, false)
        (a.a).forEach {
            listAuthor.add(it.name)
        }


        var aa = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, listAuthor)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        var positionAuthor_1 = -1
        var positionAuthor_2 = -1
        try {
            positionAuthor_1 = listAuthor.indexOf(itemBook.users[0].name)
            positionAuthor_2 = listAuthor.indexOf(itemBook.users[1].name)
        } catch (e: Exception) {
            null
        }
        with(binding.spAuthor1) {
            adapter = aa
            setSelection(positionAuthor_1, false)
            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor2) {
            adapter = aa
            if (positionAuthor_2 != -1) {
                setSelection(positionAuthor_2, false)
            }

            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor1B) {
            adapter = aa
            setSelection(positionAuthor_1, false)
            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = Gravity.CENTER
        }
        with(binding.spAuthor2B) {
            adapter = aa
            if (positionAuthor_2 != -1) {
                setSelection(positionAuthor_2, false)
            }

            onItemSelectedListener = this@BookBottonSheetDialogFragment
            prompt = "Chọn tác giả"
            gravity = android.view.Gravity.CENTER
        }
        val spinner = Spinner(requireContext())
        spinner.id = NEW_SPINNER_ID
        binding.bookModel = itemBook
        when (status) {
            "UpdateBook" -> {
                binding.standardBottomSheet.visibility = View.VISIBLE

            }

            "Book" -> {
                binding.standardBottomSheetA.visibility = View.VISIBLE
            }

            "AddBook" -> {
                binding.standardBottomSheetB.visibility = View.VISIBLE
            }
        }
        binding.btnCancel.setOnClickListener {
            destroy()
        }
        binding.btnUpdate.setOnClickListener {
            var itemListBook = ListBookItem(
                binding.rtCode.text.toString(),
                0,
                binding.tvNameBook.text.toString(),
                binding.tvNumberPage.text.toString().toInt(),
                2,
                binding.tvYear.text.toString().toInt(),
                0,
                0,
                1,
                binding.spAuthor1.selectedItem.toString() + "," + binding.spAuthor2.selectedItem.toString()
            )

            onClickl.onClickText("updateBook", itemListBook)
            destroy()
        }
        binding.btnAdd.setOnClickListener {
            var itemListBook = ListBookItem(
                binding.rtCodeB.text.toString(),
                0,
                binding.tvNameBookB.text.toString(),
                binding.tvNumberPageB.text.toString().toInt(),
                2,
                binding.tvYearB.text.toString().toInt(),
                0,
                0,
                1,
                binding.spAuthor1B.selectedItem.toString() + "," + binding.spAuthor2B.selectedItem.toString()
            )

            onClickl.onClickText("addBook", itemListBook)
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

interface onClickBottomSheetBook {
    fun onClickText(text: String, bookItem: ListBookItem)
}
