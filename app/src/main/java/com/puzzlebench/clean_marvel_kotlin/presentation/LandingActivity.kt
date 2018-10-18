package com.puzzlebench.clean_marvel_kotlin.presentation

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.puzzlebench.clean_marvel_kotlin.R
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.landing.LandingPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.landing.LandingView
import kotlinx.android.synthetic.main.activity_landing.*


class LandingActivity : AppCompatActivity() {

    val presenter = LandingPresenter(LandingView(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

}
