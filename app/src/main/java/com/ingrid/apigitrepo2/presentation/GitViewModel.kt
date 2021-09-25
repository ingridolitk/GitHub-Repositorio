package com.ingrid.apigitrepo2.presentation

import android.app.Application
import android.nfc.tech.MifareUltralight.PAGE_SIZE
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ingrid.apigitrepo2.model.Item
import com.ingrid.apigitrepo2.source.GitDataSource
import com.ingrid.apigitrepo2.source.ItemDataSourceFactory

class GitViewModel(application: Application) : AndroidViewModel(application) {

    val gitPagedList:LiveData<PagedList<Item>>
    private val liveDataSource:LiveData<GitDataSource>

    init {
        val mItemDataSourceFactory= ItemDataSourceFactory()
        liveDataSource=mItemDataSourceFactory.itemLiveDataSource
        val config=
            PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build()
        gitPagedList= LivePagedListBuilder(mItemDataSourceFactory,config).build()
    }
}