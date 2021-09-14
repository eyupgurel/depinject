package com.eyup.gurel.lib.dagger2.di

import dagger.MapKey

@MapKey
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class SocketInterfaceEnumKey(val value:SocketInterfaceEnum)