package com.eyup.gurel.lib.dagger2.di

import android.app.Activity
import androidx.fragment.app.Fragment

class Injector {
    companion object Factory {
        fun inject(activity: Activity) {ActivityInjector[activity].inject(activity)}

        fun clearComponent(activity: Activity) {
            ActivityInjector[activity].clear(activity)
        }

        fun inject(fragment: Fragment) {
            ScreenInjector[fragment.activity].inject(fragment)
        }

        fun clearComponent(fragment: Fragment) {
            ScreenInjector[fragment.activity].clear(fragment)
        }
    }
}