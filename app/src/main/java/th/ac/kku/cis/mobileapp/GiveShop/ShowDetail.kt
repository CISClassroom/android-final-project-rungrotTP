package th.ac.kku.cis.mobileapp.GiveShop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ShowDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()
    }
}
