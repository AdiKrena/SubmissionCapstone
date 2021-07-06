package com.adikrena.submissioncapstone.core.di

import androidx.room.Room
import com.adikrena.submissioncapstone.core.BuildConfig
import com.adikrena.submissioncapstone.core.data.RecipeRepository
import com.adikrena.submissioncapstone.core.data.source.local.LocalDataSource
import com.adikrena.submissioncapstone.core.data.source.local.room.RecipeDatabase
import com.adikrena.submissioncapstone.core.data.source.remote.RemoteDataSource
import com.adikrena.submissioncapstone.core.data.source.remote.network.ApiService
import com.adikrena.submissioncapstone.core.domain.repository.IRecipeRepository
import com.adikrena.submissioncapstone.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<RecipeDatabase>().recipeDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            RecipeDatabase::class.java, "Recipe.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.spoonacular.com"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/bwIewqhY96lORG26oq7vC+GJHbHcUS8D+hbnJhinaBY=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/TCBDFshx13ufChisiTL5QgqSQ5Uw4+nWKPD2Oi+iN5M=")
            .add(hostname, "sha256/FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        val logging = OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)

        if(BuildConfig.DEBUG) {
            logging.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        logging.build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IRecipeRepository> {
        RecipeRepository(
            get(),
            get(),
            get()
        )
    }
}