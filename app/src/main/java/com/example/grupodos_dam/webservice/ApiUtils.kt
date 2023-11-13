package com.example.grupodos_dam.webservice
class ApiUtils {
    companion object{
        fun getApiService():ApiService{
            return RetrofitClient.getRetrofit().create(ApiService::class.java)
        }
    }
}