package com.dmitri.yandex_tasks.network.retrofit

import com.dmitri.yandex_tasks.network.entity.TodoItemApiRequest
import com.dmitri.yandex_tasks.network.entity.TodoItemApiResponse
import com.dmitri.yandex_tasks.network.entity.TodoItemApiResponseList
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TodoApi {

    @GET("list")
    suspend fun getList(): Response<TodoItemApiResponseList>

    @PATCH("list")
    suspend fun updateTodoItemsList(@Body body: TodoItemApiRequest): Response<TodoItemApiResponse>

    @POST
    suspend fun insertTodoItem(@Body body: TodoItemApiRequest): Response<TodoItemApiResponse>

    @PUT("list/{id}")
    suspend fun updateTodoItem(
        @Path("id") id: String,
        @Body body: TodoItemApiRequest
    ): Response<TodoItemApiResponse>

    @DELETE("list/{id}")
    suspend fun deleteTodoItem( @Path("id") id: String): Response<TodoItemApiResponse>
}