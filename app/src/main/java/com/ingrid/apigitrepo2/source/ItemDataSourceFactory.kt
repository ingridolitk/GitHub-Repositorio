package com.ingrid.apigitrepo2.source

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.ingrid.apigitrepo2.model.Item


class ItemDataSourceFactory: DataSource.Factory<Int, Item>() {
    val itemLiveDataSource= MutableLiveData<GitDataSource>()

    override fun create(): DataSource<Int, Item> {

        val gitDataSource= GitDataSource()
        itemLiveDataSource.postValue(gitDataSource)
        return gitDataSource
    }
}