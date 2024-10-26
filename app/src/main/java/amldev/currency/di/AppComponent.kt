package amldev.currency.di

import amldev.currency.App
import amldev.currency.ui.activities.main.di.MainModule
import amldev.currency.ui.activities.money_conversions.di.SelectMoneyConversionsComponent
import amldev.currency.ui.activities.money_conversions.di.SelectMoneyConversionsModule
import amldev.currency.ui.activities.main.di.MainComponent
import dagger.Component
import javax.inject.Singleton

/**
 * Created by anartzmugika on 10/11/17.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun plus(mainModule: MainModule): MainComponent
    fun plus(selectMoneyModule: SelectMoneyConversionsModule): SelectMoneyConversionsComponent
    // fun plus(preferenceModule: GeneralPreferenceModule): GeneralPreferenceComponent
}