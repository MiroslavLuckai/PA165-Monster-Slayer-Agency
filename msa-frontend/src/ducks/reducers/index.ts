import {combineReducers} from 'redux'
import commonReducer from 'ducks/reducers/common'
import heroesReducer, {IHeroesState} from 'ducks/reducers/heroes'
import requestsReducer, {IRequestsState} from 'ducks/reducers/requests'
import monstersReducer, {IMonstersState} from 'ducks/reducers/monsters'
import jobsReducer, {IJobsState} from 'ducks/reducers/jobs'
import {ICommonState} from 'ducks/reducers/common'

export interface IStore {
    heroes: IHeroesState,
    monsters: IMonstersState,
    requests: IRequestsState,
    jobs: IJobsState,
    common: ICommonState,
}

export default combineReducers({
    heroes: heroesReducer,
    monsters: monstersReducer,
    requests: requestsReducer,
    jobs: jobsReducer,
    common: commonReducer,
})
