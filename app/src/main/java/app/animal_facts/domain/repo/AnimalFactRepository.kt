package app.animal_facts.domain.repo

import app.animal_facts.data.model.AnimalFactDto

interface AnimalFactRepository {
    suspend fun getAnimalFact(): AnimalFactDto
}