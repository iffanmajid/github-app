package com.android.mobile.injection.component

import com.android.mobile.injection.PerFragment
import com.android.mobile.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent