package com.mobifyall.geniusplaza.views

import com.mobifyall.geniusplaza.model.Model

interface UserListView {
    fun showResult(result: Any)
    fun showError(error: Throwable?)
    fun hideList()
    fun showTryAgain()
    fun showResultLoadMore(result: Model.UsersData?)
}