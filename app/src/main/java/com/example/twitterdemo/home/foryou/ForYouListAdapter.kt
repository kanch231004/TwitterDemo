package com.example.twitterdemo.home.foryou

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitterdemo.data.TweetModel
import com.example.twitterdemo.databinding.RvTweetListItemBinding

class ForYouListAdapter : ListAdapter<TweetModel, ForYouListAdapter.ForYouItemViewHolder>(TweetListDiffCallback()){

    class ForYouItemViewHolder(private val binding: RvTweetListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tweetModel: TweetModel) {
            binding.apply {
                Log.d("Answer", "bind: username ${tweetModel.user.handle}")
                tvUserHandle.text = tweetModel.user.handle
                tvUserName.text = tweetModel.user.name
                tvTimeStamp.text = tweetModel.postedAt
                tvTweetDescription.text = tweetModel.content
                Glide.with(binding.root.context).load(tweetModel.profileIcon).into(binding.imageViewProfilePicture)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForYouItemViewHolder {
        return ForYouItemViewHolder(RvTweetListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ForYouItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class TweetListDiffCallback: DiffUtil.ItemCallback<TweetModel>() {
    override fun areItemsTheSame(oldItem: TweetModel, newItem: TweetModel): Boolean {
        return oldItem.postedAt === newItem.postedAt
    }

    override fun areContentsTheSame(oldItem: TweetModel, newItem: TweetModel): Boolean {
       return oldItem == newItem
    }
}