package com.po.kemon.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.android.mobile.R
import com.android.mobile.util.loadImageFromUrl
import com.po.kemon.data.model.Contributor
import kotlinx.android.synthetic.main.item_user.view.*
import timber.log.Timber
import javax.inject.Inject

class ContributorsAdapter @Inject
constructor() : RecyclerView.Adapter<ContributorsAdapter.ContributorViewHolder>() {

    private var contributors: MutableList<Contributor>
    private var clickListener: ClickListener? = null

    init {
        contributors = mutableListOf()
    }

    fun setContributors(contributors: MutableList<Contributor>) {
        this.contributors.addAll(contributors)
    }

    fun clearContributors() {
        this.contributors.clear()
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        return ContributorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val contributor = contributors[position]
        holder.bind(contributor)
    }

    override fun getItemCount(): Int {
        return contributors.size
    }

    interface ClickListener {
        fun onContributorClicked(pokemon: String)
    }

    inner class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {

            }
        }

        fun bind(contributor: Contributor) {
            itemView.apply {
                ivUser.loadImageFromUrl(contributor.avatarUrl)
                tvName.text = contributor.login
                tvUrl.text = contributor.htmlUrl
                tvType.text = contributor.type
            }

        }
    }

}