package com.u3f.ethereumsign.di

import com.u3f.ethereumsign.data.repository.generate.GenerateRepositoryImpl
import com.u3f.ethereumsign.data.repository.sign.SignRepositoryImpl
import com.u3f.ethereumsign.domain.repository.generate.GenerateRepository
import com.u3f.ethereumsign.domain.repository.sign.SignRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import com.u3f.ethereumsign.ui.navigation.NavManager
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideNavManager(): NavManager {
        return NavManager()
    }

    @Provides
    fun providerGenerateAddressRepository(): GenerateRepository {
        return GenerateRepositoryImpl()
    }
    @Provides
    fun providerSignRepository(): SignRepository {
        return SignRepositoryImpl()
    }

}