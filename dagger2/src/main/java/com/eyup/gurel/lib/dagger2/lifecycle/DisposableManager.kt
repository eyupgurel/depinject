
package com.eyup.gurel.lib.dagger2.lifecycle

import javax.inject.Inject
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class DisposableManager @Inject internal constructor() {
    private val compositeDisposable =  CompositeDisposable()
    fun add(vararg disposables: Disposable) {
        compositeDisposable.addAll(*disposables)
    }

    fun dispose() {
        compositeDisposable.clear()
    }
}
