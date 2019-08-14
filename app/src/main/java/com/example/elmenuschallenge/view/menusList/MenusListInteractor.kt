package com.example.elmenuschallenge.view.menusList

import com.example.elmenuschallenge.api.MenusListApis
import com.example.elmenuschallenge.model.ItemsResponse
import com.example.elmenuschallenge.model.TagsResponse
import io.reactivex.Observable
import java.io.IOException

class MenusListInteractor(private var menusListApis: MenusListApis) : IMenusListInteractor
{
    override fun getTagsList(pageNumber: Int): Observable<TagsResponse>
    {
        return Observable.defer {
            menusListApis.getTagsList(pageNumber)
                .retryWhen { observable ->
                    observable.flatMap { throwable ->
                        if (throwable is IOException) {
                            Observable.error<Any>(Throwable("Please Check Your Internet Connection"))
                        }
                        Observable.error<Any>(throwable)
                    }
                }
        }
    }

    override fun getItemsList(tagName: String): Observable<ItemsResponse> {
        return Observable.defer {
            menusListApis.getItemsList(tagName)
                .retryWhen { observable ->
                    observable.flatMap { throwable ->
                        if (throwable is IOException) {
                            Observable.error<Any>(Throwable("Please Check Your Internet Connection"))
                        }
                        Observable.error<Any>(throwable)
                    }
                }
        }
    }

}