package com.hegunhee.newsimplememoapp.ui.statics

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.isGone
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hegunhee.newsimplememoapp.R
import com.hegunhee.newsimplememoapp.databinding.FragmentStaticsBinding
import com.hegunhee.newsimplememoapp.ui.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class StaticsFragment : BaseFragment<FragmentStaticsBinding>(R.layout.fragment_statics){

    val viewModel: StaticViewModel by viewModel()
    private val adapter = StaticsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewmodel = viewModel
            lifecycleOwner = this@StaticsFragment
            recyclerview.adapter = adapter
        }
        viewModel.initDate()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.setData()
    }

    @SuppressLint("SetTextI18n")
    private fun initObserver() = viewModel.staticsData.observe(viewLifecycleOwner){
        when(it){
            StaticsState.Uninitialized -> { }
            is StaticsState.Success -> {
                adapter.setData(it.list)
            }
            StaticsState.EmptyOrNull ->{ }

        }
    }

    companion object {
        const val TAG = "statics"
        fun newInstance() = StaticsFragment()
    }
}