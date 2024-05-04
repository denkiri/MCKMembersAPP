package com.example.mckmembersapp.screens.home
import android.app.DatePickerDialog
import android.content.res.Configuration
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.mckmembersapp.R
import com.example.mckmembersapp.components.Loader
import com.example.mckmembersapp.components.Toast
import com.example.mckmembersapp.data.Resource
import com.example.mckmembersapp.models.auth.Profile
import com.example.mckmembersapp.models.contribution.Contribution
import com.example.mckmembersapp.models.memberreport.MemberReportData
import com.example.mckmembersapp.ui.theme.md_theme_light_primary
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Calendar
import java.util.Currency
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val day = remember { mutableStateOf("") }
    val month = remember { mutableStateOf("") }
    val year = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor,
                    titleContentColor = textColor,
                ),
                title = {
                    Text("Methodist Church in Kenya ")
                },
                actions = {
                    val mContext = LocalContext.current
                    val mYear: Int
                    val mMonth: Int
                    val mDay: Int
                    val mCalendar = Calendar.getInstance()
                    mYear = mCalendar.get(Calendar.YEAR)
                    mMonth = mCalendar.get(Calendar.MONTH)
                    mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
                    mCalendar.time = Date()
                    val mDate = remember { mutableStateOf("") }
                    val mDatePickerDialog = DatePickerDialog(
                        mContext,
                        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
                            mDate.value = if (mDayOfMonth < 10) "0${mDayOfMonth}" else "$mDayOfMonth"
                            day.value = if (mDayOfMonth < 10) "0${mDayOfMonth}" else "$mDayOfMonth"
                            val mon = mMonth + 1
                            month.value = if (mon < 10) "0${mon}" else "${mon}"
                            year.value = "$mYear"
                        }, mYear, mMonth, mDay
                    )
                    IconButton(onClick = {
                        mDatePickerDialog.show()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.CalendarMonth,
                            contentDescription = "Localized description"
                        )
                    }
                }



            )
        },
    ){ innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(color = backgroundColor)
                .padding(innerPadding)
        ) {
                UiSetup(viewModel, navController,day.value,month.value,year.value)


        }
    }


}


@Composable
private fun UiSetup(
    viewModel: HomeViewModel,
    navController: NavHostController,
    day: String,
    month: String,
    year: String
) {
    val reportState by viewModel.memberReportRequestResult.collectAsState()
    val customReportState by viewModel.customMemberReportRequestResult.collectAsState()
    val profileState by viewModel.profileRequestResult.collectAsState()
    LaunchedEffect(profileState) {
        if (profileState is Resource.Idle) {
            viewModel.getProfile()
        }
    }
    LaunchedEffect(day,month,year) {
        if (day.isNotBlank() && month.isNotBlank() && year.isNotBlank()) {
            Log.d("Selected Date", "ProfileData: $day")
            var mMonth:String? = null
            if(month=="01"){
                mMonth = "January"
            }
            if(month=="02"){
                mMonth="February"
            }
            if(month=="03"){
                mMonth="March"
            }
            if(month=="04"){
                mMonth="April"
            }
            if(month=="05"){
                mMonth="May"
            }
            if(month=="06"){
                mMonth="June"
            }
            if(month=="07"){
                mMonth="July"
            }
            if(month=="08"){
                mMonth="August"
            }
            if(month=="09"){
                mMonth="September"
            }
            if(month=="10"){
                mMonth="October"
            }
            if(month=="11"){
                mMonth="November"
            }
            if(month=="12"){
                mMonth="December"
            }
                viewModel.getCustomMemberReportData(
                    profileState.data?.token.toString(),
                    profileState.data?.member_id.toString(),
                    "${month}/${day}/${year}",
                    mMonth,
                    year,
                    profileState.data?.church_id.toString()
                )
            }
        }

    when (profileState) {
        is Resource.Success -> {
            LaunchedEffect(reportState) {
                if (reportState is Resource.Idle) {
                    viewModel.getMemberReportData(
                        profileState.data?.token.toString(),
                        profileState.data?.member_id.toString(),
                        profileState.data?.church_id.toString()
                    )
                }
            }
        }
        is Resource.Error -> {
            ErrorPage(navController)
            Toast(message = profileState.message.toString())
        }

        is Resource.Idle -> {}
        is Resource.Loading -> {
            Loader()

        }
    }
    when (reportState) {
        is Resource.Success -> {
            if (reportState.data != null) {
                Dashboard(profileState, navController, reportState,viewModel)

            }
        }
        is Resource.Error -> {
            Toast(message = reportState.message.toString())
            ErrorPage(navController)
        }

        is Resource.Idle -> {}
        is Resource.Loading -> {
            Loader()
        }
    }
    when (customReportState) {
        is Resource.Success -> {
            if (customReportState.data != null) {
                Dashboard(profileState, navController, customReportState,viewModel)

            }
        }
        is Resource.Error -> {
            Toast(message = customReportState.message.toString())
            ErrorPage(navController)
        }

        is Resource.Idle -> {}
        is Resource.Loading -> {
            Loader()
        }
    }
}
@Composable
private  fun LogoutText(){
 Toast(message = "lo")
}


