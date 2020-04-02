package th.ac.kku.cis.mobileapp.GiveShop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_prodects.*

class ListProdects : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    lateinit var adapter: AdapterProduct
    private var listViewItems: ListView? = null
    var toDoGiveShopList: MutableList<GiveShopP>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_prodects)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()




        val goaddProducts: Button = findViewById(R.id.button2)
        var name = getIntent().getStringExtra("name")
        goaddProducts.setOnClickListener {
            var i = Intent(this, addProducts::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            i.putExtra("name",name)//ส่งไปยัง addProducts
            //i.putExtra("name1", name)
            startActivity(i)
        }
        listViewItems = findViewById<View>(R.id.view2) as ListView
        toDoGiveShopList = mutableListOf<GiveShopP>()
        adapter = AdapterProduct(this, toDoGiveShopList!!)
        listViewItems!!.setAdapter(adapter)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.child("GiveShop").addListenerForSingleValueEvent(object: ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val items = dataSnapshot.children.iterator()

                var name = getIntent().getStringExtra("name")

                // Check if current database contains any collection
                if (items.hasNext()) {
                    while (items.hasNext()) {
                        val toDoListindex = items.next()
                        val map = toDoListindex.getValue() as HashMap<String, Any>

                        if (map.get("provinceS") == name) {
                            // add data to object
                            val todoItem = GiveShopP.create()
                            todoItem.ProductName = map.get("productName") as String?
                            todoItem.ProductData = map.get("productData") as String?
                            todoItem.ProductPrice = map.get("productPrice") as String?
                            todoItem.ProductType = map.get("productType") as String?
                            todoItem.ProductLocation = map.get("productLocation") as String?
                            todoItem.Mobile = map.get("mobile") as String?
                            todoItem.ProductEmail = map.get("productEmail") as String?

                            toDoGiveShopList!!.add(todoItem);
                            adapter.notifyDataSetChanged()
                        }
                    }
                }
            }
            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
             view2.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as GiveShopP //ดึง
            //Toast.makeText(this,selectedItem,Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ShowDetail::class.java)
             intent.putExtra("ProductName", selectedItem.ProductName)//ส่งไปยัง
             intent.putExtra("proName", selectedItem.ProductName)
             intent.putExtra("ProType", selectedItem.ProductType)
             intent.putExtra("proprice", selectedItem.ProductPrice)
             intent.putExtra("ProLocation", selectedItem.ProductLocation)
             intent.putExtra("proData", selectedItem.ProductData)
             intent.putExtra("ProMobile", selectedItem.Mobile)
             intent.putExtra("proEmail", selectedItem.ProductEmail)
             startActivity(intent)

        }
        val gotoBack: Button = findViewById(R.id.button)

        gotoBack.setOnClickListener {
            var i = Intent(this, ListProvinces::class.java)
            startActivity(i)
        }
    }
}
