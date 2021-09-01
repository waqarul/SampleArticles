package com.wtmcodex.samplearticles.features.articles.viewitem

sealed class IViewItem
class ArticleViewItem(
    val title: String?,
    val author: String?,
    val publishedOn: String?,
    val imageUrl: String? = null,
    val onClickAction: (() -> Unit)? = null,
) : IViewItem()

class LabelTextViewItem(
    val label: String?,
    val title: String?,
    val onClickAction: (() -> Unit)? = null,
) : IViewItem()

class ProgressBarViewItem : IViewItem()