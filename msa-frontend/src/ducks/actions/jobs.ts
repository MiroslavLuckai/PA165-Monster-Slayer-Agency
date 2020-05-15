import {FETCH_JOBS} from 'ducks/actions/types'

export const fetchJobs = () => {
    return {
        type: FETCH_JOBS,
    }
}
