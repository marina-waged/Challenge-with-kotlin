package com.example.elmenuschallenge.base.basePresenter


interface ParentPresenter<V : ParentInterface>
{
    fun attachedView(mvpView: V)
    fun detachedView()
}
