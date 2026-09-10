package com.example.crud_android.di

import com.example.crud_android.data.repository.ProductRepositoryImpl
import com.example.crud_android.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        impl: ProductRepositoryImpl
    ): ProductRepository
}