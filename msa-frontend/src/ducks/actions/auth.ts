import {SET_IS_AUTHENTICATED, SIGN_IN, SIGN_OUT} from 'ducks/actions/types'
import {ICredentials} from 'types/ICredentials'
import {showErrorNotification} from 'ducks/actions/common'
import axios from 'axios'

export const signIn = (credentials: ICredentials) => async (dispatch: any) => {
    axios.post('http://localhost:8080/pa165/rest/users/authenticate', credentials)
        .then((response) => {
            const {success, user} = response.data
            if (success && user) {
                dispatch({
                    type: SIGN_IN,
                    payload: user,
                })
            } else {
                dispatch(setIsAuthenticated(false))
            }
        })
        .catch((error) => {
            console.error(error.message)
            dispatch(showErrorNotification())
        })

    return {
        type: SIGN_IN,
    }
}

export const signOut = () => {
    return {
        type: SIGN_OUT,
    }
}

export const setIsAuthenticated = (isAuthenticated: boolean) => {
    return {
        type: SET_IS_AUTHENTICATED,
        payload: isAuthenticated,
    }
}
