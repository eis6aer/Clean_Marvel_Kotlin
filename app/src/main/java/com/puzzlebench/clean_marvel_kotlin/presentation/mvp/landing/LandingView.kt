package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.landing

import android.content.Intent
import android.view.animation.*
import com.puzzlebench.clean_marvel_kotlin.presentation.LandingActivity
import com.puzzlebench.clean_marvel_kotlin.presentation.MainActivity
import kotlinx.android.synthetic.main.activity_landing.*
import java.lang.ref.WeakReference


class LandingView(activity: LandingActivity)
{
    private val activityRef = WeakReference(activity)

    fun rotateImage()
    {
        var animationSet = AnimationSet(false)

        val rotate = RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 1000
        rotate.interpolator = DecelerateInterpolator()
        rotate.fillAfter = true

        rotate.setAnimationListener(object: Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                goMainActivity()
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })

        var alphaAnimation = AlphaAnimation(0F,1F)
        alphaAnimation.duration = 900
        alphaAnimation.fillAfter = true

        animationSet.addAnimation(rotate)
        animationSet.addAnimation(alphaAnimation)

        activityRef.get()!!.image_main_logo.startAnimation(animationSet)
    }

    fun goMainActivity()
    {
        var i = Intent(activityRef.get()!!, MainActivity::class.java)
        activityRef.get()!!.startActivity(i)
        activityRef.get()!!.finish()
    }


}