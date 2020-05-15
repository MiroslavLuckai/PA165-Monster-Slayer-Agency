import {EResistance} from 'enums/EResistance'
import {EMonsterType} from 'enums/EMonsterType'
import {EMonsterFood} from 'enums/EMonsterFood'

export interface IMonster {
    id: string,
    name: string,
    size: number,
    resistances: EResistance[],
    type: EMonsterType,
    image: string,
    food: EMonsterFood,
}
