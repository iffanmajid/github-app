package com.android.mobile.injection.component

import com.android.mobile.features.main.contributors.ContributorsFragment
import com.android.mobile.features.main.repositories.RepositoriesFragment
import com.android.mobile.injection.PerFragment
import com.android.mobile.injection.module.FragmentModule
import dagger.Subcomponent

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = [FragmentModule::class])
interface FragmentComponent {
    fun inject(contributorsFragment: ContributorsFragment)
    fun inject(repositoriesFragment: RepositoriesFragment)
}