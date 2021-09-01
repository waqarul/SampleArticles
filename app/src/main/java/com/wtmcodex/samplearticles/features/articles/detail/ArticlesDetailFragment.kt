package com.wtmcodex.samplearticles.features.articles.detail

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import butterknife.BindView
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.view.AbstractToolbarFragment
import com.wtmcodex.samplearticles.core.utils.AppUtils
import com.wtmcodex.samplearticles.features.articles.adapters.ArticlesRecyclerViewAdapter

class ArticlesDetailFragment : AbstractToolbarFragment<ArticlesDetailViewModel>() {

    @BindView(R.id.rv_articles)
    lateinit var recyclerView: RecyclerView

    override fun loadData() {
        super.loadData()

        viewModel.loadData(arguments)
    }

    override fun getToolbarTitle(): String {
        return getString(R.string.articles_label)
    }

    override fun getViewLayout(): Int {
        return R.layout.fragment_article_detail
    }

    override fun getOrCreateViewModel(): ArticlesDetailViewModel {
        return ViewModelProvider(this).get(ArticlesDetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showBackButton(true)

        setupRecyclerView()
    }

    override fun setupBindings() {
        super.setupBindings()

        viewModel.title.observe(viewLifecycleOwner, { title ->
            setToolbarTitle(if (title.isNullOrEmpty()) activityContext!!.getString(R.string.articles_label) else title)
        })

        viewModel.url.observe(viewLifecycleOwner, { url ->
            AppUtils.openWebLink(activityContext!!, url)
        })

        viewModel.viewItem.observe(viewLifecycleOwner, { viewItems ->
            val adapter = recyclerView.adapter as ArticlesRecyclerViewAdapter
            adapter.setViewItems(viewItems)
        })
    }

    private fun setupRecyclerView() {
        recyclerView.adapter = ArticlesRecyclerViewAdapter()
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }
}