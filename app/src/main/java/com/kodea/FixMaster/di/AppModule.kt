package com.kodea.FixMaster.di

import android.app.Application
import androidx.annotation.Keep
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.kodea.FixMaster.data.local.localUserManagerImp
import com.kodea.FixMaster.data.remote.GeocodeReverseApi
import com.kodea.FixMaster.data.remote.GeocodeReverseApi2
import com.kodea.FixMaster.data.repository.AuthRepositoryImpl
import com.kodea.FixMaster.data.repository.FireStoreRepositoryImpl
import com.kodea.FixMaster.data.repository.LocationRepositoryImpl
import com.kodea.FixMaster.domain.local.LocalUserManager
import com.kodea.FixMaster.domain.repository.AuthRepository
import com.kodea.FixMaster.domain.repository.FireStoreRepository
import com.kodea.FixMaster.domain.repository.LocationRepository
import com.kodea.FixMaster.domain.useCases.appEntryUseCases.AppEntryUseCases
import com.kodea.FixMaster.domain.useCases.appEntryUseCases.readAppEntry
import com.kodea.FixMaster.domain.useCases.appEntryUseCases.saveAppEntry
import com.kodea.FixMaster.domain.useCases.authentifaction.AuthUseCases
import com.kodea.FixMaster.domain.useCases.authentifaction.getUserAuthState
import com.kodea.FixMaster.domain.useCases.authentifaction.isUserAuth
import com.kodea.FixMaster.domain.useCases.authentifaction.signInWithEmailandPassword
import com.kodea.FixMaster.domain.useCases.authentifaction.signOut
import com.kodea.FixMaster.domain.useCases.authentifaction.signUp
import com.kodea.FixMaster.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
@Keep
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage {
        return FirebaseStorage.getInstance()
    }

    @Provides
    @Singleton
    fun provideAuthRepository(auth: FirebaseAuth, firestore: FirebaseFirestore): AuthRepository {
        return AuthRepositoryImpl(auth, firestore)
    }

    @Provides
    @Singleton
    fun provideAuthUseCases(repositoryImpl: AuthRepository) = AuthUseCases(
        signInWithEmailandPassword = signInWithEmailandPassword(repository = repositoryImpl),
        signOut = signOut(repositoryImpl),
        signUp = signUp(repositoryImpl),
        isUserAuth = isUserAuth(repositoryImpl),
        getUserAuthState = getUserAuthState(repositoryImpl)

    )

    @Provides
    @Singleton
    fun provideLocalUserManager(context: Application): LocalUserManager =
        localUserManagerImp(context)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager) = AppEntryUseCases(
        saveAppEntry = saveAppEntry(localUserManager),
        readAppEntry = readAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideApi(): GeocodeReverseApi2 {
        return (Retrofit.Builder().baseUrl(Constants.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodeReverseApi2::class.java))
    }

    @Provides
    @Singleton
    fun provideGetLocationUseCases(api : GeocodeReverseApi2) : LocationRepository{
        return LocationRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCategoriesRepository(firestore : FirebaseFirestore , storage : FirebaseStorage) : FireStoreRepository = FireStoreRepositoryImpl(fireStore =firestore , storage = storage )

}