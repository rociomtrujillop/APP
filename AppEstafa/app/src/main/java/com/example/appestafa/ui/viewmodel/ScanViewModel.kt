package com.example.appestafa.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appestafa.data.Scam
import com.example.appestafa.data.ScamDetail
import com.example.appestafa.data.ScamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScamViewModel : ViewModel() {

    private val _scams = MutableStateFlow<List<Scam>>(emptyList())
    val scams: StateFlow<List<Scam>> = _scams.asStateFlow()

    private val _scamDetails = MutableStateFlow<Map<String, ScamDetail>>(emptyMap())

    init {
        loadData()
    }

    private fun loadData() {
        _scams.value = ScamRepository.getScams()
        _scamDetails.value = ScamRepository.getScamDetails()
    }

    fun getScamDetailById(id: String): ScamDetail? {
        return _scamDetails.value[id]
    }
}