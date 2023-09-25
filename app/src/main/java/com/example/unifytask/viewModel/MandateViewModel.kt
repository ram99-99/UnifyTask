package com.example.unifytask.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MandateViewModel(private val repository: MandateRepository) : ViewModel() {
    private val _mandateDetails = MutableLiveData<MandateDetails>()

    val mandateDetails: LiveData<MandateDetails>
        get() = _mandateDetails

    init {
        // Fetch data from the repository and update LiveData
        _mandateDetails.value = repository.getMockMandateDetails()
    }
}
