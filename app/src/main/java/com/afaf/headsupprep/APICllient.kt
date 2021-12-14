package com.afaf.headsupprep

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APICllient {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {

        retrofit = Retrofit.Builder()
            .baseUrl("https://dojo-recipes.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }
}