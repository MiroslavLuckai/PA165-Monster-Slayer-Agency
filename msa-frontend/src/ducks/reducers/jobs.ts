import {FETCH_JOBS, FETCH_JOBS_BY_HERO, FETCH_JOBS_BY_SEVERITY, FETCH_JOBS_BY_STATUS, SET_JOB_FILTER} from 'ducks/actions/types'
import produce from 'immer'
import {IJob} from 'types/IJob'
import {EJobFilter} from 'enums/EJobFilter'

export interface IJobsState {
    jobsList: IJob[],
    filter: EJobFilter,
}

const initialState: IJobsState = {
    jobsList: [] as IJob[],
    filter: EJobFilter.ALL,
}

const fetchJobs = (state: IJobsState, jobs: IJob[]) => {
    return produce(state, draft => {
        draft.jobsList = jobs
    })
}

const setJobFilter = (state: IJobsState, filter: EJobFilter) => {
    return produce(state, draft => {
        draft.filter = filter
    })
}

export default (state: IJobsState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_JOBS:
            return fetchJobs(state, payload)
        case SET_JOB_FILTER:
            return setJobFilter(state, payload)
        case FETCH_JOBS_BY_STATUS:
            return fetchJobs(state, payload)
        case FETCH_JOBS_BY_SEVERITY:
            return fetchJobs(state, payload)
        case FETCH_JOBS_BY_HERO:
            return fetchJobs(state, payload)
        default:
            return state
    }
}
