package com.puzzlebench.cmk.domain.usecase

import com.puzzlebench.cmk.domain.model.Character
import com.puzzlebench.cmk.domain.repository.CharacterRepository


class GetCharacterRepositoryUseCase(private val characterDataRepository: CharacterRepository) {

    operator fun invoke(): List<Character> = characterDataRepository.getAll()
    operator fun invoke(characters: List<Character>) = characterDataRepository.save(characters)
    operator fun invoke(characterId: Int) = characterDataRepository.getById(characterId)
    operator fun invoke(character : Character) = characterDataRepository.save(character)

}