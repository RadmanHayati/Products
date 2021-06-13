package ir.alizeyn.products.presentation.products.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.alizeyn.products.R
import ir.alizeyn.products.core.ext.invisible
import ir.alizeyn.products.core.ext.visible
import ir.alizeyn.products.databinding.ItemProductBinding
import ir.alizeyn.products.presentation.products.model.ProductUiModel

const val ADAPTER_ANIMATOR_DURATION: Int = 500

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data: List<ProductUiModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<ProductUiModel>) {
        this@ProductAdapter.data = data
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: ProductUiModel) {
            binding.productTitle.text = product.title
            binding.productPrice.text = product.price
            binding.productStrikePrice.invisible()
            product.strikePrice?.let {
                binding.productStrikePrice.visible()
                binding.productStrikePrice.text = it
            }
            Log.i("TAG", "bind: imageUrl is ${product.imageUrl}")
            binding.productImageView.load(product.imageUrl.replace("http", "https")) {
                placeholder(R.drawable.placeholder_product)
                crossfade(true)
            }
            binding.root.setOnClickListener {
                val action = ProductsFragmentDirections.actionProductsFragmentToDetailFragment()
                binding.root.findNavController().navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ProductViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ItemProductBinding.inflate(inflater, parent, false)
                return ProductViewHolder(binding)
            }
        }

    }
}