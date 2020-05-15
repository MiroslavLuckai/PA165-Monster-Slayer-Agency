import {EMonsterType} from 'enums/EMonsterType'
import {EMonsterFood} from 'enums/EMonsterFood'
import {EResistance} from 'enums/EResistance'

export const getMonsterTypeName = (type: EMonsterType) => {
    const lowercaseType = type.toLowerCase()
    return lowercaseType.charAt(0).toUpperCase() + lowercaseType.slice(1)
}

export const getMonsterFoodName = (food: EMonsterFood) => {
    const lowercaseFood = food.toLowerCase()
    return lowercaseFood.charAt(0).toUpperCase() + lowercaseFood.slice(1)
}

export const getMonsterResistanceName = (resistance: EResistance) => {
    const lowercaseResistance = resistance.toLowerCase()
    return lowercaseResistance.charAt(0).toUpperCase() + lowercaseResistance.slice(1)
}
