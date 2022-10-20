package com.ahmedroid.ui.list

import androidx.lifecycle.*
import com.ahmedroid.domain.entity.CountryFilter
import com.ahmedroid.domain.entity.Filter
import com.ahmedroid.domain.entity.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import repos.NewsApiRepo
import repos.NewsDataRepo
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(
    private val newsApiRepo: NewsApiRepo,
    private val newsDataRepo: NewsDataRepo,
) : ViewModel() {

    private val _state = MutableLiveData<Resource<*>>()
    val state: LiveData<Resource<*>> get() = _state

    val selectedFilters: MutableMap<String, Filter> = mutableMapOf("country" to CountryFilter.AE)

    init {
        fetchItems()
    }

    fun fetchItems() = viewModelScope.launch {
        val requestFilters = selectedFilters.mapValues { it.value.filterKey }
        _state.value = Resource.Loading(true)
        try {
            val newsDataList = withContext(Dispatchers.IO) { newsDataRepo.getNewsWithFilters(requestFilters) }
            val newsApiList = withContext(Dispatchers.IO) { newsApiRepo.getNewsWithFilters(requestFilters) }
            _state.value = Resource.Success(newsDataList + newsApiList)
        } catch (e: Exception) {
            _state.value = Resource.Error(0, e.message ?: "")
            _state.value = Resource.Loading(false)
        }
        _state.value = Resource.Loading(false)
    }
}
