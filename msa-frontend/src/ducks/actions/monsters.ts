import axios from 'axios'
import {FETCH_MONSTER, FETCH_MONSTERS} from 'ducks/actions/types'
import {showErrorNotification} from 'ducks/actions/common'

export const fetchMonsters = () => async (dispatch: any) => {
    axios.get('http://localhost:8080/pa165/rest/monsters')
        .then((response) => {
            dispatch({
                type: FETCH_MONSTERS,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}

export const fetchMonster = (id: string) => async (dispatch: any) => {
    axios.get(`http://localhost:8080/pa165/rest/monsters/${id}`)
        .then((response) => {
            dispatch({
                type: FETCH_MONSTER,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}
