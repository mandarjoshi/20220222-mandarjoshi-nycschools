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

import androidx.lifecycle.LiveData
import com.mandarjoshi.nycschools.databinding.FragmentSchoolScoresBinding
import com.mandarjoshi.nycschools.NycSchoolApplication

import androidx.lifecycle.Observer
import com.mandarjoshi.nycschools.R
import com.mandarjoshi.nycschools.util.Constants
import com.mandarjoshi.nycschools.util.DialogUtil.getNoDataDialog
import com.mandarjoshi.nycschools.util.DialogUtil.getSimpleErrorDialog


class SchoolScoresFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val mViewModel: SchoolViewModel by activityViewModels { viewModelFactory }

    private lateinit var schoolScores: LiveData<List<SchoolScore>?>

    private lateinit var id: String
    private lateinit var binding: FragmentSchoolScoresBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().applicationContext as NycSchoolApplication).appComponent.inject(this)
        schoolScores = mViewModel.getSchoolScore()
        schoolScores.observe(this,schoolScoreObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_school_scores, container, false)
        arguments?.getString(Constants.SCHOOL_ID_KEY)?.let {
            id = it
        }
        showProgressBar(binding.root)
        binding.viewModel = mViewModel
        if(schoolScores.value != null){
            binding.score = mViewModel.scoreMap[id]
        }
        return binding.root
    }

    private val schoolScoreObserver: Observer<List<SchoolScore>?> =
        Observer<List<SchoolScore>?> { scores ->
            hideProgressBar(binding.root)
            if (scores == null) {
                getSimpleErrorDialog(requireActivity()).show()
            } else {
                if (scores.isNotEmpty()) {
                    mViewModel.scoreMap[id]?.let {
                        binding.score = it
                    } ?: getNoDataDialog(requireActivity()).show()
                }
            }
            hideProgressBar(binding.root)
        }

}
