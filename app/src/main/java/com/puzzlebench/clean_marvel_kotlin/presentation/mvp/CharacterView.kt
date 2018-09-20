package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import android.support.v7.widget.GridLayoutManager
import android.text.style.UpdateLayout
import android.view.View
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.DetailsFragment
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.adapter.CharacterAdapter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.listener.UpdateListener
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.ref.WeakReference

class CharacterView(activity: MainActivity) {
    private val activityRef = WeakReference(activity)
    private val SPAN_COUNT = 1

    var updateListener : UpdateListener? = null

    var adapter = CharacterAdapter { character ->
        //activity.applicationContext.showToast(character.name)
        showDetails(character.id)
    }


    fun init() {
        val activity = activityRef.get()
        if (activity != null) {
            activity.fbtn_refresh.setOnClickListener {
                reloadData()
            }
            activity.recycleView.layoutManager = GridLayoutManager(activity, SPAN_COUNT)
            activity.recycleView.adapter = adapter
            showLoading()
        }

    }

    fun showToastNoItemToShow() {
        val activity = activityRef.get()
        if (activity != null) {
            val message = activity.baseContext.resources.getString(R.string.message_no_items_to_show)
            activity.applicationContext.showToast(message)
        }
    }

    fun showToastNetworkError(error: String) {
        activityRef.get()!!.applicationContext.showToast(error)
    }

    fun showGenericToast(message: String)
    {
        activityRef.get()!!.applicationContext.showToast(message)
    }

    fun hideLoading() {
        activityRef.get()!!.progressBar.visibility = View.GONE
    }

    fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    fun showLoading() {
        activityRef.get()!!.progressBar.visibility = View.VISIBLE

    }

    fun showDetails(characterId: Int)
    {
        DetailsFragment.newInstance(characterId).show(activityRef.get()!!.supportFragmentManager,"DetailsFragment")
    }

    fun reloadData()
    {
        updateListener?.let {
            it()
        }
    }

    fun onUpdate(update: () -> Unit)
    {
        updateListener = update
    }

    fun showData()
    {

    }
}
