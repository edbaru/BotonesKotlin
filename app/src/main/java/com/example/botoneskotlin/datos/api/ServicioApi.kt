package com.example.botoneskotlin.datos.api

import com.example.botoneskotlin.datos.modelo.DatoCurioso
import retrofit2.http.GET
import retrofit2.http.Header

interface ServicioApi {
    @GET("facts")
    suspend fun getDatoCurioso(
        @Header("X-Api-Key") apiKey: String = "eNeb64tfKN7NGLfzfbr0og==v20U6ICuVlYIpjZY"
    ): List<DatoCurioso>
}
