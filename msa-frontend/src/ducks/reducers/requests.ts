import {IRequest} from 'types/IRequest'
import {FETCH_REQUEST, FETCH_REQUESTS} from 'ducks/actions/types'
import produce from 'immer'

export interface IRequestsState {
    requestsList: IRequest[],
    currentRequest?: IRequest,
}

const initialState: IRequestsState = {
    requestsList: [] as IRequest[],
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

export default (state: IRequestsState = initialState, action: any) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_REQUESTS:
            return fetchRequests(state, payload)
        case FETCH_REQUEST:
            return fetchRequest(state, payload)
        default:
            return state
    }
}
