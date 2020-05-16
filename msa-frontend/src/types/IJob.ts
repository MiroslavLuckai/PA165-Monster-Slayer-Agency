import {IRequest} from 'types/IRequest'
import {IHero} from 'types/IHero'
import {EJobStatus} from 'enums/EJobStatus'
import {EJobSeverity} from 'enums/EJobSeverity'

export interface IJob {
    id: string,
    request: IRequest,
    heroes: IHero[],
    evaluation: number,
    status: EJobStatus,
    severity: EJobSeverity,
}
