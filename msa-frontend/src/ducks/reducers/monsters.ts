import {FETCH_MONSTER, FETCH_MONSTERS, SET_MONSTER} from 'ducks/actions/types'
import produce from 'immer'
import {IMonster} from 'types/IMonster'

export interface IMonstersState {
    monstersList: IMonster[],
    currentMonster?: IMonster,
}

const initialState: IMonstersState = {
    monstersList: [] as IMonster[],
}

const fetchMonsters = (state: IMonstersState, monsters: IMonster[]) => {
    return produce(state, draft => {
        draft.monstersList = monsters
    })
}

const fetchMonster = (state: IMonstersState, monster: IMonster) => {
    return produce(state, draft => {
        draft.currentMonster = monster
    })
}

export default (state: IMonstersState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_MONSTERS:
            return fetchMonsters(state, payload)
        case FETCH_MONSTER:
            return fetchMonster(state, payload)
        case SET_MONSTER:
            return fetchMonster(state, payload)
        default:
            return state
    }
}
