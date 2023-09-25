package mx.mariovaldez.yapecodechallenge.data.remote.api

import mx.mariovaldez.yapecodechallenge.domain.remote.ServiceFactory
import retrofit2.Retrofit
import javax.inject.Inject

internal class ApiServiceFactory @Inject constructor(
    private val retrofit: Retrofit
) : ServiceFactory {

    override fun <T> createApiService(serviceClass: Class<T>): T = retrofit.create(serviceClass)
}
