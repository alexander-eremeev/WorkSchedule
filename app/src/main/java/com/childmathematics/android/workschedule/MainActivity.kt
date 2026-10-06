package com.childmathematics.android.workschedule

//import androidx.compose.material.icons.filled.Home
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.childmathematics.android.workschedule.navigation.ui.setEdgeToEdgeConfig

import com.childmathematics.android.workschedule.navigation.commonui.NavCommonUiAct
import com.childmathematics.android.workschedule.navigation.conditional.NavConditionalAct
import com.childmathematics.android.workschedule.navigation.navscenedecorator.NavResponsiveNavigationSceneDecoratorAct
import com.childmathematics.android.workschedule.navigation.scenes.listdetail.NaListDetailAct
import com.childmathematics.android.workschedule.navigation.scenes.twopane.NavTwoPaneAct


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setEdgeToEdgeConfig()
        super.onCreate(savedInstanceState)
        setContent {
            //NavCommonUiAct()
            //NavConditionalAct()
            NavResponsiveNavigationSceneDecoratorAct()
            //NavTwoPaneAct()
            //NaListDetailAct()
        }
    }
}