@Composable
private fun Dashboard(
    profileState: Resource<Profile>,
    navController: NavHostController,
    reportState: Resource<MemberReportData>,
    viewModel: HomeViewModel

) {
    Column {
        HeaderCard(profileState.data, navController,viewModel)
        Report(reportState.data)
        ReceiptCardView(reportState.data, navController)
    }
}


@Composable
fun HeaderCard(profile: Profile?,navController: NavHostController,viewModel: HomeViewModel){
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val scope = rememberCoroutineScope()
    var showToast by remember { mutableStateOf(false) }
    if (showToast) {
        Toast(message = "Logging out...")
        // Reset showToast state after showing the toast
        showToast = false
    }
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
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(10.dp),
                        painter = painterResource(id = R.drawable.ic_launcher_round),
                        contentDescription = "app Logo"
                    )
                        Row {
                            Column {
                                Text(
                                    text = "Hello ${profile?.first_name}",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center,
                                    color = textColor
                                )
                                Text(
                                    text = "Welcome to ${profile?.church_name}",
                                    modifier = Modifier
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center,
                                    color = textColor

                                )
                            }

                        }
                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .padding(10.dp)
                            .align(Alignment.CenterVertically)
                            .clickable {
                                scope.launch {
                                    showToast = true
                                    viewModel.updateLoginStatus(false)
                                    delay(3000)
                                    navController.navigate("splash")


                                }
                            },
                        painter = painterResource(id = R.drawable.baseline_exit_to_app_24),
                        contentDescription = "app Logo",



                        )
                }


        }
    }

}
@Composable
fun ReceiptCardView(memberReportData: MemberReportData?,navController: NavHostController) {
    LazyColumn(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        memberReportData?.contribution?.forEach { contribution ->
            item {
                ReceiptCard(receipt = contribution, navController = navController)
            }
        }
    }
}

