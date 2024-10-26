package amldev.currency.ui.activities.money_conversions.di

import amldev.currency.ui.activities.money_conversions.SelectMoneyConversionsActivity
import dagger.Subcomponent

/********************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 13/1/18.
 ********************************************************************************/
@Subcomponent(modules = [SelectMoneyConversionsModule::class])
interface SelectMoneyConversionsComponent {
    fun inject(activity: SelectMoneyConversionsActivity)
}