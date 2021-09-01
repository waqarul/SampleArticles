package com.wtmcodex.samplearticles.features.articles.master

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.base.viewmodel.BaseViewModel
import com.wtmcodex.samplearticles.base.viewmodel.Event
import com.wtmcodex.samplearticles.constants.BundleConstants
import com.wtmcodex.samplearticles.constants.NavigationConstants
import com.wtmcodex.samplearticles.core.interactor.ArticlesInteractor
import com.wtmcodex.samplearticles.core.model.AlertModel
import com.wtmcodex.samplearticles.core.model.ArticleModel
import com.wtmcodex.samplearticles.core.model.BaseDataModel
import com.wtmcodex.samplearticles.core.navigation.INavigationFragmentListener
import com.wtmcodex.samplearticles.extentions.toJson
import com.wtmcodex.samplearticles.features.articles.viewitem.ArticleViewItem
import com.wtmcodex.samplearticles.features.articles.viewitem.IViewItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArticlesViewModel : BaseViewModel(), INavigationFragmentListener {
    private val TAG = this::class.simpleName
    var viewItems = MutableLiveData<List<IViewItem>>()

    // Private fields
    private val interactor = ArticlesInteractor()

    override fun loadData(params: Bundle?) {

    }

    override fun getFragmentNavigationId(): Int {
        return R.id.nav_articles
    }

    fun makeRequestToFetchArticlesList() {
        if (!isInternetConnectionAvailable) {
            showNoInternetAlert.value = Event(true)
            showLoading.value = false
            return
        }

        showLoading.value = true
        val disposable = interactor.getArticles("7")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                viewItems.value = getViewItemsForArticles(response)
                showLoading.value = false
            }, {
                showLoading.value = false
                this.showAlertDialog.value = Event(
                    AlertModel(
                        title = context.getString(R.string.title_error_in_request),
                        message = it.localizedMessage,
                        positiveButtonTitle = context.getString(R.string.alert_ok_label)
                    )
                )
            })

        disposables.add(disposable)
    }

    private fun getViewItemsForArticles(response: BaseDataModel<List<ArticleModel>>?): List<IViewItem> {
        val itemsList = ArrayList<IViewItem>()
        val articles = response?.results ?: return ArrayList()
        articles.map {
            itemsList.add(
                ArticleViewItem(
                    it.title,
                    it.byline,
                    it.publishedDate
                ) { navigateToMarketPlaceDetail(it) }
            )
        }

        return itemsList
    }

    private fun navigateToMarketPlaceDetail(articlesModel: ArticleModel) {
        Log.e(TAG, "${articlesModel.id}")
        val args = Bundle().apply {
            putString(BundleConstants.ARTICLES_DETAIL_OBJECT_KEY, articlesModel.toJson())
        }
        navigator.navigate(NavigationConstants.ARTICLES_DETAIL, args)
    }
}