import {combineReducers} from 'redux'
import heroesReducer, {IHeroesState} from 'ducks/reducers/heroes'
import commonReducer from 'ducks/reducers/common'
import requestsReducer, {IRequestsState} from 'ducks/reducers/requests'
import {ICommonState} from 'ducks/reducers/common'

export interface IStore {
    heroes: IHeroesState,
    requests: IRequestsState,
    common: ICommonState,
}

export default combineReducers({
    heroes: heroesReducer,
    requests: requestsReducer,
    common: commonReducer,
})
