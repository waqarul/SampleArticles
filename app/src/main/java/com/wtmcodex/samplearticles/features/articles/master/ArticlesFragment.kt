package com.wtmcodex.samplearticles.features.articles.master

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import butterknife.BindView
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.view.BaseFragment
import com.wtmcodex.samplearticles.features.articles.adapters.ArticlesRecyclerViewAdapter

class ArticlesFragment : BaseFragment<ArticlesViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.sr_layout)
    lateinit var swipeRefreshLayout: SwipeRefreshLayout

    @BindView(R.id.rv_articles)
    lateinit var recyclerView: RecyclerView

    private var adapter = ArticlesRecyclerViewAdapter()

    override fun loadData() {
        super.loadData()
        viewModel.makeRequestToFetchArticlesList()
    }

    override fun getTitle(): String {
        return getString(R.string.articles_label)
    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_articles
    }

    override fun getOrCreateViewModel(): ArticlesViewModel {
        return ViewModelProvider(this).get(ArticlesViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupSwipeRefreshLayout()
    }

    override fun setupBindings() {
        super.setupBindings()

        viewModel.viewItems.observe(viewLifecycleOwner, { viewItems ->

            val adapter = recyclerView.adapter as ArticlesRecyclerViewAdapter
            adapter.setViewItems(viewItems)
            swipeRefreshLayout.isRefreshing = false
        })
    }

    override fun showLoading(shouldShow: Boolean) {
        super.showLoading(shouldShow)
        if (!shouldShow) {
            swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun doApiCall() {
        viewModel.makeRequestToFetchArticlesList()
    }

    private fun setupRecyclerView() {
        recyclerView.setHasFixedSize(true);

        recyclerView.adapter = adapter
        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.layoutManager = layoutManager
    }

    private fun setupSwipeRefreshLayout() {
        swipeRefreshLayout.setOnRefreshListener(this)

        // Scheme colors for animation
        swipeRefreshLayout.setColorSchemeColors(
            ContextCompat.getColor(swipeRefreshLayout.context, android.R.color.holo_blue_bright),
            ContextCompat.getColor(swipeRefreshLayout.context, android.R.color.holo_green_light),
            ContextCompat.getColor(swipeRefreshLayout.context, android.R.color.holo_orange_light),
            ContextCompat.getColor(swipeRefreshLayout.context, android.R.color.holo_red_light)
        )
    }

    override fun onRefresh() {
        adapter.clear()
        doApiCall()
    }
}