package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.details

import android.support.v4.app.Fragment
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.toPathString
import com.puzzlebench.cmk.domain.model.Thumbnail
import kotlinx.android.synthetic.main.fragment_details.*
import java.lang.ref.WeakReference

class CharacterDetailsView(fragment: Fragment){

    private val fragmentRef = WeakReference(fragment)

    fun updateTitle(heroName: String)
    {
        fragmentRef.get()!!.text_title.text = heroName
    }

    fun updateImage(thumbnail: Thumbnail)
    {
        fragmentRef.get()!!.image_hero.getImageByUrl(thumbnail.toPathString())
    }
}