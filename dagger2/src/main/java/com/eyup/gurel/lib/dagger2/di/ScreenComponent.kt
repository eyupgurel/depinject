package com.eyup.gurel.lib.dagger2.di

import com.eyup.gurel.lib.dagger2.lifecycle.DisposableManager
import dagger.android.AndroidInjector

interface ScreenComponent<T> : AndroidInjector<T> {
    @ForScreen
    fun disposableManager(): DisposableManager
}
