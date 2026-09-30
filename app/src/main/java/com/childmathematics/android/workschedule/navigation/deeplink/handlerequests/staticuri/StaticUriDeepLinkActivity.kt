package com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.staticuri

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.net.toUri
import androidx.lifecycle.compose.dropUnlessResumed
import com.childmathematics.android.workschedule.navigation.common.deeplink.EntryScreen
import com.childmathematics.android.workschedule.navigation.common.deeplink.PaddedButton
import com.childmathematics.android.workschedule.navigation.common.deeplink.TextContent
import com.childmathematics.android.workschedule.navigation.deeplink.handlerequests.uriarguments.ui.PATH_BASE
import com.childmathematics.android.workschedule.navigation.ui.setEdgeToEdgeConfig

const val HOME_URI = "$PATH_BASE/home"

class StaticUriDeepLinkActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setEdgeToEdgeConfig()
        super.onCreate(savedInstanceState)

        setContent {
            EntryScreen("Deep link url:") {
                TextContent(HOME_URI)
                PaddedButton("Deeplink Away!", onClick = dropUnlessResumed {
                    val intent = Intent(
                        this@StaticUriDeepLinkActivity,
                        MainActivity::class.java
                    )
                    // the uri to deep link with
                    intent.data = HOME_URI.toUri()
                    startActivity(intent)
                })
            }
        }
    }
}
