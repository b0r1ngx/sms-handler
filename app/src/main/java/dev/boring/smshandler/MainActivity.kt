package dev.boring.smshandler

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Telephony
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.boring.smshandler.ui.theme.SMSHandlerTheme

private const val REQUEST_DEFAULT_APP = 7331

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMSHandlerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        val setSmsAppIntent = Intent(Telephony.Sms.Intents.ACTION_CHANGE_DEFAULT)
        setSmsAppIntent.putExtra(Telephony.Sms.Intents.EXTRA_PACKAGE_NAME, packageName)
        startActivityForResult(setSmsAppIntent, REQUEST_DEFAULT_APP)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode != Activity.RESULT_OK) {
            return
        }
        when (resultCode) {
            REQUEST_DEFAULT_APP -> //user allowed the app to become default
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SMSHandlerTheme {
        Greeting("Android")
    }
}