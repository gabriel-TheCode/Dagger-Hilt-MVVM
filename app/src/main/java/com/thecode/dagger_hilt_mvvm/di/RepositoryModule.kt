package com.thecode.dagger_hilt_mvvm.di

import com.thecode.dagger_hilt_mvvm.repository.MainRepository
import com.thecode.dagger_hilt_mvvm.retrofit.BlogApi
import com.thecode.dagger_hilt_mvvm.retrofit.NetworkMapper
import com.thecode.dagger_hilt_mvvm.room.BlogDao
import com.thecode.dagger_hilt_mvvm.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        blogApi: BlogApi,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository {
        return MainRepository(blogDao, blogApi, cacheMapper, networkMapper)
    }
}