package com.mobifyall.geniusplaza.model

import java.io.Serializable


object Model {
    data class User(val id : Int, val email : String, val first_name : String, val last_name : String,
                       val avatar : String) : Serializable

    data class UsersData (val page : Int, val per_page : Int, val total : Int, val total_pages : Int, val data: List<User>)


}
