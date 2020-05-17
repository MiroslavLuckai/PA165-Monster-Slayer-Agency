import {FETCH_JOBS, FETCH_JOBS_BY_STATUS, SET_JOB_FILTER} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'
import {EJobFilter} from 'enums/EJobFilter'
import {EJobStatus} from 'enums/EJobStatus'

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

export const fetchJobsByStatus = (status: EJobStatus) => async (dispatch: any) => {
    axios.get(`http://localhost:8080/pa165/rest/jobs/status/${status}`)
        .then((response) => {
            dispatch({
                type: FETCH_JOBS_BY_STATUS,
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
