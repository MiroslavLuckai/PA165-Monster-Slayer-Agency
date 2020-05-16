import {IUser} from 'types/IUser'
import {SIGN_IN, SIGN_OUT} from 'ducks/actions/types'

export const signIn = () => {
    return {
        type: SIGN_IN,
    }
}

export const signOut = () => {
    return {
        type: SIGN_OUT,
    }
}
