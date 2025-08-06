package app.animal_facts.data.repo_impl

import app.animal_facts.data.api.AnimalFactApi
import app.animal_facts.data.model.AnimalFactDto
import app.animal_facts.domain.repo.AnimalFactRepository
import javax.inject.Inject

class AnimalFactRepositoryImpl @Inject constructor(
    private val api: AnimalFactApi
) : AnimalFactRepository {
    override suspend fun getAnimalFact(): AnimalFactDto {
        return api.getAnimalFact()
    }
}