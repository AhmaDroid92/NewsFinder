package com.ahmedroid.ui.list

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.ahmedroid.domain.entity.NewsEntity
import com.ahmedroid.domain.entity.Resource
import com.ahmedroid.ui.R
import com.ahmedroid.ui.databinding.FragmentNewsListBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null

    private val binding get() = _binding!! // if the view is not created then it must be crashing

    private val newsListViewModel: NewsListViewModel
            by hiltNavGraphViewModels(R.id.main_navigation_graph_xml)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)

        initViews()

        newsListViewModel.state.observe(viewLifecycleOwner, ::handleNewsResource)

        return binding.root
    }

    private fun initViews() {
        val layoutManager = object : GridLayoutManager(requireContext(), 1, VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return !binding.itemsRefreshLayout.isRefreshing
            }
        }

        binding.newsRecyclerView.apply {
            this.layoutManager = layoutManager

            val dividerItemDecoration = DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
            this.addItemDecoration(dividerItemDecoration)
        }

        binding.itemsRefreshLayout.setOnRefreshListener {
            newsListViewModel.fetchItems()
        }

        binding.filtersImageButton.setOnClickListener {
            findNavController().navigate(R.id.action_newsListFragment_to_filtersDialog)
        }
    }

    private fun handleNewsResource(event: Resource<*>) {
        when (event) {
            is Resource.Success -> {
                binding.newsRecyclerView.adapter = NewsAdapter(event.data as List<NewsEntity>) { newsEntity ->
                    if (!binding.itemsRefreshLayout.isRefreshing) {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(newsEntity.newsUrl)))
                    }
                }
            }
            is Resource.Error -> {
                Snackbar.make(
                    binding.itemsRefreshLayout,
                    "code: ${event.code} Err: ${event.message}",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            is Resource.Loading -> {
                binding.itemsRefreshLayout.isRefreshing = event.show
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
