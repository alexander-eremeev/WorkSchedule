package com.childmathematics.android.workschedule

//import androidx.compose.material.icons.filled.Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.childmathematics.android.workschedule.navigation.ui.setEdgeToEdgeConfig

import com.childmathematics.android.workschedule.navigation.commonui.NavCommonUiAct


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setEdgeToEdgeConfig()
        super.onCreate(savedInstanceState)
        setContent {
            NavCommonUiAct()
        }
    }
}
