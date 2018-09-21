package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.details

import android.util.Log
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.toPathString
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterView
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.usecase.GetCharacterRepositoryUseCase
import com.puzzlebench.cmk.domain.usecase.GetCharacterServiceUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlin.properties.Delegates

class CharacterDetailsPresenter(view: CharacterDetailsView,
                                private val getChatacterServiceUseCase: GetCharacterServiceUseCase,
                                private val getCharacterRepositoryUseCase: GetCharacterRepositoryUseCase, val subscriptions: CompositeDisposable, val charId : Int) : Presenter<CharacterDetailsView>(view)
{
    var character: Character? by Delegates.observable<Character?>(null) { property, oldVal, newVal ->
        newVal?.let {
            view.updateTitle(it.name)
            view.updateImage(it.thumbnail)
            Log.v(this@CharacterDetailsPresenter::class.java.simpleName,"delegate: data updated")
        }
    }

    fun init() {
        character = getCharacterRepositoryUseCase.invoke(charId)
        getChatacterServiceUseCase.invoke(charId)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.v(this@CharacterDetailsPresenter::class.java.simpleName,"init: data fetched")
                    getCharacterRepositoryUseCase.invoke(character!!)
                    character = getCharacterRepositoryUseCase.invoke(charId)
                }

    }

}