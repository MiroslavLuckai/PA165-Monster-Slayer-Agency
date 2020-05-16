import {IRequest} from 'types/IRequest'
import {FETCH_RECOMMENDED_REQUESTS, FETCH_REQUEST, FETCH_REQUESTS, SET_REQUEST_FILTER} from 'ducks/actions/types'
import produce from 'immer'
import {ERequestFilter} from 'enums/ERequestFilter'

export interface IRequestsState {
    requestsList: IRequest[],
    currentRequest?: IRequest,
    filter: ERequestFilter,
}

const initialState: IRequestsState = {
    requestsList: [] as IRequest[],
    filter: ERequestFilter.ALL,
}

const fetchRequests = (state: IRequestsState, requests: IRequest[]) => {
    return produce(state, draft => {
        draft.requestsList = requests
    })
}

const fetchRequest = (state: IRequestsState, request: IRequest) => {
    return produce(state, draft => {
        draft.currentRequest = request
    })
}

const setRequestFilter = (state: IRequestsState, filter: ERequestFilter) => {
    return produce(state, draft => {
        draft.filter = filter
    })
}

export default (state: IRequestsState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_REQUESTS:
            return fetchRequests(state, payload)
        case FETCH_REQUEST:
            return fetchRequest(state, payload)
        case FETCH_RECOMMENDED_REQUESTS:
            return fetchRequests(state, payload)
        case SET_REQUEST_FILTER:
            return setRequestFilter(state, payload)
        default:
            return state
    }
}
