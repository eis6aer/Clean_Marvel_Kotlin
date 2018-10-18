package com.puzzlebench.cmk.data.service

import com.puzzlebench.cmk.data.mapper.service.CharacterMapperService
import com.puzzlebench.cmk.data.service.api.MarvelApi
import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.service.CharacterServices
import io.reactivex.Observable


class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(), private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterServices {
    override fun getCharacterData(characterId: Int): Observable<Character> {
        return Observable.create {
            subscriber ->
                val callResponse = api.createService(MarvelApi::class.java).getCharacterDetails(characterId)
                val response = callResponse.execute()

            if (response.isSuccessful) {
                subscriber.onNext(mapper.transform(response.body()!!.data!!.characters[0]))
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    override fun getCharacters(): Observable<List<Character>> {
        return Observable.create { subscriber ->
            val callResponse = api.createService(MarvelApi::class.java).getCharacter()
            val response = callResponse.execute()

            if (response.isSuccessful) {
                subscriber.onNext(mapper.transform(response.body()!!.data!!.characters))
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}