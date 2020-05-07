import {combineReducers} from 'redux'
import heroesReducer, {IHeroesState} from 'ducks/reducers/heroes'

export interface IStore {
    heroes: IHeroesState,
}

export default combineReducers({
    heroes: heroesReducer,
})
