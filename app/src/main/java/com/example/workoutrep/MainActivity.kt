
package com.example.workoutrep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ViewModel - Handles rep count
class WorkoutViewModel : ViewModel() {

    private val _repCount = MutableStateFlow(0)
    val repCount: StateFlow<Int> = _repCount.asStateFlow()

    fun increment() {
        _repCount.value++
    }

    fun reset() {
        _repCount.value = 0
    }
}

// Main Activity
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                WorkoutScreen()
            }
        }
    }
}

// Jetpack Compose UI
@Composable
fun WorkoutScreen(
    viewModel: WorkoutViewModel = viewModel()
) {

    val count by viewModel.repCount.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Workout Rep Counter",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Reps: $count",
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { viewModel.increment() }
        ) {
            Text("Increment +")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.reset() }
        ) {
            Text("Reset")
        }
    }
}
