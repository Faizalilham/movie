package code.faizal.androidexpert

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import code.faizal.androidexpert.presentation.screen.HomeActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        splash()
    }

    private fun splash(){
        Handler().postDelayed({
            startActivity(Intent(
                this@MainActivity, HomeActivity::class.java).
            also{finish()})
        },2000)
    }
}