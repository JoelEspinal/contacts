package com.joelespinal.contacts.remote.api

import com.joelespinal.contacts.remote.response.ContactsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ContactApi {

    @Headers("Content-Type: application/json")
    @GET("/api")
    suspend fun fetchUsers(
        @Query("page") page: Int,
        @Query("results") limit: Int
    ): Response<ContactsResponse>
}