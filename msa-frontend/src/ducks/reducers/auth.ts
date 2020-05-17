import {IUser} from 'types/IUser'
import {SET_IS_AUTHENTICATED, SET_USER, SIGN_IN, SIGN_OUT} from 'ducks/actions/types'
import produce from 'immer'

export interface IAuthState {
    user?: IUser,
    isSignedIn: boolean,
    isAuthenticated: boolean | null,
}

const initialState: IAuthState = {
    isSignedIn: false,
    isAuthenticated: null,
}

const signIn = (state: IAuthState, user: IUser) => {
    return produce(state, draft => {
        draft.isSignedIn = true
        draft.isAuthenticated = true
        draft.user = user
    })
}

const signOut = (state: IAuthState) => {
    return produce(state, draft => {
        draft.isSignedIn = false
        draft.user = undefined
    })
}

const setIsAuthenticated = (state: IAuthState, isAuthenticated: boolean) => {
    return produce(state, draft => {
        draft.isAuthenticated = isAuthenticated
    })
}

const setUser = (state: IAuthState, user: IUser) => {
    return produce(state, draft => {
        draft.user = user
    })
}

export default (state: IAuthState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case SIGN_IN:
            return signIn(state, payload)
        case SIGN_OUT:
            return signOut(state)
        case SET_IS_AUTHENTICATED:
            return setIsAuthenticated(state, payload)
        case SET_USER:
            return setUser(state, payload)
        default:
            return state
    }
}
