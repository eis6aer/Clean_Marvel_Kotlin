package com.puzzlebench.clean_marvel_kotlin.presentation

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterView
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.details.CharacterDetailsPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.details.CharacterDetailsView
import com.puzzlebench.cmk.data.mapper.repository.CharacterMapperRepository
import com.puzzlebench.cmk.data.repository.CharacterDataRepository
import com.puzzlebench.cmk.data.repository.source.CharacterDataSource
import com.puzzlebench.cmk.data.service.CharacterServicesImpl
import com.puzzlebench.cmk.domain.usecase.GetCharacterRepositoryUseCase
import com.puzzlebench.cmk.domain.usecase.GetCharacterServiceUseCase
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_details.*

private const val ARGUMENT_CHARACTER_ID = "character_id"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DetailsFragment : DialogFragment() {

    private var characterId: Int? = null
    protected var subscriptions = CompositeDisposable()


    val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())
    val getCharacterRepositoryUseCase = GetCharacterRepositoryUseCase(CharacterDataRepository(CharacterDataSource(), CharacterMapperRepository()))

    var presenter : CharacterDetailsPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            characterId = it.getInt(ARGUMENT_CHARACTER_ID,0)

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onStart() {
        super.onStart()
        dialog.window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //setTitleText(characterId.toString())
        presenter = CharacterDetailsPresenter(CharacterDetailsView(this@DetailsFragment), getCharacterServiceUseCase, getCharacterRepositoryUseCase, subscriptions, characterId!!)
        presenter?.init()
    }

    fun setTitleText(text: String)
    {
        text_title.text = text
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param characterId Id of the character to fetch.
         * @return A new instance of fragment DetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(characterId: Int) =
                DetailsFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARGUMENT_CHARACTER_ID, characterId)
                    }
                }
    }
}
