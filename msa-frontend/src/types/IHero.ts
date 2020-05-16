import {ESkill} from 'enums/ESkill'

export interface IHero {
    id: string,
    name: string,
    image: string,
    skills: ESkill[],
}
