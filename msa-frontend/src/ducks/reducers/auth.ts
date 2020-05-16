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

const signIn = (state: IAuthState, user: IUser) => {
    return produce(state, draft => {
        draft.isSignedIn = true
        draft.user = user
    })
}

const signOut = (state: IAuthState) => {
    return produce(state, draft => {
        draft.isSignedIn = false
        draft.user = undefined
    })
}

const FAKE_USER: IUser = {
    id: '2',
    email: 'yen@gmail.com',
    password: '1000:e664b0da10e30b7c089e698f3b90c2feba699434d316ee12:056bd1b35b488b55ada4f25cb811103bb552b9c1d17c19fd',
    userName: 'Yennefer of Vengerberg',
    image: 'yennefer.jpg',
}

export default (state: IAuthState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case SIGN_IN:
            return signIn(state, FAKE_USER)
        case SIGN_OUT:
            return signOut(state)
        default:
            return state
    }
}
