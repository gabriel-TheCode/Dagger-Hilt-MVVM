package com.thecode.dagger_hilt_mvvm.repository

import com.thecode.dagger_hilt_mvvm.database.BlogDao
import com.thecode.dagger_hilt_mvvm.database.CacheMapper
import com.thecode.dagger_hilt_mvvm.model.Blog
import com.thecode.dagger_hilt_mvvm.network.BlogApi
import com.thecode.dagger_hilt_mvvm.network.BlogMapper
import com.thecode.dagger_hilt_mvvm.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val blogDao: BlogDao,
    private val blogApi: BlogApi,
    private val cacheMapper: CacheMapper,
    private val blogMapper: BlogMapper
) {
    fun getBlog(): Flow<DataState<List<Blog>>> = flow {
        emit(DataState.Loading)
        try {
            val networkBlogs = blogApi.get()
            val blogs = blogMapper.mapFromEntityList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cachedBlogs = blogDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedBlogs)))
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }
}