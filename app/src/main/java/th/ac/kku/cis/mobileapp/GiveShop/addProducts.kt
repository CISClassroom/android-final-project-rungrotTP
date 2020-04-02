package th.ac.kku.cis.mobileapp.GiveShop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_products.*

class addProducts : AppCompatActivity() {
    lateinit var GiveShopList: MutableList<GiveShopP>// <GiveShopP> database
    lateinit var mDatabase: DatabaseReference
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_products)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()


        GiveShopList = mutableListOf()
        //2 เพิ่มนักศึกษา--------------
        auth = FirebaseAuth.getInstance()
        // Id = auth.currentUser!!.uid
        mDatabase = FirebaseDatabase.getInstance().reference
        button3.setOnClickListener {
            AddDataPhamacy("String")
        }

    }
    fun AddDataPhamacy(data: String) {

        var name = getIntent().getStringExtra("name")
        var newData: GiveShopP = GiveShopP.create()
        val obj = mDatabase.child("GiveShop").push()
        // newData.NewName = name.toString()////////------
        newData.ProvinceS = name.toString()
        newData.ProductName = edit1.text.toString()
        newData.ProductData = edit2.text.toString()
        newData.ProductPrice = edit3.text.toString()
        newData.ProductType = edit4.text.toString()
        newData.ProductLocation = edit5.text.toString()
        newData.Mobile = edit6.text.toString()
        newData.ProductEmail = edit7.text.toString()
        newData.ProductID = obj.key
        obj.setValue(newData)


        Toast.makeText(applicationContext,"Product save successfully", Toast.LENGTH_LONG).show()
        var i = Intent(this, ListProdects::class.java)
        i.putExtra("name",name)//ส่งไป ListProduct
        startActivity(i)//รีเฟรชกลับไปหน้าก่อนนี้เพื่อรีเฟรชfirebase มาแสดง


    }
}
