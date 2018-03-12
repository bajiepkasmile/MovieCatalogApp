package com.nodomain.moviecatalogapp.presentation.ui.recyclerviews.base.recyclerviewwithadapterandfooter


import android.support.v7.widget.RecyclerView
import android.view.ViewGroup


private const val VIEW_TYPE_ITEM = 0
private const val VIEW_TYPE_FOOTER = 1


abstract class RecyclerViewAdapterWithFooter<IVH: RecyclerView.ViewHolder, FVH: RecyclerView.ViewHolder>
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val isEmpty: Boolean
        get() = getItemCountWithoutFooter() == 0

    protected var footerViewHolder: FVH? = null
        private set

    abstract fun onCreateItemViewHolder(parent: ViewGroup?): IVH

    abstract fun onCreateFooterViewHolder(parent: ViewGroup?): FVH

    abstract fun onBindItemViewHolder(holder: IVH, position: Int)

    abstract fun getItemCountWithoutFooter(): Int

    final override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_ITEM)
            onCreateItemViewHolder(parent)
        else
            onCreateFooterViewHolder(parent)
    }

    final override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_ITEM)
            onBindItemViewHolder(holder as IVH, position)
        else
            footerViewHolder = holder as FVH
    }

    final override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1)
            VIEW_TYPE_FOOTER
        else
            VIEW_TYPE_ITEM
    }

    final override fun getItemCount() = getItemCountWithoutFooter() + 1
}