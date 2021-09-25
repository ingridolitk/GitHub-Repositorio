package com.ingrid.apigitrepo2.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.ingrid.apigitrepo2.R
import com.ingrid.apigitrepo2.databinding.RepositoryItemBinding
import com.ingrid.apigitrepo2.model.Item


class GitAdapter: PagedListAdapter<Item, GitAdapter.GitViewHolder>(ITEM_COMPARATOR) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitViewHolder {

        var binding = RepositoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GitViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(item)
        }
    }

    class GitViewHolder(val binding: RepositoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val avatarImage = binding.imgProfile
        private val repoDescription = binding.txtDescription
        private val repoName = binding.txtName
        private val repoOwnerName = binding.txtOwner
        private val numberOfStars = binding.txtStars
        private val numberOfIssues = binding.txtFork
        private val lang = binding.txtLanguage

        fun bind(data: Item) {

            repoOwnerName.text = "[${data.owner.login}]"
            repoName.text = data.name
            if (data.description.isNotEmpty()) {
                repoDescription.text = data.description
            } else {
                repoDescription.text = "Sem Descrição."
            }

            if (data.stargazers_count > 1000) {
                var num = (data.stargazers_count.toDouble() / 1000)
                var s = String.format("%.1f", num) + "k"
                numberOfStars.text = "[Stars: $s]"
            } else {
                numberOfStars.text = "[Stars: ${data.stargazers_count}]"
            }

            numberOfIssues.text = "[Forks: ${data.open_issues_count}]"

            val url: String = data.owner.avatar_url
            avatarImage.load(url) {
                crossfade(true)
                placeholder(R.drawable.ic_place_holder)
                transformations(CircleCropTransformation())
            }
            lang.text = data.language
        }
    }
    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item):
                    Boolean = oldItem == newItem
        }
    }
}