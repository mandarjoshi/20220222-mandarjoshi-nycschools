package com.mandarjoshi.nycschools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.mandarjoshi.nycschools.viewmodel.SchoolViewModel
import com.mandarjoshi.nycschools.viewmodel.ViewModelFactory

import javax.inject.Inject
import com.mandarjoshi.nycschools.model.SchoolScore

import com.mandarjoshi.nycschools.databinding.FragmentSchoolScoresBinding
import com.mandarjoshi.nycschools.NycSchoolApplication

import androidx.lifecycle.Observer
import com.mandarjoshi.nycschools.R
import com.mandarjoshi.nycschools.util.Constants
import com.mandarjoshi.nycschools.util.DialogUtil.getNoDataDialog
import com.mandarjoshi.nycschools.util.DialogUtil.getSimpleErrorDialog
import com.mandarjoshi.nycschools.util.Resource
import com.mandarjoshi.nycschools.util.Status


class SchoolScoresFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mViewModel: SchoolViewModel by activityViewModels { viewModelFactory }

    private lateinit var id: String
    private lateinit var binding: FragmentSchoolScoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as NycSchoolApplication).appComponent.inject(this)
        mViewModel.getSchoolScore().observe(this,schoolScoreObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_school_scores, container, false)
        arguments?.getString(Constants.SCHOOL_ID_KEY)?.let {
            id = it
        }
        binding.viewModel = mViewModel
        return binding.root
    }

    private val schoolScoreObserver: Observer<Resource<List<SchoolScore>?>> =
        Observer<Resource<List<SchoolScore>?>> { scores ->
            when(scores.status){
                Status.ERROR -> {
                    hideProgressBar(binding.root)
                    getSimpleErrorDialog(requireActivity()).show()
                } Status.SUCCESS -> {
                    hideProgressBar(binding.root)
                    mViewModel.refreshSchoolScores(scores)
                    scores.data?.let {
                        if (it.isNotEmpty()) {
                            mViewModel.scoreMap[id]?.let { schoolScore ->
                                binding.score = schoolScore
                            } ?: getNoDataDialog(requireActivity()).show()
                        }

                    }
                } else -> { showProgressBar(binding.root) }
            }
        }

}
