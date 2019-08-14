package com.example.elmenuschallenge.api

import com.example.elmenuschallenge.model.ItemsResponse
import com.example.elmenuschallenge.model.TagsResponse
import com.example.elmenuschallenge.utils.ITEMS_URL
import com.example.elmenuschallenge.utils.TAGS_URL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface MenusListApis
{
    @GET(TAGS_URL)
    fun getTagsList(@Path("pageNumber") pageNumber : Int): Observable<TagsResponse>

    @GET(ITEMS_URL)
    fun getItemsList(@Path("tagName") tagName : String): Observable<ItemsResponse>
}