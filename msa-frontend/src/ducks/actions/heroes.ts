import {FETCH_HEROES} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'

export const fetchHeroes = () => async (dispatch: any) => {
    axios.get('http://localhost:8080/pa165/rest/heroes')
        .then((response) => {
            dispatch({
                type: FETCH_HEROES,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}
