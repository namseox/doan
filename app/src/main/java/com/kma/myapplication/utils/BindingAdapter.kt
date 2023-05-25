package com.kma.myapplication.utils

import android.R
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.amulyakhare.textdrawable.TextDrawable


@BindingAdapter("setImage")
fun ImageView.setImage(name: String){
    val drawable: TextDrawable = TextDrawable.builder().beginConfig()
        .endConfig()
        .buildRound(name.substring(0,1), resources.getColor(R.color.holo_green_light))

    this.setImageDrawable(drawable)

}

@BindingAdapter("setText")
fun TextView.setText(id: Int){
    this.text = id.toString()
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


