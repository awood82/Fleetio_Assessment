package com.androidandrew.fleetio_assessment.di

import com.androidandrew.fleetio_assessment.data.DefaultVehicleRepository
import com.androidandrew.fleetio_assessment.data.IVehicleRepository
import com.androidandrew.fleetio_assessment.data.VehiclePagingSource
import com.androidandrew.fleetio_assessment.network.CachedVehiclesService
import com.androidandrew.fleetio_assessment.network.IFleetioVehiclesService
import com.androidandrew.fleetio_assessment.ui.details.DetailsViewModel
import com.androidandrew.fleetio_assessment.ui.main.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

@ExperimentalSerializationApi
val appModule = module {

    fun provideVehiclesService(): IFleetioVehiclesService {
        val json = Json { ignoreUnknownKeys = true }

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(IFleetioVehiclesService.BASE_URL)
//    .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(IFleetioVehiclesService::class.java)
    }



    single<IFleetioVehiclesService> { CachedVehiclesService }
//    single<IFleetioVehiclesService> { provideVehiclesService() }
    single<IVehicleRepository> { DefaultVehicleRepository(get()) }

    factory { VehiclePagingSource(get()) }

    viewModel { MainViewModel(get()) }
    viewModel { parameters -> DetailsViewModel(vehicleId = parameters.get(), get()) }
}