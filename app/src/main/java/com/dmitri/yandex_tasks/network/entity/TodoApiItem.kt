package com.dmitri.yandex_tasks.network.entity

import com.dmitri.yandex_tasks.util.entity.Priority
import com.dmitri.yandex_tasks.util.entity.TodoItem
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.UUID

data class TodoApiItem(
    @SerializedName("id") val id: String,
    @SerializedName("text") val description: String,
    @SerializedName("importance") val priority: String,
    @SerializedName("deadline") val deadline: Long?,
    @SerializedName("done") val done: Boolean,
    @SerializedName("color") val color: String?,
    @SerializedName("created_at") val creationDate: Long,
    @SerializedName("changed_at") val modificationDate: Long,
    @SerializedName("last_updated_by") val lastUpdatedBy: String
)

data class TodoItemApiRequestList(
    @SerializedName("status") val status: String,
    @SerializedName("list") val list: List<TodoApiItem>
)

data class TodoItemApiResponseList(
    @SerializedName("status") val status: String,
    @SerializedName("list") val list: List<TodoApiItem>,
    @SerializedName("revision") val revision: Int
)

data class TodoItemApiRequest(
    @SerializedName("element") val element: TodoApiItem
)

data class TodoItemApiResponse(
    @SerializedName("status") val status: String,
    @SerializedName("revision") val revision: Int,
    @SerializedName("element") val element: TodoApiItem
)