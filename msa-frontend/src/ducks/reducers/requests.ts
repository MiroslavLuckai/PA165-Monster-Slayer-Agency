import {IRequest} from 'types/IRequest'
import {FETCH_REQUESTS} from 'ducks/actions/types'
import produce from 'immer'

export interface IRequestsState {
    requestsList: IRequest[],
}

const initialState: IRequestsState = {
    requestsList: [] as IRequest[],
}

const fetchRequests = (state: IRequestsState, requests: IRequest[]) => {
    return produce(state, draft => {
        draft.requestsList = requests
    })
}

export default (state: IRequestsState = initialState, action: any) => {
    switch (action.type) {
        case FETCH_REQUESTS:
            return fetchRequests(state, action.payload)
        default:
            return state
    }
}
