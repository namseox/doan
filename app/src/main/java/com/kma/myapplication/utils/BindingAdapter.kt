package com.kma.myapplication.utils

import android.R
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.amulyakhare.textdrawable.TextDrawable
import com.kma.myapplication.data.model.Book
import com.kma.myapplication.data.model.UserXX


@BindingAdapter("setImage")
fun ImageView.setImage(name: String){
    val drawable: TextDrawable = TextDrawable.builder().beginConfig()
        .endConfig()
        .buildRound(name.substring(0,1), resources.getColor(R.color.holo_green_light))

    this.setImageDrawable(drawable)

}

@BindingAdapter("setTextInt")
fun TextView.setTextInt(id: Int){
    this.text = id.toString()
}
@BindingAdapter("setNameBook")
fun TextView.setNameBook(name: String){
    this.text = "Tên sách: "+name
}
@BindingAdapter("setTimeCreatBook")
fun TextView.setTimeCreatBook(time:Int){
    this.text = "Năm phát hành: " +time
}
@BindingAdapter("setTextIDStaff")
fun TextView.setTextIDStaff(id:Int){
    this.text = "ID: " +id
}

@BindingAdapter("setTextUser")
fun TextView.setTextUser(listUser: List<UserXX>){
    var text : String =""
    for (i in listUser){
        text = text+i+", "
    }
        this.text = text.substring(0,text.length-2)

}

@BindingAdapter("setTextDate")
fun TextView.setTextDate(date: String){
this.text = date.substring(0,10)
}
@BindingAdapter("setFormExam")
fun TextView.
        setFormExam(type: Int){
    this.text =
    when(type){
        0 ->{"Tự luận"}
        1->{"Trắc nghiệm"}
        2->{"Vấn đáp, thực hành"}
        else->{"Tiểu luận"}
    }
}
@BindingAdapter("setLevelInvention")
fun TextView.setLevelInvention(type: Int){
    this.text =
        when(type){
            0 ->{"Bằng độc quyền sáng chế"}
            1->{"Giải thưởng khoa học và công nghệ cấp quốc gia"}
            2->{"Giải thưởng khoa học và công nghệ từ cấp Bộ trở lên"}
            3->{"Giải thưởng khoa học và công nghệ cấp dưới Bộ"}
            else->{"Giải pháp hữu ích"}
        }
}
//@BindingAdapter("setTypeMark")
//fun TextView.setTypeMark(type: Int){
//    this.text =
//        when(type){
//            0 ->{"Bằng độc quyền sáng chế"}
//            1->{"Giải thưởng khoa học và công nghệ cấp quốc gia"}
//            2->{"Giải thưởng khoa học và công nghệ từ cấp Bộ trở lên"}
//            3->{"Giải thưởng khoa học và công nghệ cấp dưới Bộ"}
//            else->{"Giải pháp hữu ích"}
//        }
//}

