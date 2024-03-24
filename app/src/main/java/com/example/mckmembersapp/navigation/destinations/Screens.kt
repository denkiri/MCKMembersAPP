package com.example.mckmembersapp.navigation.destinations

import java.lang.IllegalArgumentException

enum class Screens {
    SplashScreen;


    companion object {
         fun fromRoute(route: String?): Screens
          = when(route?.substringBefore("/")) {
             SplashScreen.name -> SplashScreen
             null -> SplashScreen
             else -> throw IllegalArgumentException("Route $route is not recognized")
          }
    }


}