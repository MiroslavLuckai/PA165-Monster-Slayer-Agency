import {FETCH_JOBS, SET_JOB_FILTER} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'
import {EJobFilter} from 'enums/EJobFilter'

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

export const setJobFilter = (filter: EJobFilter) => {
    return {
        type: SET_JOB_FILTER,
        payload: filter,
    }
}
