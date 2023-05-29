package com.aleksejb.notesinddd.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import com.aleksejb.core.ui.theme.AppTheme
import com.aleksejb.notesinddd.navigation.RootNode
import com.bumble.appyx.core.integration.NodeHost
import com.bumble.appyx.core.integrationpoint.NodeActivity

class MainActivity : NodeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Log.d("TAAAG", "reached setContent")
            AppTheme {
                NodeHost(
                    integrationPoint = appyxIntegrationPoint,
                ) { buildContext ->
                    RootNode(buildContext = buildContext)
                }
            }
        }
    }
}
