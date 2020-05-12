package com.android.mobile.injection


import com.android.mobile.injection.component.ConfigPersistentComponent
import javax.inject.Scope

/**
 * A scoping annotation to permit dependencies confirm to the life of the
 * [ConfigPersistentComponent]
 */
@Scope @Retention annotation class ConfigPersistent