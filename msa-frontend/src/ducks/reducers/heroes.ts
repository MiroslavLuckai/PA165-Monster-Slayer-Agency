import {FETCH_HERO, FETCH_HEROES} from 'ducks/actions/types'
import {IHero} from 'types/IHero'
import produce from 'immer'

export interface IHeroesState {
    heroesList: IHero[],
    currentHero?: IHero,
}

const initialState: IHeroesState = {
    heroesList: [] as IHero[],
}

const fetchHeroes = (state: IHeroesState, heroes: IHero[]) => {
    return produce(state, draft => {
        draft.heroesList = heroes
    })
}

const fetchHero = (state: IHeroesState, hero: IHero) => {
    return produce(state, draft => {
        draft.currentHero = hero
    })
}

export default (state: IHeroesState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_HEROES:
            return fetchHeroes(state, payload)
        case FETCH_HERO:
            return fetchHero(state, payload)
        default:
            return state
    }
}
