package com.example.mckmembersapp.screens.home
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mckmembersapp.R
@Composable
fun HeaderCard(){
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth(),
                    contentAlignment = Alignment.Center
        )
        {
                Row {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_round),
                        contentDescription = "app Logo"
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                        )

                    {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                        ) {
                            Column {
                                Text(
                                    text = "Good Evening Dennis",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center,
                                    color = textColor
                                )
                                Text(
                                    text = "Welcome to MCK Kiegoi",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center,
                                    color = textColor

                                )
                            }

                        }


                    }


                }
            Image(
                modifier = Modifier
                    .size(50.dp)
                    .padding(10.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                contentDescription = "app Logo"

            )

        }
    }

}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun HeaderCardPreview(){
    HeaderCard()
}



