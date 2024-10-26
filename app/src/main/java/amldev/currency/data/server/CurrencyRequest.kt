package amldev.currency.data.server

import amldev.currency.data.Constants
import amldev.currency.domain.model.Money
import amldev.currency.extensions.DataPreference
import amldev.currency.extensions.getFlagDrawable
import amldev.currency.extensions.getJSONResource
import amldev.currency.extensions.isNetworkConnected
import android.content.Context
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.google.gson.Gson
import java.net.URL

/******************************************************************************************************************
 * Created by Anartz Mugika on 19/07/2017.
 * Take data from server (if network connected) or from assets (not connected) with pass values in constructor
 ******************************************************************************************************************/
class CurrencyRequest(private val baseMoney: String = "EUR") {
    companion object {
        const val symbols = "AUD,CAD,CHF,CNY,EUR,GBP,INR,JPY,MYR,RUB,SGD,USD"
        // BRL,DKK,ILS,HKD
        // https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html
        private const val URL_LOCALHOST = "https://api.exchangeratesapi.io/latest?symbols=$symbols&base="
        // https://api.exchangeratesapi.io/latest?symbols=AUD,CAD,CHF,CNY,EUR,GBP,INR,JPY,MYR,RUB,SGD,USD&base=GBP
    }

    fun execute(context:Context) : CurrencyResult {
        if (isNetworkConnected(context) && DataPreference.getPreferenceBoolean(context, Constants.USE_INTERNET)) {
            println("Take data from internet!!")
            var currencyResult: CurrencyResult
            try {
                currencyResult = Gson().fromJson(URL(URL_LOCALHOST + baseMoney).readText(), CurrencyResult::class.java)
            } catch (e: Exception) {
                currencyResult = Gson().fromJson(getJSONResource(context, "currencies/${baseMoney.toLowerCase()}"), CurrencyResult::class.java)
            }
            return currencyResult
        }
        //Without Internet, take assets/currencies/baseMoney.json file
        return Gson().fromJson(getJSONResource(context, "currencies/${baseMoney.toLowerCase()}"), CurrencyResult::class.java)
    }

    //Load start money info list to use to select our base money
    fun getMoneyList(context:Context) : ArrayList<Money> {
        val moneys: ArrayList<Money> = ArrayList()
        ((Parser().parse(StringBuilder(getJSONResource(context, "list-currencies")))
                as JsonObject)["currencies"] as JsonArray<*>).map {
            money ->
            val data = money as JsonObject

            moneys.add(Money(data["symbol"].toString(), 0.0, data["name"].toString(), data["flag"].toString(), getFlagDrawable(context, data["flag"].toString())))
        }
        return moneys
    }

}


