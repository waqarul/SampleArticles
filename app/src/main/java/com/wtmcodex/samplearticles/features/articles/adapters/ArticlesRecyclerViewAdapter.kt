package com.wtmcodex.samplearticles.features.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.wtmcodex.samplearticles.R
import com.wtmcodex.samplearticles.features.articles.viewitem.ArticleViewItem
import com.wtmcodex.samplearticles.features.articles.viewitem.IViewItem
import com.wtmcodex.samplearticles.features.articles.viewitem.LabelTextViewItem


class ArticlesRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var viewItems: MutableList<IViewItem> = ArrayList()

    fun setViewItems(viewItems: List<IViewItem>?) {
        viewItems?.let {
            this.viewItems = it as MutableList<IViewItem>
            notifyDataSetChanged()
        } ?: run {
            clear()
        }
    }

    fun clear() {
        this.viewItems = ArrayList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return viewItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return when (viewType) {
            R.layout.article_card_view_item -> ArticlesViewHolder(view)
            R.layout.label_text_view_item -> LabelTextViewHolder(view)
            else -> throw IllegalArgumentException("Unhandled view type in onCreateViewHolder NotificationCenterRecyclerViewAdapter.")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (viewItems[position]) {
            is ArticleViewItem -> R.layout.article_card_view_item
            is LabelTextViewItem -> R.layout.label_text_view_item
            else -> throw IllegalArgumentException("Unhandled view type in getItemViewType NotificationCenterRecyclerViewAdapter.")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (viewItems[position]) {
            is ArticleViewItem -> {
                val viewHolder = (holder as ArticlesViewHolder)
                val viewItem = (viewItems[position] as ArticleViewItem)
                viewHolder.bindData(viewItem)
            }
            is LabelTextViewItem -> {
                val viewHolder = (holder as LabelTextViewHolder)
                val viewItem = (viewItems[position] as LabelTextViewItem)
                viewHolder.bindData(viewItem)
            }
        }
    }

    private inner class ArticlesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var container: CardView = view.findViewById(R.id.container)
        private var imageView: ImageView = view.findViewById(R.id.iv_image)
        private var tvTitle: TextView = view.findViewById(R.id.tv_title)
        private var tvAuthor: TextView = view.findViewById(R.id.tv_author)
        private var tvPublishedOn: TextView = view.findViewById(R.id.tv_published_on)

        fun bindData(viewItem: ArticleViewItem) {
            tvTitle.text = viewItem.title ?: tvTitle.context.getString(R.string.not_applicable)
            tvAuthor.text = viewItem.author ?: tvAuthor.context.getString(R.string.not_applicable)
            tvPublishedOn.text =
                viewItem.publishedOn ?: tvPublishedOn.context.getString(R.string.not_applicable)

            container.setOnClickListener { viewItem.onClickAction?.invoke() }

            if (!viewItem.imageUrl.isNullOrEmpty()) {
                Glide
                    .with(imageView.context)
                    .load(viewItem.imageUrl)
                    .placeholder(R.drawable.placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(imageView)
            } else {
                imageView.setImageResource(R.drawable.placeholder)
            }
        }
    }

    private inner class LabelTextViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var container: ConstraintLayout = view.findViewById(R.id.container)
        private var tvLabel: TextView = view.findViewById(R.id.tv_label)
        private var tvTitle: TextView = view.findViewById(R.id.tv_title)

        fun bindData(viewItem: LabelTextViewItem) {
            tvLabel.text = viewItem.label ?: tvLabel.context.getString(R.string.not_applicable)
            tvTitle.text = viewItem.title ?: tvTitle.context.getString(R.string.not_applicable)
            container.setOnClickListener { viewItem.onClickAction?.invoke() }
        }
    }
}