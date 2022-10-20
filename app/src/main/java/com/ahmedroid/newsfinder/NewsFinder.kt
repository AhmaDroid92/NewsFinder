package com.ahmedroid.newsfinder

import android.app.Application
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.HiltAndroidApp
import utils.BindingAdapters
import javax.inject.Inject

@HiltAndroidApp
class NewsFinder : Application() {

    @Inject lateinit var bindingAdapters: BindingAdapters

    override fun onCreate() {
        super.onCreate()
        DataBindingUtil.setDefaultComponent(object : DataBindingComponent {

            override fun getBindingAdapters(): BindingAdapters {
                return this@NewsFinder.bindingAdapters
            }
        })
    }
}