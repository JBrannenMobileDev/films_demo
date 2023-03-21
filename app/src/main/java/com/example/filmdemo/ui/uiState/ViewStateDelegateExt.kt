package com.example.filmdemo.ui.uiState

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <R> ViewStateDelegate<R, *>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) = this.uiState.collectAsStateWithLifecycle(
    initialValue = this.stateValue,
    minActiveState = minActiveState,
)