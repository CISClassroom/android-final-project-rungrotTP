package th.ac.kku.cis.mobileapp.GiveShop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_provinces.*

class ListProvinces : AppCompatActivity() {

    var newpropro: Boolean = false


    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_provinces)
        if (supportActionBar != null) // เอาแถบบนออก
            supportActionBar?.hide()

        //logout button
        val btlogout: Button = findViewById(R.id.button4)
        btlogout.setOnClickListener({ v -> singOut() })

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
            val intent = Intent(this, ListProdects::class.java)
             intent.putExtra("name", selectedItem.name)//ส่งไปยัง ListProduct
            startActivity(intent)
        }

    }
    private fun passproject() {
        if (newpropro) {
            var i = Intent(this, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(i)
        }

    }

    //logout
    private fun singOut() {
        auth.signOut()
        newpropro = true
        passproject()
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            //show.text = "No User"
        } else {
            //show.text = user.email.toString()
            passproject()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuth(account!!)
                //FirebaseAuth(account)
            } catch (e: ApiException) {
                Log.i("Error OOP", e.toString())
                newpropro = false
                updateUI(null)
            }
        }
    }

    private fun firebaseAuth(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    newpropro = true
                    updateUI(user)
                } else {
                    newpropro = false
                    updateUI(null)
                }
            }
    }
}
