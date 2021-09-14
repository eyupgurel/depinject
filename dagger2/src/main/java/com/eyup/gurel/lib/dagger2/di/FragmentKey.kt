package com.eyup.gurel.lib.dagger2.di

import androidx.fragment.app.Fragment
import dagger.MapKey
import kotlin.reflect.KClass

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class FragmentKey(val value: KClass<out Fragment>)
