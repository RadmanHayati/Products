package ir.alizeyn.products.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import ir.alizeyn.products.R
import ir.alizeyn.products.databinding.FragmentDetailBinding


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args by navArgs<DetailFragmentArgs>()
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        val product = args.product

        binding.apply {
            //todo fix hardcode network scheme
            productImage.load(product.imageUrl.replace("http", "https")) {
                placeholder(R.drawable.placeholder_product)
                crossfade(true)
            }
            productTitle.text = product.title
            productDescription.text = product.description
            productPrice.text = product.price
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}