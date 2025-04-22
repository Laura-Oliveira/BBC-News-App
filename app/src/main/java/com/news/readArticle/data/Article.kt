package com.news.readArticle.data

import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val title: String?,
    val description: String?,
    val publishedAt: String?,
    val content: String?,
    val urlToImage: String?
) : Parcelable
//{
//    constructor(parcel: Parcel) : this(
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString(),
//        parcel.readString()
//    )
//
//    override fun writeToParcel(parcel: Parcel, flags: Int)
//    {
//        parcel.writeString(title)
//        parcel.writeString(description)
//        parcel.writeString(publishedAt)
//        parcel.writeString(content)
//        parcel.writeString(urlToImage)
//    }
//
//    override fun describeContents(): Int
//    { return 0 }
//
//    companion object CREATOR : Parcelable.Creator<Article>
//    {
//        override fun createFromParcel(parcel: Parcel): Article
//        { return Article(parcel) }
//
//        override fun newArray(size: Int): Array<Article?>
//        { return arrayOfNulls(size) }
//    }
//}