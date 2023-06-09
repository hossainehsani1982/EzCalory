package di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.hossain_ehs.core.data.shared_preferences.DefaultPreferences
import com.hossain_ehs.core.domain.shared_preferences.Preferences
import com.hossain_ehs.core.domain.use_cases.FilterDigitsUseCases
import com.hossain_ehs.core.domain.use_cases.FilterOutDigits
import com.hossain_ehs.core.domain.use_cases.FilterOutDigitsAndDot
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app : Application
    ) : SharedPreferences {
        return app.getSharedPreferences("shared_pref", MODE_PRIVATE)
    }
    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences) : Preferences {
        return DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideCoreModuleUseCases() : FilterDigitsUseCases {
        return FilterDigitsUseCases(
            filterOutDigits = FilterOutDigits(),
            filterOutDigitsAndDot = FilterOutDigitsAndDot()
        )
    }
}