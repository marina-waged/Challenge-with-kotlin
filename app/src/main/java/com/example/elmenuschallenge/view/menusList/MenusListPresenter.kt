package com.example.elmenuschallenge.view.menusList

import android.content.Context
import android.util.Log
import com.example.elmenuschallenge.base.basePresenter.BasePresenter
import com.example.elmenuschallenge.model.ItemData
import com.example.elmenuschallenge.model.ItemsResponse
import com.example.elmenuschallenge.model.TagData
import com.example.elmenuschallenge.model.TagsResponse
import com.example.elmenuschallenge.roomDB.AppDatabase
import com.example.elmenuschallenge.utils.NetworkUtils
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.observers.DisposableObserver
import java.lang.ref.WeakReference


class MenusListPresenter(private var interactor: MenusListInteractor, private var ioScheduler: Scheduler,
                         private var mainScheduler: Scheduler, private val cnx : WeakReference<Context>) : BasePresenter<IMenusListController.View>(),
    IMenusListController.Presenter {

    private var dataBase = AppDatabase.getAppDataBase(cnx)
    private val commonMethod = NetworkUtils()

    override fun callGetTags(pageNumber: Int)
    {
        checkViewAttached()
        if(commonMethod.checkNetworkConnection(cnx))
        {
            addDisposable(interactor.getTagsList(pageNumber).subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribeWith(object : DisposableObserver<TagsResponse>()
                {
                    override fun onNext(response: TagsResponse) {
                        view!!.hideLoader()

                        if(!response.getTags().isNullOrEmpty())
                        {
                            saveTagsInDB(response.getTags()!!)
                            view!!.getTagsSuccess(response.getTags()!!)
                        }
                    }

                    override fun onComplete() {
                        view!!.hideLoader()
                    }

                    override fun onError(e: Throwable) {
                        view!!.hideLoader()
                        view!!.handleError(e.localizedMessage!!)
                    }

                }))
        }else{
            if(pageNumber == 0)
                getTagsFromDB()
        }
    }

    override fun callGetItems(tagName: String)
    {
        checkViewAttached()
        if(commonMethod.checkNetworkConnection(cnx))
        {
            addDisposable(interactor.getItemsList(tagName).subscribeOn(ioScheduler).observeOn(mainScheduler)
                .subscribeWith(object : DisposableObserver<ItemsResponse>()
                {
                    override fun onNext(response: ItemsResponse)
                    {
                        view!!.hideLoader()
                        if(!response.getItems().isNullOrEmpty())
                        {
                            saveItem(response.getItems()!!, tagName)
                            view!!.getItemsSuccess(response.getItems()!!)
                        }
                    }

                    override fun onComplete() {
                        view!!.hideLoader()
                    }

                    override fun onError(e: Throwable) {
                        view!!.hideLoader()
                        view!!.handleError(e.localizedMessage!!)
                    }

                }))
        }else
            getItemsByTagName(tagName)
    }

    private fun saveTagsInDB(tagsList: List<TagData>)
    {
        Observable.fromCallable {
            dataBase!!.tagsDao().insertAllTags(tagsList)

        }.doOnError()
        {
            Log.d("DB_Error","save tags error")
        }.doOnNext{
            Log.d("DB_Success","save tags done")
        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe()
    }

    private fun getTagsFromDB()
    {
        Observable.fromCallable {
            dataBase!!.tagsDao().tagsList

        }.doOnError(){
            view!!.hideLoader()
            Log.d("DB_Error","get tags error")

        }.doOnNext {list ->
            view!!.hideLoader()
            if(!list.isNullOrEmpty())
                view!!.getTagsSuccess(list)

        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe()
    }

    private fun saveItem(itemList : List<ItemData>, tagName : String)
    {
        Observable.fromCallable {
            for(index : Int in 0 until itemList.size)
                itemList[index].setItemTagName(tagName)

            dataBase!!.itemsDao().insertItems(itemList)

        }.doOnError()
        {
            Log.d("DB_Error","save tags error")
        }.doOnNext{
            Log.d("DB_Success","save tags done")
        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe()
    }

    private fun getItemsByTagName(tagName : String)
    {
        Observable.fromCallable {
            dataBase!!.itemsDao().getItemsByTagName(tagName)

        }.doOnError(){
            view!!.hideLoader()
            Log.d("DB_Error","get tags error")

        }.doOnNext {list ->
            view!!.hideLoader()
            if(!list.isNullOrEmpty())
                view!!.getItemsSuccess(list)

        }.subscribeOn(ioScheduler)
            .observeOn(mainScheduler)
            .subscribe()
    }
}