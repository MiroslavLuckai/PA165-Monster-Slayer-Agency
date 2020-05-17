import {ESkill} from 'enums/ESkill'
import {IUser} from 'types/IUser'

export interface IHero {
    id?: string,
    name: string,
    user: IUser,
    image: string,
    skills: ESkill[],
}
