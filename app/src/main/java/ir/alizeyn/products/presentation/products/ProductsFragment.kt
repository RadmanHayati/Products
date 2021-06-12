package ir.alizeyn.products.presentation.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.products.R
import ir.alizeyn.products.core.ext.gone
import ir.alizeyn.products.core.ext.visible
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.databinding.FragmentProductsBinding


@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener {
            findNavController().navigate(R.id.action_productsFragment_to_detailFragment)
        }

        viewModel.products.observe(viewLifecycleOwner, {
            when (it) {
                is StateData.Loading -> {
                    binding.loadingGroup.visible()
                }
                is StateData.Success -> {
                    binding.loadingGroup.gone()
                    Log.i("TAG", "SUCCESS")
                    it.data?.forEach {
                        Log.i("TAG", "onCreateView: ${it.title}")
                    }
                }
                is StateData.Error -> {
                    Log.i("TAG", "ERROR")
                }
            }
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}