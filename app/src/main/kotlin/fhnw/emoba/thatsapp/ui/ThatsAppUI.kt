package fhnw.emoba.thatsapp.ui


import androidx.compose.animation.Crossfade

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import fhnw.emoba.thatsapp.model.HomeModel
import fhnw.emoba.thatsapp.model.LoginModel
import fhnw.emoba.thatsapp.model.Screen
import fhnw.emoba.thatsapp.model.ThatsAppModel
import fhnw.emoba.thatsapp.ui.components.Loading
import fhnw.emoba.thatsapp.ui.screens.HomeScreen
import fhnw.emoba.thatsapp.ui.screens.LoginScreen
import fhnw.emoba.thatsapp.ui.theme.MaterialAppTheme


@Composable
fun AppUI(model: ThatsAppModel) {
    with(model) {
        MaterialAppTheme(darkTheme = false) {
            Crossfade(targetState = screenState.screen, label = "AppCrossfade") { screen ->
                when (screen) {
                    Screen.HOME -> {
                        val homeModel = screenState.model as? HomeModel
                        if (homeModel != null && !homeModel.isLoading) {
                            HomeScreen(homeModel)
                        } else {
                            Loading()
                        }
                    }

                    Screen.LOGIN -> {
                        val loginModel = screenState.model as? LoginModel
                        if (loginModel != null && !loginModel.isLoading) {
                            LoginScreen(loginModel)
                        } else {
                            Loading()
                        }
                    }

                    else -> Text("Screen not implemented yet")
                }
            }
        }
    }
}

