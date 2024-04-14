package com.example.mckmembersapp.screens.home
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mckmembersapp.R
import com.example.mckmembersapp.ui.theme.md_theme_light_primary

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
fun ReceiptCard(){
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 14.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth(),
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Invoice Number",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Status",
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )

                    Text(
                        text = "23/01/2021",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 14.sp,
                        color = textColor
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = "Name:",
                        modifier = Modifier
                            .padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Dennis Kirimi Gitonga",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 60.dp, end = 5.dp),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = textColor
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = "Total Amount:",
                        modifier = Modifier
                            .padding(5.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "50",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 60.dp, end = 5.dp),
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = md_theme_light_primary,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(
                            text = "View Receipt",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun Intro(){
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    Text(
        text = stringResource(id = R.string.proverbs_3_9_niv_says_honor_the_lord_with_your_wealth_with_the_first_fruits_of_all_your_crops),
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp),
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis, color = textColor
    )
}
@Composable
fun Report() {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 14.dp
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .background(color = md_theme_light_primary)
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Tithe Contribution",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = Color.White,

                        )
                }

                Image(
                    painter = painterResource(id = R.drawable.tithe1_round),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp, 80.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = "Today",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Month",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Year",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Text(
                        text = "Today Contribution",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Month Contribution",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "Year Contribution",
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                }
            }
        }
    }
}
@Composable
fun ReceiptPreview() {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    Box(
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
            ) {
                Text(
                    text = "Print Preview",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge,
                    color = textColor
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "METHODIST CHURCH IN KENYA",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(5.dp),
                    color = textColor
                )
                Text(
                    text = "NYAMBENE SYNOD",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(5.dp),
                    color = textColor
                )
                Text(
                    text = "MCK KIEGOI",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(5.dp),
                    color = textColor
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "RNO",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(5.dp),
                        color = textColor
                    )
                    Text(
                        text = "5/07/2023",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(5.dp),
                        color = textColor
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Name",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                    Text(
                        text = "Amount",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Member Name",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                    Text(
                        text = "00.00",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                        .padding(5.dp)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TOTAL THIS MONTH",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                    Text(
                        text = "00.00",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "TOTAL THIS YEAR",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                    Text(
                        text = "00.00",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(4.dp),
                        color = textColor
                    )
                }
                Text(
                    text = "Payment of:Tithe",
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(4.dp),
                    color = textColor
                )
            }
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun HeaderCardPreview(){
    HeaderCard()

}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun ReceiptCardPreview(){
    ReceiptCard()
}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun IntroPreview(){
    Intro()
}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun ReportPreview(){
 Report()
}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun ReceiptPrev(){
ReceiptPreview()
}




