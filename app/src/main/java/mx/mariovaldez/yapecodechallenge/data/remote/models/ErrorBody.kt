package mx.mariovaldez.yapecodechallenge.data.remote.models

import com.google.gson.annotations.SerializedName

internal data class ErrorBody(
    @SerializedName("message")
    val message: String?,
    @SerializedName("code")
    val code: Int? = null
)
