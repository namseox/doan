package com.kma.myapplication.utils

import android.R
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.amulyakhare.textdrawable.TextDrawable
import com.kma.myapplication.data.model.Book


@BindingAdapter("setImage")
fun ImageView.setImage(name: String){
    val drawable: TextDrawable = TextDrawable.builder().beginConfig()
        .endConfig()
        .buildRound(name.substring(0,1), resources.getColor(R.color.holo_green_light))

    this.setImageDrawable(drawable)

}

@BindingAdapter("setText")
fun TextView.setText(id: Int){
    this.text = "id: "+id.toString()
}
@BindingAdapter("setNameBook")
fun TextView.setNameBook(name: String){
    this.text = "Tên sách: "+name
}
@BindingAdapter("setTimeCreatBook")
fun TextView.setTimeCreatBook(time:Int){
    this.text = "Năm phát hành: " +time
}
@BindingAdapter("setTextDepartment")
fun TextView.setTextDepartment(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setIdStaff")
fun TextView.setIdStaff(id: Int){
    this.text = id.toString()
}

@BindingAdapter("setNumberSalary")
fun TextView.setNumberSalary(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setSalaryStep")
fun TextView.setSalaryStep(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setTextId")
fun TextView.setTextId(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setTextType")
fun TextView.setTextType(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setTextNumPage")
fun TextView.setTextNumPage(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setTextYear")
fun TextView.setTextYear(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setTextAuthor")
fun TextView.setTextAuthor(book: Book){
    if (book.users.size==1){
        this.text = book.users[0].name
    }else{
        this.text = book.users[0].name +", "+ book.users[1].name
    }
}