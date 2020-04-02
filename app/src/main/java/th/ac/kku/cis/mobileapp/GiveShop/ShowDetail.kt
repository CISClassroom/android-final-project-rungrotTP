package th.ac.kku.cis.mobileapp.GiveShop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_detail.*

class ShowDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_detail)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()

        var name = getIntent().getStringExtra("proName")
        var name1 = getIntent().getStringExtra("ProType")
        var name2 = getIntent().getStringExtra("proprice")
        var name3 = getIntent().getStringExtra("ProLocation")
        var name4 = getIntent().getStringExtra("proData")
        var name5 = getIntent().getStringExtra("ProMobile")
        var name6 = getIntent().getStringExtra("proEmail")

        text1.text = name
        text2.text = name1
        text3.text = name2
        text4.text = name3
        text5.text = name4
        text6.text = name5
        text7.text = name6
    }
}
