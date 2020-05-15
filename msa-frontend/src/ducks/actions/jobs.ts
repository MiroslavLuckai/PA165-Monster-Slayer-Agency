import {FETCH_JOBS} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'

export const fetchJobs = () => async (dispatch: any) => {
    axios.get('http://localhost:8080/pa165/rest/jobs')
        .then((response) => {
            dispatch({
                type: FETCH_JOBS,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}
