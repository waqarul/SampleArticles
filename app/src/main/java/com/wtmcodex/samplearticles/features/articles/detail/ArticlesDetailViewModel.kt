package com.wtmcodex.samplearticles.features.articles.detail

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.Domain
import com.wtmcodex.samplearticles.base.viewmodel.BaseViewModel
import com.wtmcodex.samplearticles.constants.BundleConstants
import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.extentions.getObjectFromJson
import com.wtmcodex.samplearticles.features.articles.viewitem.IViewItem
import com.wtmcodex.samplearticles.features.articles.viewitem.LabelTextViewItem

class ArticlesDetailViewModel : BaseViewModel() {
    private val TAG = this::class.simpleName
    var title = MutableLiveData<String>()
    var url = MutableLiveData<String>()
    var viewItem = MutableLiveData<List<IViewItem>>()

    // Private fields

    override fun loadData(params: Bundle?) {
        params?.let { args ->
            val articleModelJson =
                args.getString(BundleConstants.ARTICLES_DETAIL_OBJECT_KEY)
            val articleModel =
                articleModelJson?.getObjectFromJson(articleModelJson, ArticleModel::class.java)
                    ?: return
            title.value = articleModel.title ?: ""
            viewItem.value = getViewItemForArticleDetails(articleModel)
        }
    }

    private fun getViewItemForArticleDetails(articleModel: ArticleModel): List<IViewItem> {
        val context = Domain.applicationContext
        val itemsList = ArrayList<IViewItem>()
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_title), articleModel.title)
        )
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_abstract), articleModel.abstract)
        )
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_author), articleModel.byline)
        )
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_source), articleModel.source)
        )
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_type), articleModel.type)
        )
        itemsList.add(
            LabelTextViewItem(context.getString(R.string.articles_section), articleModel.section)
        )
        itemsList.add(
            LabelTextViewItem(
                context.getString(R.string.articles_subsection),
                articleModel.subSection
            )
        )
        itemsList.add(
            LabelTextViewItem(
                context.getString(R.string.articles_published_on),
                articleModel.publishedDate
            )
        )
        itemsList.add(
            LabelTextViewItem(
                context.getString(R.string.articles_url),
                context.getString(R.string.articles_open_link)
            ) { url.value = articleModel.url!! }
        )
        return itemsList
    }
}