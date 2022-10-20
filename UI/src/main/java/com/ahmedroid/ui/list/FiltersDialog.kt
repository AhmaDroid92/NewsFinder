package com.ahmedroid.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import com.ahmedroid.domain.entity.CategoryFilter
import com.ahmedroid.domain.entity.CountryFilter
import com.ahmedroid.ui.R
import com.ahmedroid.ui.databinding.DialogFiltersBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FiltersDialog : BottomSheetDialogFragment() {

    private var _binding: DialogFiltersBinding? = null
    private val binding get() = _binding!!

    private val newsListViewModel: NewsListViewModel
            by hiltNavGraphViewModels(R.id.main_navigation_graph_xml)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogFiltersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            var selectedCountry: String? = newsListViewModel.selectedFilters[COUNTRY_KEY]?.filterKey
            var selectedCategory: String? = newsListViewModel.selectedFilters[CATEGORY_KEY]?.filterKey

            countryRadioGroup.findViewWithTag<RadioButton>(selectedCountry)?.isChecked = true
            categoryRadioGroup.findViewWithTag<RadioButton>(selectedCategory)?.isChecked = true

            countryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                selectedCountry = group.findViewById<RadioButton>(checkedId).tag as String
            }

            categoryRadioGroup.setOnCheckedChangeListener { group, checkedId ->
                selectedCategory = group.findViewById<RadioButton>(checkedId).tag as String
            }

            applyButton.setOnClickListener {
                selectedCountry?.let {
                    newsListViewModel.selectedFilters[COUNTRY_KEY] = CountryFilter.valueOf(it)
                }
                selectedCategory?.let {
                    newsListViewModel.selectedFilters[CATEGORY_KEY] = CategoryFilter.valueOf(it)
                }

                newsListViewModel.fetchItems()
                this@FiltersDialog.dismiss()
            }
        }
    }

    companion object {
        const val COUNTRY_KEY = "country"
        const val CATEGORY_KEY = "category"
    }
}
