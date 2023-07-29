package com.example.twitterdemo.home.configs

import android.content.Context
import com.example.twitterdemo.R

/** This can be moved to a config bucket if we want to play around which
 *  fragment we want to show in which position on the fly */

const val FOR_YOU_POSITION = 0
const val FOLLOWING_FRAGMENT_POSITION = 1
const val TOTAL_FRAGMENT_COUNT = 2;

fun getTabByPosition(position: Int, context: Context?): String? {
    context?.resources?.let {
    return when (position) {
            FOR_YOU_POSITION ->  it.getString(R.string.for_you)
            FOLLOWING_FRAGMENT_POSITION -> it.getString(R.string.following)
            else -> it.getString(R.string.for_you)
        }
    }
    return context?.resources?.getString(R.string.for_you)
}