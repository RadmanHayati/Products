package ir.alizeyn.products.presentation.products

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import ir.alizeyn.products.core.ext.gone
import ir.alizeyn.products.databinding.ItemProductBinding
import ir.alizeyn.products.domain.product.model.Product

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private var data: List<Product> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateData(data: List<Product>) {
        this@ProductAdapter.data = data
        notifyDataSetChanged()
    }

    class ProductViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.productTitle.text = product.title
            //todo fix formatting
            binding.productPrice.text = product.price.toString()
            binding.strikePriceGroup.gone()
//            product.strikePrice?.let {
//                binding.strikePriceGroup.visible()
//                binding.productStrikePrice.strike()
//                binding.productStrikePrice.text = it.toString()
//            }
            Log.i("TAG", "bind: imageUrl is ${product.imageUrl}")
            binding.productImageView.load(product.imageUrl.replace("http", "https"))
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