package th.ac.kku.cis.mobileapp.GiveShop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_provinces.*

class ListProvinces : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_provinces)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()


        val NameSetting: TextView = findViewById(R.id.textView2)
        val Profile: ImageView = findViewById(R.id.imageView)
        val Email: TextView = findViewById(R.id.textView3)
        auth = FirebaseAuth.getInstance()
        auth.currentUser!!.email
        val xx: Uri? = auth.currentUser!!.photoUrl
        NameSetting.text = auth.currentUser!!.displayName.toString()
        Picasso.get().load(xx).into(Profile)
        Email.text = auth.currentUser!!.email
        auth.currentUser!!.email



//        val arrayAdapter: ArrayAdapter<*>
//
//        val Province = resources.getStringArray(R.array.province)
//
//        // access the listView from xml file
//        var mListView = findViewById<ListView>(R.id.listviewpv1)
//        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, Province)
//        mListView.adapter = arrayAdapter
//
//        mListView.setOnItemClickListener { parent, view, position, id ->
//
//            val selectedItem = parent.getItemAtPosition(position) as String
//            Toast.makeText(this, selectedItem, Toast.LENGTH_SHORT).show()
        val province_name = resources.getStringArray(R.array.province)
        val province_list = mutableListOf<provinceS>()

        for (i in 0..province_name.size-1) {
            province_list.add(provinceS(province_name[i]))
        }
        val listView = findViewById<ListView>(R.id.listviewpv1)
        listView.adapter = ListAdapter(
            this,
            R.layout.list_province,
            province_list
        )
        listviewpv1.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as provinceS
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ListProduct::class.java)
             intent.putExtra("name", selectedItem.name)//ส่งไปยัง ListProduct
            startActivity(intent)
        }
    }
}
