package com.po.kemon.features.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import butterknife.ButterKnife
import com.android.mobile.R
import com.po.kemon.data.model.Contributor
import javax.inject.Inject

class ContributorAdapter @Inject
constructor() : RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>() {

    private var contributors: List<Contributor>
    private var clickListener: ClickListener? = null

    init {
        contributors = listOf()
    }

    fun setPokemon(contributors: List<Contributor>) {
        this.contributors = contributors
    }

    fun setClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_contributor, parent, false)
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
        fun onPokemonClick(pokemon: String)
    }

    inner class ContributorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {

            }
        }

        fun bind(contributor: Contributor) {

        }
    }

}