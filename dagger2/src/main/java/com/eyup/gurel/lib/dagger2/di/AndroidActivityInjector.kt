package com.eyup.gurel.lib.dagger2.di

import com.eyup.gurel.lib.dagger2.lifecycle.DisposableManager
import dagger.android.AndroidInjector

interface AndroidActivityInjector<T> : AndroidInjector<T> {
    @ForActivityScreen
    fun disposableManager(): DisposableManager
}
