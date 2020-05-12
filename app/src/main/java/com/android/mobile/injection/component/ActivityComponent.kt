package com.android.mobile.injection.component

import com.android.mobile.injection.PerActivity
import com.android.mobile.injection.module.ActivityModule
import com.android.mobile.features.base.BaseActivity
import com.po.kemon.features.main.MainActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(baseActivity: BaseActivity)

    fun inject(mainActivity: MainActivity)
}
