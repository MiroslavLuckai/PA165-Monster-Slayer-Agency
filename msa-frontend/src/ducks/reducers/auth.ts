import {IUser} from 'types/IUser'
import {SIGN_IN, SIGN_OUT} from 'ducks/actions/types'
import produce from 'immer'

export interface IAuthState {
    user?: IUser,
    isSignedIn: boolean,
}

const initialState: IAuthState = {
    isSignedIn: false,
}

const signIn = (state: IAuthState) => {
    return produce(state, draft => {
        draft.isSignedIn = true
    })
}

const signOut = (state: IAuthState) => {
    return produce(state, draft => {
        draft.isSignedIn = false
        draft.user = undefined
    })
}

export default (state: IAuthState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case SIGN_IN:
            return signIn(state)
        case SIGN_OUT:
            return signOut(state)
        default:
            return state
    }
}
