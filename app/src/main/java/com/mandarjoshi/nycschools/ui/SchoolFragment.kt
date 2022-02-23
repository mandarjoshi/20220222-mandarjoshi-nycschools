package com.mandarjoshi.nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandarjoshi.nycschools.util.DialogUtil

import androidx.navigation.fragment.NavHostFragment
import com.mandarjoshi.nycschools.util.Constants

import com.mandarjoshi.nycschools.databinding.FragmentSchoolListBinding
import com.mandarjoshi.nycschools.viewmodel.SchoolViewModel
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory
import javax.inject.Inject
import com.mandarjoshi.nycschools.NycSchoolApplication
import com.mandarjoshi.nycschools.model.SchoolDetails

import com.mandarjoshi.nycschools.util.Resource
import com.mandarjoshi.nycschools.util.Status

class SchoolFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mViewModel: SchoolViewModel by activityViewModels { viewModelFactory }

    private lateinit var binding: FragmentSchoolListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as NycSchoolApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSchoolListBinding.inflate(inflater, container, false)
        binding.schoolList.layoutManager = LinearLayoutManager(context)

        mViewModel.getSchoolList().observe(this, schoolListObserver)
        return binding.root
    }

    private fun navigateToScores(id: String) {
        val bundle = Bundle()
        bundle.putString(Constants.SCHOOL_ID_KEY, id)
        NavHostFragment.findNavController(this)
            .navigate(com.mandarjoshi.nycschools.R.id.navigate_to_school_scores, bundle)
    }

    private val schoolListObserver: Observer<Resource<List<SchoolDetails>?>> =
        Observer<Resource<List<SchoolDetails>?>> { list ->
            when(list.status) {
                Status.ERROR -> {
                    hideProgressBar(binding.root)
                    DialogUtil.getSimpleErrorDialog(requireActivity()).show()
                }
                Status.SUCCESS -> {
                    hideProgressBar(binding.root)
                    mViewModel.refreshSchoolList(list)
                    list.data?.let {
                        if(it.isEmpty()){
                            DialogUtil.getNoDataDialog(requireActivity()).show()
                        } else {
                            binding.schoolList.adapter =
                                MySchoolAdapter(list.data) { id -> navigateToScores(id) }
                        }
                    }

                }
                else -> showProgressBar(binding.root)
            }
        }

}
