package com.android.mobile.common.injection.component

import dagger.Component
import com.android.mobile.common.injection.module.ApplicationTestModule
import com.android.mobile.injection.component.AppComponent
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationTestModule::class))
interface TestComponent : AppComponent