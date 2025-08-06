package app.animal_facts.data.api

import app.animal_facts.data.model.AnimalFactDto
import retrofit2.http.GET

interface AnimalFactApi {

    @GET("fact")
    suspend fun getAnimalFact(): AnimalFactDto

}