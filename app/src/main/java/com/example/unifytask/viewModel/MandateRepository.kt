package com.example.unifytask.viewModel

class MandateRepository {
    fun getMockMandateDetails(): MandateDetails {
        // Simulate a mock JSON response
        return MandateDetails(
            date = "29-May-2024",
            ugx = "UGX 10,000",
            status = "As presented"
        )
    }
}
