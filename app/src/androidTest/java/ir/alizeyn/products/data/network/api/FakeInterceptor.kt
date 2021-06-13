package ir.alizeyn.products.data.network.api

import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

fun fakeInterceptor() = object : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url.toString()
        when {
            url.contains("73402840-975a-4f7c-9964-f865d4356d0c/products_simple.json") -> {
                return Response.Builder()
                    .code(200)
                    .message("")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(
                        PRODUCTS_RESPONSE.toResponseBody("application/json"
                        .toMediaTypeOrNull()))
                    .build()
            }
            else -> {
                return Response.Builder()
                    .code(404)
                    .message("")
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body("".toResponseBody("application/json".toMediaTypeOrNull()))
                    .build()
            }
        }
    }
}