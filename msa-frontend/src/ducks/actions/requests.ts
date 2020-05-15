import {FETCH_REQUEST, FETCH_REQUESTS} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'

export const fetchRequests = () => async (dispatch: any) => {
    axios.get('http://localhost:8080/pa165/rest/requests')
        .then((response) => {
            dispatch({
                type: FETCH_REQUESTS,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}

export const fetchRequest = (id: string) => async (dispatch: any) => {
    axios.get(`http://localhost:8080/pa165/rest/requests/${id}`)
        .then((response) => {
            dispatch({
                type: FETCH_REQUEST,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}
