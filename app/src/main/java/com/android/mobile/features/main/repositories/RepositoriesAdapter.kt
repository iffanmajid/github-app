package com.po.kemon.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.android.mobile.R
import com.android.mobile.data.model.Item
import kotlinx.android.synthetic.main.item_repository.view.*
import javax.inject.Inject

class RepositoriesAdapter @Inject
constructor() : RecyclerView.Adapter<RepositoriesAdapter.RepositoryViewHolder>() {

    private var repositories: List<Item>
    private var clickListener: ClickListener? = null

    init {
        repositories = listOf()
    }

    fun setRepositories(contributors: List<Item>) {
        if (repositories.isNotEmpty()) {
            repositories = listOf()
        }
        this.repositories = contributors
    }

    fun clearRepositories() {
        if (repositories.isNotEmpty()) {
            repositories = listOf()
        }
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val item = repositories[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return repositories.size
    }

    interface ClickListener {
        fun onRepositoryClicked(pokemon: String)
    }

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {

            }
        }

        fun bind(item: Item) {
            itemView.apply {
                tvName.text = resources.getString(R.string.repository_name, item.name)
                tvUrl.text = resources.getString(R.string.repository_url, item.htmlUrl)
                tvOwner.text = resources.getString(R.string.repository_owner, item.owner?.login)
            }

        }
    }

}