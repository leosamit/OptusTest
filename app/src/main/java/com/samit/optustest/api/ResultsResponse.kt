package com.samit.optustest.api

data class ResultsResponse<T>(
//    @SerializedName(ApiKeys.TITLE)
//    val title: String? = null,
//    @SerializedName(ApiKeys.ROWS)
    val users: List<T>
)