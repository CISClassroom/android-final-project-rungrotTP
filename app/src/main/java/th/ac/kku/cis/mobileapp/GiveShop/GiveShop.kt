package th.ac.kku.cis.mobileapp.GiveShop

class GiveShopP {
    companion object Factory {
        fun create(): GiveShopP = GiveShopP()
    }

    var ProvinceS :String? =null
    var ProductID: String? = null
    var ProductName: String? = null
    var ProductType: String? = null
    var ProductPrice: String? = null
    var ProductLocation: String? = null
    var ProductData: String? = null

    var Mobile: String? = null
    var ProductEmail: String? = null
}

class provinceS (var name:String)