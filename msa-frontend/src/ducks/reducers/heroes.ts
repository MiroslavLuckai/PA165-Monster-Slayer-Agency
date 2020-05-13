import {FETCH_HEROES} from 'ducks/actions/types'
import {IHero} from 'types/IHero'
import produce from 'immer'

export interface IHeroesState {
    heroesList: IHero[],
}

const initialState: IHeroesState = {
    heroesList: [] as IHero[],
}

const fetchHeroes = (state: IHeroesState, heroes: IHero[]) => {
    return produce(state, draft => {
        draft.heroesList = heroes
    })
}

export default (state: IHeroesState = initialState, action: any) => {
    switch (action.type) {
        case FETCH_HEROES:
            return fetchHeroes(state, action.payload)
        default:
            return state
    }
}
