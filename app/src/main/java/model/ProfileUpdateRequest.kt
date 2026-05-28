package model

data class ProfileUpdateRequest(

    val id:Int,

    val name:String,

    val mobile:String,

    val about:String,

    val account_holder:String,

    val account_number:String

)