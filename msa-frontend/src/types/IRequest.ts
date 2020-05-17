import {IUser} from 'types/IUser'
import {IMonster} from 'types/IMonster'
import {EJobSeverity} from 'enums/EJobSeverity'

export interface IRequest {
    id?: string,
    customer: IUser,
    location: string,
    monsters: IMonster[],
    award: number,
    severity: EJobSeverity,
}
