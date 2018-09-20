package com.puzzlebench.clean_marvel_kotlin.presentation.mvp.details

import android.util.Log
import com.puzzlebench.clean_marvel_kotlin.presentation.base.Presenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterView
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.usecase.GetCharacterRepositoryUseCase
import com.puzzlebench.cmk.domain.usecase.GetCharacterServiceUseCase
import io.reactivex.disposables.CompositeDisposable

class CharacterDetailsPresenter(view: CharacterDetailsView,
                                private val getChatacterServiceUseCase: GetCharacterServiceUseCase,
                                private val getCharacterRepositoryUseCase: GetCharacterRepositoryUseCase, val subscriptions: CompositeDisposable, val charId : Int) : Presenter<CharacterDetailsView>(view)
{
    lateinit var character: Character
    fun init() {
        //view.init()
        character = getCharacterRepositoryUseCase.invoke(charId)
        view.updateTitle(character.name)
        view.updateImage(character.thumbnail)

//        if (characters.isEmpty()) {
//            requestGetCharacters()
//        } else {
//            view.hideLoading()
//            view.showCharacters(characters)
//        }
    }

}