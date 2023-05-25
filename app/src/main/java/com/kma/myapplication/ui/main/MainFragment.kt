package com.kma.myapplication.ui.main

import android.app.Application
import android.widget.ExpandableListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController

import com.kma.myapplication.R
import com.kma.myapplication.databinding.FragmentMainBinding
import com.kma.myapplication.ui.ExpandabletListView.Adapter
import com.kma.myapplication.ui.base.AbsBaseFragment
import com.kma.myapplication.ui.splash.ConfirmFragment
import com.kma.myapplication.ui.splash.onClick
import com.kma.myapplication.utils.SharedPreferenceUtils
import com.kma.myapplication.utils.Utils.hideKeyboard

class MainFragment : AbsBaseFragment<FragmentMainBinding>() {
    private lateinit var listViewAdapter: Adapter
    private lateinit var chapterList: List<String>
    private lateinit var topicList: HashMap<String, List<String>>
    override fun getLayout(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        show()
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
                                    val action = MainFragmentDirections.actionMainFragmentToSubjectFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                1 -> {
                                    val action = MainFragmentDirections.actionMainFragmentToClassFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                2 -> {
                                    val action = MainFragmentDirections.actionMainFragmentToExamFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                3 -> {
                                    val action = MainFragmentDirections.actionMainFragmentToMonotorExamFragment()
                                    findNavController().navigate(action)
                                    binding.drawerlayout.close()
                                }

                                4 -> {
                                    val action = MainFragmentDirections.actionMainFragmentToMarkExamFragment()
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
                    val action = MainFragmentDirections.actionMainFragmentToScientificResearchFragment()
                    findNavController().navigate(action)
                    binding.drawerlayout.close()
                }

                5 -> {
                    val action = MainFragmentDirections.actionMainFragmentToScientificArticleFragment()
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
                    val action = MainFragmentDirections.actionMainFragmentToEducationProgramFragment()
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



    private fun show() {
        chapterList = ArrayList()
        topicList = HashMap()
        (chapterList as ArrayList<String>).add("Nhân viên")
        (chapterList as ArrayList<String>).add("Quản lý giảng dạy")
        (chapterList as ArrayList<String>).add("Đánh giá học phần")
        (chapterList as ArrayList<String>).add("Luận án/lLuận văn")
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


}