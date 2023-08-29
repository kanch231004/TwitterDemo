package com.example.twitterdemo.home.configs

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.twitterdemo.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/** This can be moved to a config bucket if we want to play around which
 *  fragment we want to show in which position on the fly */

const val FOR_YOU_POSITION = 0
const val FOLLOWING_FRAGMENT_POSITION = 1
const val TOTAL_FRAGMENT_COUNT = 2;

fun getTabByPosition(position: Int, context: Context?): String? {
    context?.resources?.let {
        return when (position) {
            FOR_YOU_POSITION -> it.getString(R.string.for_you)
            FOLLOWING_FRAGMENT_POSITION -> it.getString(R.string.following)
            else -> it.getString(R.string.for_you)
        }
    }
    return context?.resources?.getString(R.string.for_you)
}

fun replaceFragment(
    fragmentManager: FragmentManager,
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true,
    tag: String? = null
) {
    val transaction: FragmentTransaction = fragmentManager.beginTransaction()
    transaction.replace(containerId, fragment, tag)
    if (addToBackStack) {
        transaction.addToBackStack(null)
    }
    transaction.commit()
}

fun convertToReadableFormat(timeString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val outputFormat = SimpleDateFormat("dd MMMM yyyy")

        val date: Date = inputFormat.parse(timeString)
        outputFormat.format(date)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
