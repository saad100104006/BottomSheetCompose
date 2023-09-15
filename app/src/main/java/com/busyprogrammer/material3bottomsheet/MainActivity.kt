package com.busyprogrammer.material3bottomsheet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.busyprogrammer.material3bottomsheet.ui.theme.Gray
import com.busyprogrammer.material3bottomsheet.ui.theme.Material3BottomSheetTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var showBottomSheet = remember { mutableStateOf(false) }
            Material3BottomSheetTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Button(onClick = {
                            showBottomSheet.value = true
                        }) {
                            Text(text = "Open Sheet")

                        }

                        if (showBottomSheet.value) {
                            showBottomSheet=  BottomSheetCompose(showBottomSheet)
                        }

                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetCompose(showBottomSheet: MutableState<Boolean>): MutableState<Boolean> {
    val modalBottomSheetState = rememberModalBottomSheetState()
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(onDismissRequest = {
        showBottomSheet.value = false
    },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Verification Required",
                    modifier = Modifier.padding(bottom = 8.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )

                Text(
                    modifier = Modifier.padding(bottom = 15.dp, start = 15.dp, end = 15.dp),
                    fontSize = 10.sp, color = Gray,
                    text = "Verification is required to move ahead in the process, if you don't verify you can't sign up, do you want to verify now?"
                )


                Row(modifier = Modifier.padding(start = 15.dp, bottom = 20.dp, end = 15.dp)) {

                    OutlinedButton(
                        modifier = Modifier
                            .weight(5f)
                            .padding(horizontal = 15.dp),
                        onClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                                showBottomSheet.value = false
                            }
                        },
                        border = BorderStroke(1.dp, Color.Red),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text(
                            text = "Not Now",
                            modifier = Modifier.padding(
                                horizontal = 10.dp,
                                vertical = 6.dp
                            ),
                            fontSize = 12.sp,
                            color = Color.Red
                        )

                    }

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                modalBottomSheetState.hide()
                                showBottomSheet.value = false
                            }
                        },
                        modifier = Modifier
                            .weight(5f)
                            .padding(horizontal = 15.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text(
                            text = "Verify Now",
                            modifier = Modifier.padding(horizontal = 10.dp, 6.dp),
                            color = Color.White,
                            fontSize = 12.sp
                        )

                    }


                }

            }

        }
    )
    return  showBottomSheet

}

