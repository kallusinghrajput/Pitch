package com.pitch.api.apiinterface

import com.pitch.data.model.Test
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface APIS {

    @GET("posts")
    suspend fun getPost(): Response<List<Test>>


}