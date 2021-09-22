package openfoodfacts.rackluxury.scrachx.openfood.features.categories.adapter

import androidx.recyclerview.widget.RecyclerView
import openfoodfacts.rackluxury.scrachx.openfood.databinding.CategoryRecyclerItemBinding
import openfoodfacts.rackluxury.scrachx.openfood.features.search.ProductSearchActivity.Companion.start
import openfoodfacts.rackluxury.scrachx.openfood.models.entities.category.CategoryName
import openfoodfacts.rackluxury.scrachx.openfood.utils.SearchType

/**
 * Created by Abdelali Eramli on 27/12/2017.
 */
class CategoryViewHolder(private val binding: CategoryRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(category: CategoryName) {
        binding.itemCategoryName.text = category.name
        binding.root.setOnClickListener {
            start(binding.root.context, SearchType.CATEGORY, category.categoryTag!!, category.name!!)
        }
    }
}
