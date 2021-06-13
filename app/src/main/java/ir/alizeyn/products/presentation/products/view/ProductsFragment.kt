package ir.alizeyn.products.presentation.products.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.products.core.ext.gone
import ir.alizeyn.products.core.ext.visible
import ir.alizeyn.products.core.state.StateData
import ir.alizeyn.products.databinding.FragmentProductsBinding
import ir.alizeyn.products.presentation.products.viewmodel.ProductsViewModel

const val PRODUCTS_SPAN_COUNT: Int = 2

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding: FragmentProductsBinding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()
    private val adapter: ProductAdapter by lazy { ProductAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        setupRecyclerView()

        viewModel.products.observe(viewLifecycleOwner, { productStateData ->
            when (productStateData) {
                is StateData.Loading -> {
                    binding.loadingGroup.visible()
                }
                is StateData.Success -> {
                    binding.loadingGroup.gone()
                    productStateData.data?.let {
                        adapter.updateData(it)
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

    private fun setupRecyclerView() {
        binding.productsRecyclerView.adapter = adapter
        binding.productsRecyclerView.layoutManager =
            GridLayoutManager(requireContext(), PRODUCTS_SPAN_COUNT)
    }
}