package amldev.currency.extensions

import amldev.currency.data.Constants
import amldev.currency.ui.activities.preferences.PreferencesActivity
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.google.gson.JsonParser
import java.io.InputStreamReader

/********************************************************************************
 * Created by Anartz Mugika (mugan86@gmail.com) on 6/8/17.
 ********************************************************************************/
fun showHideKeyBoardForce(editText: EditText, show: Boolean, context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (show) {
        editText.requestFocus()
        editText.selectAll()
        inputMethodManager.toggleSoftInputFromWindow(editText.applicationWindowToken, InputMethodManager.SHOW_FORCED, 0)
    } else inputMethodManager.toggleSoftInputFromWindow(editText.applicationWindowToken, InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
}

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
fun getJSONResource(context: Context, name: String): String? {
    try {
        context.assets.open("$name.json").use({ `is` ->
            val parser = JsonParser()
            return parser.parse(InputStreamReader(`is`)).toString()
        })
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun getFlagDrawable(context: Context, flag: String) =
        context.resources.getIdentifier("ic_$flag", "drawable", context.packageName)

fun Activity.goToMarket() {
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ourAppUrlGooglePlay(this))))
    } catch (anfe: android.content.ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(Constants.ourAppUrlAndroidMarket(this))))
    }
    overridePendingTransition(0, 0)
}

fun Activity.back() {
    startActivity(Intent(this, PreferencesActivity::class.java))
    this.finish()
    this.overridePendingTransition(0, 0)
}


fun EditText.afterTextChanged(afterTextChanged: StringUnit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) = afterTextChanged.invoke(editable.toString())
    })
}