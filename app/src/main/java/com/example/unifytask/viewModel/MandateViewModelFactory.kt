package com.example.unifytask.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MandateViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MandateViewModel::class.java)) {
            val repository = MandateRepository() // Create your repository instance here
            @Suppress("UNCHECKED_CAST")
            return MandateViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
