package com.esan.diarioespacial.ui.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.esan.diarioespacial.DiarioApp
import com.esan.diarioespacial.data.local.ApodEntity
import com.esan.diarioespacial.data.remote.RetrofitClient
import com.esan.diarioespacial.data.remote.apod.ApodApi
import com.esan.diarioespacial.data.repository.ApodRepository
import com.esan.diarioespacial.util.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ApodUiState(
    val isLoading: Boolean = false,
    val items: List<ApodEntity> = emptyList(),
    val error: String? = null
)

class ApodViewModel(private val repository: ApodRepository) : ViewModel() {

    // Últimos 7 días: desde hace 6 días hasta hoy
    private val start = DateUtils.daysAgo(6)
    private val end = DateUtils.today()

    private val isLoading = MutableStateFlow(false)
    private val error = MutableStateFlow<String?>(null)

    val uiState: StateFlow<ApodUiState> =
        combine(repository.observeRange(start, end), isLoading, error) { items, loading, err ->
            ApodUiState(isLoading = loading, items = items, error = err)
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), ApodUiState(isLoading = true))

    val favorites: StateFlow<List<ApodEntity>> =
        repository.observeFavorites()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun observeByDate(date: String): Flow<ApodEntity?> = repository.observeByDate(date)

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            isLoading.value = true
            error.value = null
            try {
                repository.refresh(start, end)
            } catch (e: Exception) {
                error.value = "No se pudo conectar con la NASA. Revisa tu conexión o tu API key."
            } finally {
                isLoading.value = false
            }
        }
    }

    fun toggleFavorite(item: ApodEntity) {
        viewModelScope.launch { repository.toggleFavorite(item) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val app = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as DiarioApp
                ApodViewModel(
                    ApodRepository(
                        api = RetrofitClient.retrofit.create(ApodApi::class.java),
                        dao = app.database.apodDao()
                    )
                )
            }
        }
    }
}
