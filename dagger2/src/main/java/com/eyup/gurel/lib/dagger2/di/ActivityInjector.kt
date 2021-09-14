package com.eyup.gurel.lib.dagger2.di

import android.app.Activity
import android.content.Context
import dagger.android.AndroidInjector
import java.util.HashMap
import javax.inject.Inject
import javax.inject.Provider

class ActivityInjector @Inject internal constructor(private val activityInjectors: Map<Class<out Activity>, @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>) {
    fun inject(activity: Activity) {
        require(activity is InstanceInfoProvider) {  "Activity must implement InstanceInfoProvider" }
        val instanceId = (activity as InstanceInfoProvider).instanceId
        if (cache.containsKey(instanceId)) {
            (cache[instanceId] as AndroidInjector<Activity>).inject(activity)
            return
        }
        val injectorFactory = (activityInjectors[activity.javaClass] ?: error("Unknown class ${activity.javaClass.name}  asking for Injector Factory")).get() as AndroidInjector.Factory<Activity>
        val injector = injectorFactory.create(activity)
        cache[instanceId] = injector
        injector.inject(activity)
    }

    fun clear(activity: Activity) {
        require(activity is InstanceInfoProvider) { "Activity must implement InstanceInfoProvider" }
        val instance:InstanceInfoProvider = activity
        cache.remove(instance.instanceId)
    }

    companion object {
        private val cache: MutableMap<String, AndroidInjector<out Activity>> = HashMap()
        @JvmStatic
        operator fun get(context: Context): ActivityInjector {
            return (context.applicationContext as ProvidesActivityInjector).getActivityInjector()
        }
    }
}