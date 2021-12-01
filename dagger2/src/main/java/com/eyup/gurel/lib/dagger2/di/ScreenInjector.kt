package com.eyup.gurel.lib.dagger2.di

import android.app.Activity
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider


@ActivityScope
class ScreenInjector @Inject internal constructor(private val screenInjectors: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Fragment>>>) {
    fun inject(fragment: Fragment) {
       // require(fragment is BaseFragment) { "Fragment must extend BaseFragment" }

        val instanceId = fragment.requireArguments().getString("instance_id")
        if (cache.containsKey(instanceId)) {
            cache[instanceId]!!.inject(fragment)
            return
        }
        val injectorFactory = (screenInjectors[fragment.javaClass] ?: error(  "${fragment.javaClass.name } is not in the multibinding map!")).get() as AndroidInjector.Factory<Fragment>
        val injector = injectorFactory.create(fragment)
        cache[instanceId] = injector
        injector.inject(fragment)
    }

    fun clear(fragment: Fragment) {
        if(fragment.arguments == null || cache.isEmpty()) return
        val instanceId = fragment.requireArguments().getString("instance_id")
        if (cache.containsKey(instanceId)) {
            val injector: AndroidInjector<*> =
                cache.remove(instanceId)!!
            if (injector is ScreenComponent<*>) {
                injector.disposableManager().dispose()
            }
        }
    }

    companion object {
        private val cache: MutableMap<String?, AndroidInjector<Fragment>> = HashMap()
        @JvmStatic
        operator fun get(activity: Activity?): ScreenInjector {
            require(activity is ScreenInjectorProvider) { "Fragment must hosted by a ScreenInjectorProvider" }
            return (activity as ScreenInjectorProvider).screenInjector
        }
    }
}