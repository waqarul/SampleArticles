package com.wtmcodex.samplearticles.core.configuration

import android.content.SharedPreferences
import com.wtmcodex.samplearticles.base.Domain
import com.wtmcodex.samplearticles.constants.SharedPreferencesConstants


class SharedPreferencesStore : ISharedPreferencesStore {

    private val context = Domain.applicationContext

    private fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences(
            SharedPreferencesConstants.SHARED_PREFERENCES_FILE_NAME,
            0
        )
    }

    private fun getSharedPreferencesEditor(): SharedPreferences.Editor {
        val preferences = getSharedPreferences()
        return preferences.edit()
    }
}

