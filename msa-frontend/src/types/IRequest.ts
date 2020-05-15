import {IUser} from 'types/IUser'
import {IMonster} from 'types/IMonster'

export interface IRequest {
    id: string,
    customer: IUser,
    location: string,
    monsters: IMonster[],
    award: number,
}
