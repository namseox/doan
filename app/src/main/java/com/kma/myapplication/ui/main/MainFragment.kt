package com.kma.myapplication.ui.main

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hola360.m3uplayer.data.response.DataResponse
import com.hola360.m3uplayer.data.response.LoadingStatus

import com.kma.myapplication.R
import com.kma.myapplication.data.model.DashboardClass
import com.kma.myapplication.data.model.ListArticleItem
import com.kma.myapplication.data.model.ListBookItem
import com.kma.myapplication.data.model.ListClassItem
import com.kma.myapplication.data.model.TopicItemMainItem
import com.kma.myapplication.data.model.Year
import com.kma.myapplication.databinding.FragmentMainBinding
import com.kma.myapplication.ui.expandabletlistview.Adapter
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.managerclass.AdapterClass
import com.kma.myapplication.utils.SharedViewModel
import com.kma.myapplication.utils.Utils.hideKeyboard

class MainFragment : AbsBaseFragment<FragmentMainBinding>(), AdapterView.OnItemSelectedListener {
    private lateinit var listViewAdapter: Adapter
    private lateinit var chapterList: List<String>
    private lateinit var topicList: HashMap<String, List<String>>
    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var viewModelMainFragment: ViewModelMainFragment
    private lateinit var adapter: AdapterDashbroadClass
    private lateinit var adapter2: AdapterlistTopicItemMainItem
    private lateinit var adapter3: AdapterArticle
    var listDashboardClass = listOf<DashboardClass>()
    var listTopicItemMainItem = listOf<TopicItemMainItem>()
    var listArticle = listOf<ListArticleItem>()
    var id_year = 0
    var listYear2 = listOf<Year>()
    val handler = Handler(Looper.myLooper()!!)


    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        handler.postDelayed(runnable, 1000)
        viewModelMainFragment = ViewModelMainFragment(requireContext())
        sharedViewModel = SharedViewModel.getInstance(requireContext())
        sharedViewModel.getListYear()

    }

    private val runnable = Runnable {
        loadExpandableListView()
        showExpandableListView()
        runView()
    }

    private fun showExpandableListView() {
        var listYear = arrayListOf<String>()
        SharedViewModel.getInstance(requireContext()).listYear.observe(this) {
            it?.let {
                listYear2 = it
                id_year = it[0].id
                sharedViewModel.yearId = id_year
                for (i in it) {
                    listYear.add(i.name)
                }
                sharedViewModel.arrayYear = listYear
                var aa = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    sharedViewModel.arrayYear
                )
                aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                with(binding.spYear) {
                    adapter = aa
                    setSelection(0, false)
                    onItemSelectedListener = this@MainFragment
                    prompt = "Select your favourite language"
                    gravity = android.view.Gravity.CENTER
                }
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            it.hideKeyboard()
            binding.drawerlayout.open()
        }


        var idELV = binding.root.findViewById<ExpandableListView>(R.id.ELVMain)
        listViewAdapter = Adapter(requireContext(), chapterList, topicList, idELV)
        idELV.setAdapter(listViewAdapter)
        idELV.setOnGroupClickListener { parent, _, groupPosition, _ ->
            when (groupPosition) {
                0 -> {
                    val action = MainFragmentDirections.actionMainFragmentToStaffFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                1 -> {
                    if (parent.isGroupExpanded(groupPosition)) {
                        parent.collapseGroup(groupPosition)
                    } else {
                        parent.expandGroup(groupPosition)
                        parent.setOnChildClickListener { parent, _, groupPosition, childPosition, _ ->
                            when (childPosition) {
                                0 -> {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToSubjectFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                1 -> {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToClassFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                2 -> {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToExamFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                3 -> {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToMonotorExamFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                4 -> {
                                    val action =
                                        MainFragmentDirections.actionMainFragmentToMarkExamFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                else -> {
                                    binding.drawerlayout.close()
                                }

                            }
                            parent.collapseGroup(groupPosition)
                        }
                    }
                }

                2 -> {

                    binding.drawerlayout.close()
                }

                3 -> {
                    binding.drawerlayout.close()
                }

                4 -> {
                    val action =
                        MainFragmentDirections.actionMainFragmentToScientificResearchFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                5 -> {
                    val action =
                        MainFragmentDirections.actionMainFragmentToScientificArticleFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                6 -> {
                    val action = MainFragmentDirections.actionMainFragmentToBookFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                7 -> {
                    val action = MainFragmentDirections.actionMainFragmentToPrizeFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                8 -> {
                    val action =
                        MainFragmentDirections.actionMainFragmentToEducationProgramFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                else -> {
                    binding.drawerlayout.close()
                }
            }
            true
        }
    }

    private fun runView() {
        adapter = AdapterDashbroadClass()
        adapter2 = AdapterlistTopicItemMainItem()
        adapter3 = AdapterArticle()
//        adapterClass = AdapterClass(this)//
//        adapterClass = AdapterClass(this)
        upData()
    }

    private fun upData() {
        viewModelMainFragment.getListDashboardClass(id_year, sharedViewModel.user.user.id)
        viewModelMainFragment.getListTopicItemMainItem(sharedViewModel.user.user.id)
        viewModelMainFragment.getListArticle()
        viewModelMainFragment.listDashboardClassItem.observe(this) {
            it?.let {
                listDashboardClass = it as List<DashboardClass>
                Log.d("TAG", "upData:,,, " + listDashboardClass)
                setAudioRecycleView1()
            }
        }
        viewModelMainFragment.listTopicItemMainItem.observe(this) {
            it?.let {
                listTopicItemMainItem = it as List<TopicItemMainItem>
                Log.d("TAG", "upData:,,,listTopicItemMainItem " + listTopicItemMainItem)
                setAudioRecycleView2()
            }
        }
        viewModelMainFragment.listArticle.observe(this) {
            it?.let {
                listArticle = it as List<ListArticleItem>
                setAudioRecycleView3()
            }
        }
        binding.cpiLoading.visibility = View.GONE
    }

    private fun setAudioRecycleView1() {
        adapter.getValueData(listDashboardClass)

        binding.rcv1.adapter = adapter
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcv1.layoutManager = manager
    }

    private fun setAudioRecycleView2() {
        adapter2.getValueData(listTopicItemMainItem)
        binding.rcv2.adapter = adapter2
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcv2.layoutManager = manager
    }

    private fun setAudioRecycleView3() {
        adapter3.getValueData(listArticle)
        binding.rcv3.adapter = adapter3
        var manager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcv3.layoutManager = manager
    }

    private fun loadExpandableListView() {
        chapterList = ArrayList()
        topicList = HashMap()
        (chapterList as ArrayList<String>).add("Nhân viên")
        (chapterList as ArrayList<String>).add("Quản lý giảng dạy")
        (chapterList as ArrayList<String>).add("Đánh giá học phần")
        (chapterList as ArrayList<String>).add("Luận án/Luận văn")
        (chapterList as ArrayList<String>).add("Nghiên cứu khoa học")
        (chapterList as ArrayList<String>).add("Bài báo khoa học")
        (chapterList as ArrayList<String>).add("Sách/giáo trình suất bản")
        (chapterList as ArrayList<String>).add("Bằng sáng chế giải thưởng khoa học")
        (chapterList as ArrayList<String>).add("Xây dựng chương trình đào tạo")

        val topic2: MutableList<String> = ArrayList()
        topic2.add("Quản lý môn học")
        topic2.add("Quản lý lớp học")
        topic2.add("Quản lý đề thi")
        topic2.add("Quản lý coi thi thi")
        topic2.add("Quản lý chấm thi")

        topicList[chapterList[1]] = topic2

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("TAG", "onItemSelected:00 " + listYear2[position].id + "     " + position)
        id_year = listYear2[position].id
        sharedViewModel.yearId = id_year
        viewModelMainFragment.getListDashboardClass(id_year, sharedViewModel.user.user.id)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(requireContext(), "ko chon gi", Toast.LENGTH_SHORT)
    }


}