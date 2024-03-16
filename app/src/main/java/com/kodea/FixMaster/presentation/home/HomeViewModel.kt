package com.kodea.FixMaster.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodea.FixMaster.data.repository.Category
import com.kodea.FixMaster.domain.useCases.getLimitCategoriesUseCase
import com.kodea.FixMaster.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val getLimitCategoriesUseCase: getLimitCategoriesUseCase) :
    ViewModel() {
    private var _category = mutableStateOf<Response<List<Category?>>>(Response.onLoading)
    var categoryList: State<Response<List<Category?>>> = _category


    init {
        viewModelScope.launch {
            getLimitCategoriesUseCase().collect {
                _category.value = it
            }
        }
    }

}