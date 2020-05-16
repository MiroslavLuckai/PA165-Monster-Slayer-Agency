import {FETCH_JOBS} from 'ducks/actions/types'
import produce from 'immer'
import {IJob} from 'types/IJob'

export interface IJobsState {
    jobsList: IJob[],
}

const initialState: IJobsState = {
    jobsList: [] as IJob[],
}

const fetchJobs = (state: IJobsState, jobs: IJob[]) => {
    return produce(state, draft => {
        draft.jobsList = jobs
    })
}

export default (state: IJobsState = initialState, action: any) => {
    switch (action.type) {
        case FETCH_JOBS:
            return fetchJobs(state, action.payload)
        default:
            return state
    }
}
