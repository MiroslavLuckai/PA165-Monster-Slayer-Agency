import {FETCH_REQUESTS} from 'ducks/actions/types'
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
