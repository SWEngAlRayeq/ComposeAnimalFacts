package app.animal_facts.di

import app.animal_facts.data.api.AnimalFactApi
import app.animal_facts.data.repo_impl.AnimalFactRepositoryImpl
import app.animal_facts.domain.repo.AnimalFactRepository
import app.animal_facts.domain.usecase.GetAnimalFactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun provideAnimalFactApi(client: OkHttpClient): AnimalFactApi {
        return Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimalFactApi::class.java)
    }

    @Provides
    fun provideAnimalFactRepository(api: AnimalFactApi): AnimalFactRepository {
        return AnimalFactRepositoryImpl(api)
    }

    @Provides
    fun provideGetAnimalFactUseCase(repository: AnimalFactRepository): GetAnimalFactUseCase {
        return GetAnimalFactUseCase(repository)
    }

}