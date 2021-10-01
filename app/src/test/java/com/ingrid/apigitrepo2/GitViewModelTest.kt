package com.ingrid.apigitrepo2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ingrid.apigitrepo2.model.GitHubResponse
import com.ingrid.apigitrepo2.model.Item
import com.ingrid.apigitrepo2.model.Owner
import com.ingrid.apigitrepo2.presentation.GitAdapter
import com.ingrid.apigitrepo2.presentation.GitViewModel
import com.ingrid.apigitrepo2.source.GitDataSource
import com.ingrid.apigitrepo2.source.ItemDataSourceFactory
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var gitListObserver: Observer<List<Item>>

    @Mock
    private lateinit var observerGitError: Observer<String>

    @Mock
    private lateinit var viewModel: GitAdapter.GitViewHolder

    @Test
    fun `when viewModel getMovie result in success then sets movieListLiveData`() {
        //Arrange

        val list = listOf<Item>(
            Item(
                id = 1,
                name = "",
                description = "",
                language = "",
                created_at = "",
                stargazers_count = 5,
                open_issues_count = 2,
                owner = Owner("login", "")
            )
        )

        //Arrange
        val resultSuccess = MockRepository()
        viewModel = GitViewModel.observeForever(gitListObserver)
        //Act
        viewModel.getGitHub()

        // Assert
        verify(gitListObserver).onChanged(list)

    }

    class MockRepository(private val result: GitDataSource) {
        override fun getMovies(usersResultCallback: (result: GitDataSource) -> Unit) {
            usersResultCallback(result)
        }
    }
}