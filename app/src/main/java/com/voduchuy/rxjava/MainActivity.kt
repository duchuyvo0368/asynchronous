package com.voduchuy.rxjava

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.voduchuy.rxjava.databinding.ActivityMainBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    private val retrofitService = ApiClient.getApiInterface()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(Repository(retrofitService))).get(
                MainViewModel::class.java
            )
        viewModel.data.observe(this) {
            if (it != null) {
                binding.userId.text = it.userId.toString()
                binding.id.text = it.id.toString()
                binding.title.text = it.title
                binding.completed.text = it.completed.toString()
            }
        }
        viewModel.getAll()

    }


    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable.dispose()
    }
}