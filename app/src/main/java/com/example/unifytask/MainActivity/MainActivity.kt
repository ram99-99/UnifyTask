package com.example.unifytask.MainActivity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import com.example.unifytask.R
import com.example.unifytask.viewModel.MandateViewModel
import com.example.unifytask.viewModel.MandateViewModelFactory

class MainActivity : ComponentActivity() {

    private val  TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate called ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mandateViewModel = ViewModelProvider(this, MandateViewModelFactory()).get(
            MandateViewModel::class.java
        )

        mandateViewModel.mandateDetails.observe(this) { mandateDetails ->
            // Update your UI with the fetched data
            // Example: update text views with mandateDetails.date, mandateDetails.ugx, etc.
            val date = mandateDetails.date
            val ugx = mandateDetails.ugx
            val status = mandateDetails.status
            val composeView = findViewById<ComposeView>(R.id.compose_view)
            composeView.setContent {
                MandateDetailsScreen(date,ugx,status)
            }
        }

    }

    @Composable
    fun MandateDetailsScreen(date:String,ugx: String,status: String) {
        Log.d(TAG,"MandateDetailsScreen called ")
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {

            MandateDetailsHeader()
            MandateDetailsCard(date,ugx,status);
            AutoPayPaymentOptions()
            PayUsingCard()
        }
    }


    @Composable
    fun MandateDetailsHeader() {
        Log.d(TAG,"MandateDetailsHeader called ")
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(1.dp)
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Heading
            IconButton(
                onClick = { /* Handle arrow button click here */ },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color(255, 165, 0)
                )
            }
            Text(
                text = "Mandate Details",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 2.dp),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }

    @Composable
    fun MandateDetailsCard(date:String,ugx:String,status:String) {
        Log.d(TAG,"MandateDetailsCard called ")

        // Card and its contents
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 10.sp)) {
                                append("Valid till - ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            ) {
                                append(date) // Display the time value
                            }
                        },
                    )
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(fontSize = 10.sp)) {
                                append("Upto Amount - ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp
                                )
                            ) {
                                append(ugx)
                            }
                        },
                    )
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = Color.Black)
                )
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(fontSize = 10.sp)) {
                            append("Frequency- ")
                        }
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 10.sp
                            )
                        ) {
                            append(status)
                        }
                    },
                )
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(color = Color.Black)
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                        .background(
                            color = Color(255, 240, 225),

                            shape = RoundedCornerShape(10.dp) // Set the shape to round
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp), // Increase padding for a larger gap
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Increase spacing between icon and text
                    ) {
                        // Info icon (in the starting line)
                        IconButton(
                            onClick = { /* Handle info button click here */ },
                            modifier = Modifier.size(15.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = Color(255, 165, 0),
                            )
                        }

                        Text(
                            text = buildAnnotatedString {
                                withStyle(style = SpanStyle(fontSize = 8.sp)) {
                                    append("The amount will be blocked when the ride is booked with\n")
                                    append("SafeFoda, Subject to the above-mentioned limit and validity.\n")
                                    append("The limit is up to ")
                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append(ugx)
                                    }
                                }
                            },
                            textAlign = TextAlign.Start,
                            color = Color.Black
                        )
                    }
                }

            }
        }
    }

    @Composable
    fun AutoPayPaymentOptions() {
        Log.d(TAG,"AutoPayPaymentOptions called ")
        // AutoPay payment options section
        Text(
            text = "AutoPay Payment Options:",
            style = MaterialTheme.typography.headlineSmall
                .copy(fontSize = 13.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .border(2.dp, Color(255, 180, 0), shape = RoundedCornerShape(4.dp))
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.aritel),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.White)
                    .padding(4.dp) // Add padding to create space
            ) {
                Image(
                    painter = painterResource(id = R.drawable.fp),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color.White)
                    .padding(4.dp) // Add padding to create space
            ) {
                Image(
                    painter = painterResource(id = R.drawable.m),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    @Composable
    fun PayUsingCard() {
        Log.d(TAG,"PayUsingCard called ")
        // Pay Using card and its contents
        Text(
            text = "Pay Using",
            style = MaterialTheme.typography.headlineSmall
                .copy(fontSize = 13.sp, fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .border(1.dp, Color(255, 200, 100), RoundedCornerShape(10.dp)),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.alogo),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp) // Adjust the size as needed
                    )
                    Text(
                        text = "Airtel Money - XXX897:",
                        style = MaterialTheme.typography.headlineSmall.copy(fontSize = 10.sp),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { /* Handle arrow button click here */ },
                        modifier = Modifier.size(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowRight,
                            contentDescription = null,
                            tint = Color.DarkGray
                        )

                    }
                }
            }

        }
    }
}