package com.pitch.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel


abstract class BaseFragment<VB : ViewDataBinding, VM : ViewModel> : Fragment() {

    abstract val viewModel: VM
    open lateinit var binding: VB

    private fun init(inflater: LayoutInflater, container: ViewGroup) {
        binding = DataBindingUtil.inflate(inflater, getLayoutRes(), container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        init(inflater, container!!)
        super.onCreateView(inflater, container, savedInstanceState)
        // binding.setVariable(BR.viewModel, viewModel)
        initViewModel(viewModel)
        return binding.root
    }

    abstract fun initViewModel(viewModel: VM)

    @LayoutRes
    abstract fun getLayoutRes(): Int
}