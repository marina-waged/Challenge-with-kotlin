package com.example.elmenuschallenge.base.basePresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<T : ParentInterface> : ParentPresenter<T>
{
    var view: T? = null
        private set
    private var compositeDisposable = CompositeDisposable()

    override fun attachedView(mvpView: T)
    {
        view = mvpView
    }

    override fun detachedView()
    {
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }


    private val isViewAttached: Boolean?
        get() = view != null

    fun checkViewAttached()
    {
        if ((isViewAttached) == null)
            throw MvpViewNotAttachedException()
    }

    fun addDisposable(disposable: Disposable) {
        this.compositeDisposable.add(disposable)
    }

    // must be static class
    class MvpViewNotAttachedException : RuntimeException("Please call Presenter.attachView(MvpView) before" + " requesting data to the Presenter")
}

