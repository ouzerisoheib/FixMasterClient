package com.kodea.FixMaster.presentation.CategoryList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodea.FixMaster.data.repository.Category
import com.kodea.FixMaster.domain.useCases.getCategoriesUseCase
import com.kodea.FixMaster.util.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class CategoryListViewModel @Inject constructor(private val getCategoryUseCase: getCategoriesUseCase) :
    ViewModel() {
    /*private var _category = mutableStateOf<Response<Category?>>(Response.onSuccess(null))
    var categoryList : State<Response<Category?>> = _category*/

    private var _category = mutableStateOf<Response<List<Category?>>>(Response.onLoading)
    var categoryList : State<Response<List<Category?>>> = _category


    init {
        viewModelScope.launch {
            getCategoryUseCase().collect{
                _category.value = it
            }
        }
    }

}