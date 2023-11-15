package com.tasty.recipesapp.utils
import com.tasty.recipesapp.data.dto.*
import com.tasty.recipesapp.data.models.*

object Mapping {
    fun InstructionDTO.toModel(): InstructionModel {
        return InstructionModel(
            id = this.id,
            displayText = this.displayText,
            time = InstructionTime(this.startTime, this.endTime)
        )
    }

    fun List<InstructionDTO>.toInstructionModelList(): List<InstructionModel> {
        return this.map { it.toModel() }
    }

    fun TagDTO.toModel(): TagModel {
        return TagModel(
            id = this.id,
            displayName = this.displayName,
            type = this.type,
            rootTagType = this.rootTagType,
            name = this.name
        )
    }

    fun List<TagDTO>.toTagModelList(): List<TagModel> {
        return this.map { it.toModel() }
    }

    fun CreditDTO.toModel(): CreditModel {
        return CreditModel(
            name = this.name,
            type = this.type
        )
    }

    fun List<CreditDTO>.toCreditModelList(): List<CreditModel> {
        return this.map { it.toModel() }
    }

    fun IngredientDTO.toModel(): IngredientModel {
        return IngredientModel(
            id = this.id,
            displaySingular = this.displaySingular,
            updatedAt = this.updatedAt,
            name = this.name,
            createdAt = this.createdAt,
            displayPlural = this.displayPlural
        )
    }

    fun List<IngredientDTO>.toIngredientModelList(): List<IngredientModel> {
        return this.map { it.toModel() }
    }

    fun UserRatingsDTO.toModel(): UserRatingsModel {
        return UserRatingsModel(
            countPositive = this.countPositive,
            score = this.score,
            countNegative = this.countNegative
        )
    }

    fun List<UserRatingsDTO>.toUserRatingsModelList(): List<UserRatingsModel> {
        return this.map { it.toModel() }
    }

    fun UnitDTO.toModel(): UnitModel {
        return UnitModel(
            displaySingular = this.displaySingular,
            abbreviation = this.abbreviation,
            system = this.system,
            name = this.name,
            displayPlural = this.displayPlural
        )
    }

    fun List<UnitDTO>.toUnitModelList(): List<UnitModel> {
        return this.map { it.toModel() }
    }

    fun MeasurementDTO.toModel(): MeasurementModel {
        return MeasurementModel(
            unit = this.unit.toModel(),
            quantity = this.quantity,
            id = this.id
        )
    }

    fun List<MeasurementDTO>.toMeasurementModelList(): List<MeasurementModel> {
        return this.map { it.toModel() }
    }

    fun ComponentDTO.toModel(): ComponentModel {
        return ComponentModel(
            position = this.position,
            measurements = this.measurements.toMeasurementModelList(),
            rawText = this.rawText,
            extraComment = this.extraComment,
            ingredient = this.ingredient.toModel(),
            id = this.id
        )
    }

    fun List<ComponentDTO>.toComponentModelList(): List<ComponentModel> {
        return this.map { it.toModel() }
    }

    fun SectionDTO.toModel(): SectionModel {
        return SectionModel(
            components = this.components.toComponentModelList(),
            name = this.name,
            position = this.position
        )
    }

    fun List<SectionDTO>.toSectionModelList(): List<SectionModel> {
        return this.map { it.toModel() }
    }

    fun TopicDTO.toModel(): TopicModel {
        return TopicModel(
            name = this.name,
            slug = this.slug
        )
    }

    fun List<TopicDTO>.toTopicModelList(): List<TopicModel> {
        return this.map { it.toModel() }
    }

    fun NutritionDTO.toModel(): NutritionModel {
        return NutritionModel(
            carbohydrates = this.carbohydrates,
            fiber = this.fiber,
            updatedAt = this.updatedAt,
            protein = this.protein,
            fat = this.fat,
            calories = this.calories,
            sugar = this.sugar
        )
    }

    fun List<NutritionDTO>.toNutritionModelList(): List<NutritionModel> {
        return this.map { it.toModel() }
    }

    fun RecipeDTO.toModel(): RecipeModel {
        return RecipeModel(
            tags = this.tags.toTagModelList(),
            thumbnailUrl = this.thumbnailUrl,
            originalVideoUrl = this.originalVideoUrl,
            userRatings = this.userRatings.toModel(),
            language = this.language,
            id = this.id,
            sections = this.sections.toSectionModelList(),
            name = this.name,
            videoUrl = this.videoUrl,
            nutrition = this.nutrition.toModel(),
            topics = this.topics.toTopicModelList(),
            instructions = this.instructions.toInstructionModelList(),
            credits = this.credits.toCreditModelList(),
            country = this.country,
            description = this.description,
            keywords = this.keywords,
            numServings = this.numServings
        )
    }

    fun List<RecipeDTO>.toRecipeModelList(): List<RecipeModel> {
        return this.map { it.toModel() }
    }


}