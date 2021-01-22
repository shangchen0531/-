package com.example.wechatimitation

class Peo(private var name: String, private var imageId: Int) {
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getImageId(): Int {
        return imageId
    }

    fun setImageId(imageId: Int) {
        this.imageId = imageId
    }

}