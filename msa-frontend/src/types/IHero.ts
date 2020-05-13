import {ESkill} from 'enums/ESkill'

export interface IHero {
    name: string,
    image: string,
    skills: ESkill[],
}
