package com.example.mckmembersapp.navigation.destinations

import java.lang.IllegalArgumentException

enum class Screens {
    SplashScreen,
    HomeScreen;


    companion object {
         fun fromRoute(route: String?): Screens
          = when(route?.substringBefore("/")) {
             SplashScreen.name -> SplashScreen
             HomeScreen.name -> HomeScreen
             null -> HomeScreen
             else -> throw IllegalArgumentException("Route $route is not recognized")
          }
    }


}