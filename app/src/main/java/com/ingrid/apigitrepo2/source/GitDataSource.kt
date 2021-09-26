package com.ingrid.apigitrepo2.source

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.ingrid.apigitrepo2.data.RetrofitInstance
import com.ingrid.apigitrepo2.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okio.IOException

const val FIRST_PAGE_NUMBER = 1
const val PAGE_SIZE = 10

class GitDataSource : PageKeyedDataSource<Int, Item>() {

    private val queryLanguage = "language:kotlin"
    private val sort = "stars"
    private val order = "desc"

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Item>
    ) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = RetrofitInstance.apiService.getRepositories(
                    query = queryLanguage, sort = sort, order = order,
                    page = FIRST_PAGE_NUMBER, per_page = PAGE_SIZE
                )
                if (response.isSuccessful) {
                    response.body()?.items?.let {
                        callback.onResult(it, null, FIRST_PAGE_NUMBER + 1)
                    }
                } else {
                    Log.d("DataSource Bad", response.toString())
                }
            } catch (e: IOException) {
                Log.d("DataSource:loadInitial", "IOException " + e.message)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = RetrofitInstance.apiService.getRepositories(
                    query = queryLanguage, sort = sort, order = order,
                    page = params.key, per_page = PAGE_SIZE
                )
                if (response.isSuccessful) {
                    val key = if (params.key > 1) params.key - 1 else 0
                    response.body()?.items?.let {
                        callback.onResult(it,  key)
                    }
                }
            } catch (e: IOException) {
                Log.d("DataSource:loadBefore", "IOException " + e.message)
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Item>) {
        GlobalScope.launch(Dispatchers.IO) {

            try {
                val response = RetrofitInstance.apiService.getRepositories(
                    query = queryLanguage, sort = sort, order = order,
                    page = params.key, per_page = PAGE_SIZE
                )

                if (response.isSuccessful) {
                    val key = params.key + 1

                    response.body()?.items?.let {
                        callback.onResult(it,  key)
                    }
                }
            } catch (e: IOException) {
                Log.d("DataSource:loadAfter", "IOException " + e.message)
            }
        }
    }
}