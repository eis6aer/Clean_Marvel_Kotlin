package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.landing

import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter

class LandingPresenter(view: LandingView) : Presenter<LandingView>(view)
{
    fun onResume() {
        view.rotateImage()
    }
}