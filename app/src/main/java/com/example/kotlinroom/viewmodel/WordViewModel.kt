package com.example.kotlinroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinroom.model.Word
import com.example.kotlinroom.model.WordRepository
import com.example.kotlinroom.model.database.WordRoomDatabase
import kotlinx.coroutines.launch

class WordViewModel(application: Application): AndroidViewModel(application)  {

    //access data layer
    private val repository: WordRepository

    val allWords: LiveData<List<Word>>

    init {
        val wordsDao = WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }
    // update Livedata
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}