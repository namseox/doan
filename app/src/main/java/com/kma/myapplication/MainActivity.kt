package com.kma.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ExpandableListView
import android.widget.TextView
import com.kma.myapplication.databinding.ActivityMainBinding
import com.kma.myapplication.databinding.ActivitySplashBinding
import com.kma.myapplication.ui.ExpandabletListView.Adapter
import com.kma.myapplication.ui.base.AbsBaseActivity

class MainActivity : AbsBaseActivity<ActivityMainBinding>() {


    override fun getFragmentID(): Int {
        return R.id.navContainerViewMain
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


}