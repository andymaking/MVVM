package io.king.mvvm.data.repository

import io.king.mvvm.data.api.CharacterApi
import io.king.mvvm.data.api.model.Character
import javax.inject.Inject

class CharacterRepo @Inject constructor(
    private val characterApi: CharacterApi
) {
    suspend fun getCharacters():List<Character>{
        return characterApi.getCharacter()
    }
}