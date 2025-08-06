package app.animal_facts.domain.usecase

import app.animal_facts.data.model.AnimalFactDto
import app.animal_facts.domain.repo.AnimalFactRepository
import javax.inject.Inject

class GetAnimalFactUseCase @Inject constructor(
    private val repository: AnimalFactRepository
) {
    suspend operator fun invoke(): AnimalFactDto {
        return repository.getAnimalFact()
    }
}