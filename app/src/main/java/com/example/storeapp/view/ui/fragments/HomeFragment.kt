package com.example.storeapp.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.storeapp.R
import com.example.storeapp.databinding.FragmentHomeBinding
import com.example.storeapp.view.BaseComponents.BaseFragment
import com.example.storeapp.view.Navigators.HomeNavigator
import com.example.storeapp.view.viewModel.fragments.HomeFragmentViewModel

private const val TAG = "HomeFragment"

class HomeFragment : BaseFragment(), HomeNavigator {
    lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = HomeFragmentViewModel(requireActivity(), viewLifecycleOwner, this)
//        viewModel.(productId, requireActivity())
        binding.vm = viewModel

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textView.text = "aaa"
//        viewModel.branchAPI(requireContext())
    }

    override fun showProgressBar() {
        TODO("Not yet implemented")
    }

    override fun hideProgressBar() {
        TODO("Not yet implemented")
    }

//    override fun onBranchesResponse(response: BranchesResponse) {
//        textView.text = response.data[0].name
//        Log.d(TAG, "onBranchesResponse: $response")
//    }

//    override fun showProgressBar() {
//        progress_circular.visibility = View.VISIBLE
//    }
//
//    override fun hideProgressBar() {
//        progress_circular.visibility = View.GONE
//    }

}