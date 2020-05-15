import {combineReducers} from 'redux'
import heroesReducer, {IHeroesState} from 'ducks/reducers/heroes'
import commonReducer from 'ducks/reducers/common'
import requestsReducer, {IRequestsState} from 'ducks/reducers/requests'
import monstersReducer, {IMonstersState} from 'ducks/reducers/monsters'
import {ICommonState} from 'ducks/reducers/common'

export interface IStore {
    heroes: IHeroesState,
    monsters: IMonstersState,
    requests: IRequestsState,
    common: ICommonState,
}

export default combineReducers({
    heroes: heroesReducer,
    monsters: monstersReducer,
    requests: requestsReducer,
    common: commonReducer,
})
