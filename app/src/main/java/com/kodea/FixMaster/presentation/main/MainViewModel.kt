package com.kodea.FixMaster.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kodea.FixMaster.domain.useCases.appEntryUseCases.AppEntryUseCases
import com.kodea.FixMaster.domain.useCases.authentifaction.AuthUseCases
import com.kodea.FixMaster.util.navigation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class mainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases , authUseCases: AuthUseCases) :
    ViewModel() {
    private val _isReady = MutableStateFlow(true)
    val isReady = _isReady.asStateFlow()

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        viewModelScope.launch {
            appEntryUseCases.readAppEntry().collect {
                if (!it){
                    startDestination = Route.AppStartNavigation.route
                }else {
                    if (authUseCases.isUserAuth()){
                        startDestination = Route.BrowseNavigation.route
                    }else{
                        startDestination = Route.AuthNavigation.route
                    }
                }

                delay(500L)
                _isReady.value = false
            }

        }
    }
}