@Composable
fun ReceiptCard(receipt:Contribution?,navController: NavHostController){
   val scope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val currencyFormat = remember {
        NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance("KSh")
        }
    }
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
                        text = "${receipt?.receiptNumber}",
                        modifier = Modifier
                            .padding(5.dp),
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = "${receipt?.status}",
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )

                    Text(
                        text = "${receipt?.date}",
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
                        text = "${receipt?.firstName}  ${receipt?.secondName} ${receipt?.surname}",
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
                        text = currencyFormat.format(receipt?.amount ?: 0.0),
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

                }
            }
        }
    }
}
@Composable
fun ErrorPage(navController: NavHostController){
    val scope = rememberCoroutineScope()
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    Box (
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxHeight()
            .fillMaxWidth(),

    ) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(
                text = "Something went wrong,Please try again later",
                modifier = Modifier
                    .padding(4.dp)
                    .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = textColor
            )
            Button(
                onClick = {
              scope.launch {
                  navController.navigate("home_screen")
              }
                },
                modifier = Modifier
                    .padding(5.dp)
                    .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    containerColor = md_theme_light_primary,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(5.dp)
            ) {
                Text(
                    text = "Try Again",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
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
fun Report(memberReportData: MemberReportData?) {
    val isDarkTheme = isSystemInDarkTheme()
    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
    val textColor = if (isDarkTheme) Color.White else Color.Black
    val currencyFormat = remember {
        NumberFormat.getCurrencyInstance().apply {
            currency = Currency.getInstance("KSh")
        }
    }
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
                        text = currencyFormat.format(memberReportData?.dayContribution?.dayContribution ?: 0.0),
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = currencyFormat.format(memberReportData?.monthContribution?.monthContribution ?: 0.0),
                        modifier = Modifier
                            .padding(4.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = textColor
                    )
                    Text(
                        text = currencyFormat.format(memberReportData?.yearContribution?.yearContribution ?: 0.0),
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
//@Composable
//fun ReceiptPreview(
//    contribution: Contribution?
//) {
//    val isDarkTheme = isSystemInDarkTheme()
//    val backgroundColor = if (isDarkTheme) Color.Black else Color.White
//    val textColor = if (isDarkTheme) Color.White else Color.Black
//    Box(
//        modifier = Modifier
//            .background(color = backgroundColor)
//            .fillMaxWidth(),
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp)
//        ) {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(40.dp)
//            ) {
//                Text(
//                    text = "Print Preview",
//                    modifier = Modifier.align(Alignment.Center),
//                    style = MaterialTheme.typography.bodyLarge,
//                    color = textColor
//                )
//            }
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(top = 20.dp)
//                    .padding(bottom = 20.dp),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Text(
//                    text = "METHODIST CHURCH IN KENYA",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                    modifier = Modifier.padding(5.dp),
//                    color = textColor
//                )
//                Text(
//                    text = "NYAMBENE SYNOD",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                    modifier = Modifier.padding(5.dp),
//                    color = textColor
//                )
//                Text(
//                    text = "MCK KIEGOI",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                    modifier = Modifier.padding(5.dp),
//                    color = textColor
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "$receiptNumber",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(5.dp),
//                        color = textColor
//                    )
//                    Text(
//                        text = "$date",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(5.dp),
//                        color = textColor
//                    )
//                }
//
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(2.dp)
//                        .background(Color.Gray)
//                        .padding(5.dp)
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "Name",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                    Text(
//                        text = "Amount",
//                        fontWeight = FontWeight.Bold,
//                        fontSize = 16.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "Member Name",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                    Text(
//                        text = "00.00",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                }
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(2.dp)
//                        .background(Color.Gray)
//                        .padding(5.dp)
//                )
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "TOTAL THIS MONTH",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                    Text(
//                        text = "00.00",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                }
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceBetween
//                ) {
//                    Text(
//                        text = "TOTAL THIS YEAR",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                    Text(
//                        text = "00.00",
//                        fontSize = 14.sp,
//                        modifier = Modifier.padding(4.dp),
//                        color = textColor
//                    )
//                }
//                Text(
//                    text = "Payment of:Tithe",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                    modifier = Modifier.padding(4.dp),
//                    color = textColor
//                )
//            }
//        }
//    }
//}



@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun HeaderCardPreview(){
    HeaderCard(profile = null, rememberNavController(), viewModel())

}
@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun ReceiptCardPreview(){
    ReceiptCard(receipt = null, navController = rememberNavController())
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
 Report(memberReportData = null)
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview
fun ErrorPagePrev(){
    ErrorPage(navController = rememberNavController())
}




