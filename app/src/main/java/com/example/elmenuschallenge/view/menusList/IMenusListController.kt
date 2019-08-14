package com.example.elmenuschallenge.view.menusList

import com.example.elmenuschallenge.base.basePresenter.ParentInterface
import com.example.elmenuschallenge.base.basePresenter.ParentPresenter
import com.example.elmenuschallenge.model.ItemData
import com.example.elmenuschallenge.model.TagData


interface IMenusListController
{
    interface View : ParentInterface
    {
        fun hideLoader()

        fun getTagsSuccess(tagsResponse: List<TagData>)
        fun getItemsSuccess(itemsResponse: List<ItemData>)

        fun handleError(msg : String)
    }

    interface Presenter : ParentPresenter<View>
    {
        fun callGetTags(pageNumber : Int)
        fun callGetItems(tagName : String)
    }
}