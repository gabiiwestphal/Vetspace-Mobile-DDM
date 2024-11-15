package com.example.ddm_vetspace.retrofit

import com.example.ddm_vetspace.services.blogService
import com.example.ddm_vetspace.services.petService
import com.example.ddm_vetspace.services.usuarioService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInitializer {
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usuarioApi: usuarioService = retrofit.create(usuarioService::class.java)
    val blogApi: blogService =  retrofit.create(blogService::class.java)
    val petApi: petService =  retrofit.create(petService::class.java)
}