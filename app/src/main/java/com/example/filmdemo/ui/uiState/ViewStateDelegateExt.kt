package com.example.filmdemo.ui.uiState

import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@Composable
fun <R> ViewStateDelegate<R, *>.collectWithLifecycle(
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED,
) = this.uiState.collectAsStateWithLifecycle(
    initialValue = this.stateValue,
    minActiveState = minActiveState,
)

fun <State, Event> ViewStateDelegate<State, Event>.collectEvent(
    lifecycleOwner: LifecycleOwner,
    lifecycleState: Lifecycle.State = Lifecycle.State.RESUMED,
    block: (event: Event) -> Unit
): Job = lifecycleOwner.lifecycleScope.launch {
    singleEvents.flowWithLifecycle(
        lifecycle = lifecycleOwner.lifecycle,
        minActiveState = lifecycleState,
    ).collect { event ->
        block.invoke(event)
    }
}