package com.example.elmenuschallenge.view.menusList

import com.example.elmenuschallenge.model.ItemsResponse
import com.example.elmenuschallenge.model.TagsResponse
import io.reactivex.Observable

interface IMenusListInteractor
{
    fun getTagsList(pageNumber : Int) : Observable<TagsResponse>
    fun getItemsList(tagName: String) : Observable<ItemsResponse>
}