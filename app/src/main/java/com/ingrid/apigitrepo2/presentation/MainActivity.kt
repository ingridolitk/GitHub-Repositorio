package com.ingrid.apigitrepo2.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ingrid.apigitrepo2.R
import com.ingrid.apigitrepo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var mAdapter: GitAdapter
    lateinit var gitViewModel: GitViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView(binding.recyclerView)
        setupViewModel()
        setObservables()
    }

    private fun initRecyclerView(recyclerView: RecyclerView) {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            mAdapter = GitAdapter()
            adapter = mAdapter
            val decoration =
                DividerItemDecoration(applicationContext, StaggeredGridLayoutManager.VERTICAL)
            decoration.setDrawable(this.resources.getDrawable(R.drawable.shape, null))
        }

    }

   private  fun setupViewModel(){
        gitViewModel= ViewModelProvider(this).get(GitViewModel::class.java)
    }

    private fun setObservables() {
        gitViewModel.gitPagedList.observe(this, Observer {
            mAdapter.submitList(it)
            mAdapter.notifyDataSetChanged()
        })
    }
}