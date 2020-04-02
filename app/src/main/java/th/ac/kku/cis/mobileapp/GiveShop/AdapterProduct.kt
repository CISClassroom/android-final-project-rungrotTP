package th.ac.kku.cis.mobileapp.GiveShop

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.content.Context
import android.provider.ContactsContract
import android.content.Context  as Context1
import android.widget.ArrayAdapter

class AdapterProduct(context: android.content.Context, toDoGiveShopList: MutableList<GiveShopP>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var itemList = toDoGiveShopList


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // create object from view
        val ProductName: String = itemList.get(position).ProductName as String


        val view: View
        val vh: ListRowHolder

        // get list view
        if (convertView == null) {
            view = mInflater.inflate(R.layout.activity_item_product, parent, false)
            vh = ListRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }

        // add text to view
        vh.label2.text = ProductName


        return view
    }

    override fun getItem(index: Int): Any {
        return itemList.get(index)
    }

    override fun getItemId(index: Int): Long {
        return index.toLong()
    }

    override fun getCount(): Int {
        return itemList.size
    }

    private class ListRowHolder(row: View?) {
        val label2: TextView = row!!.findViewById<TextView>(R.id.text1) as TextView
    }
}

class ListAdapter(var mCtx: Context1, var resource:Int, var items:List<provinceS>)
    : ArrayAdapter<provinceS>( mCtx , resource , items ) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        //super.getView(position, convertView, parent)

        val layoutInflater: LayoutInflater = LayoutInflater.from(mCtx)

        val view: View = layoutInflater.inflate(resource, null)
        var tvprovinceName : TextView = view.findViewById(R.id.textView6)



        var province: provinceS = items[position]

        tvprovinceName.text = province.name

        return view
    }
}

