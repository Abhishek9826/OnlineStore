package com.example.onlinestore

class EProduct {

    val id: Int
    var name: String
    var price: Int
    var picture: String

    constructor(id: Int, name: String, price: Int, picture: String) {
        this.id = id
        this.name = name
        this.price = price
        this.picture = picture
    }
}