package model

import com.example.impacklink.api.User

data class ProfileResponse(

    val status:String,

    val user: User?,

    )