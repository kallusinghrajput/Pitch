package com.pitch.data.repository

import com.pitch.api.apiinterface.APIS
import com.pitch.api.apistate.Result
import com.pitch.data.model.Test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(private val apis: APIS) {


    suspend fun fetchPost(): Flow<Result<List<Test>?>> = flow {
        emit(Result.Loading())
        val response = apis.getPost()
        if (response.isSuccessful && response.body() != null) {
            emit(Result.Success(response.body()))
        } else {
            emit(Result.Error(errorMessage = response.message(), response.body()))
        }
    }.flowOn(Dispatchers.IO)

}