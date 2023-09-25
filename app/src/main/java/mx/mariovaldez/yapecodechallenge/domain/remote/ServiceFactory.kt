package mx.mariovaldez.yapecodechallenge.domain.remote

internal interface ServiceFactory {

    fun <T> createApiService(serviceClass: Class<T>): T
}
