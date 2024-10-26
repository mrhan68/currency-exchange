package amldev.currency.interfaces

import amldev.currency.domain.model.Currency
import amldev.currency.extensions.CurrencyUnit
import amldev.currency.extensions.MoneysListUnit
import android.content.Context

/**
 * Created by anartzmugika on 13/1/18.
 */
interface Provider {
    fun loadCurrenciesList(context: Context, f: MoneysListUnit)
    fun loadMoneySelectData(selectCurrency_: Currency, context: Context, f: CurrencyUnit)
}