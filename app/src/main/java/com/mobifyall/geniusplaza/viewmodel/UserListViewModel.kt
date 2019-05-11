package com.mobifyall.geniusplaza.viewmodel

import androidx.lifecycle.ViewModel
import com.app.androidapp.service.RetrofitFactory
import com.mobifyall.geniusplaza.model.Model
import com.mobifyall.geniusplaza.views.UserListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserListViewModel : ViewModel() {
    lateinit var navigator: UserListView;
    lateinit var userData: Model.UsersData


    public fun getUsersFromServer(loadMore: Boolean) {
//        if (userData == null)
        userData = Model.UsersData(0, 0, 0, 0, emptyList())
        RetrofitFactory.create().getUsers(userData.page + 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                run {
                    userData = result
                    if (loadMore)
                        navigator.showResultLoadMore(result)
                    else
                        navigator.showResult(result)
                }
            },
                { error ->
                    run {
                        if (loadMore) {

                        } else {
                            navigator.showError(error)
                            navigator.showTryAgain()
                            navigator.hideList()
                        }
                    }
                })
    }

    public fun loadMore() {
        RetrofitFactory.create().getUsers(userData.page + 1).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->
                run {
                    userData = result

                    navigator.showResultLoadMore(result)
                }
            },
                { error ->
                    run {

                    }
                })
    }


}
