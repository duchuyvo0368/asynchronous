package com.voduchuy.rxjava

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers




class MyViewModelFactory constructor(private val repository: Repository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
        }
    }
class MainViewModel (private val repository: Repository): ViewModel() {
    private val _data:MutableLiveData<Todo> = MutableLiveData<Todo>()
    val data: LiveData<Todo> =_data
    lateinit var disposable: Disposable

    fun getAll(){
        val response=repository.getAll()
        response.subscribeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribe(getTodoAll())
    }

    private fun getTodoAll(): Observer<Todo> {
        return object :Observer<Todo>{
            override fun onSubscribe(d: Disposable) {
                disposable = d
            }

            override fun onError(e: Throwable) {
                _data.postValue(null)
            }

            override fun onComplete() {

            }

            override fun onNext(t: Todo) {
                _data.postValue(t)
            }
        }
    }

}
