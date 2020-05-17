import {FETCH_RECOMMENDED_REQUESTS, FETCH_REQUEST, FETCH_REQUESTS, SET_REQUEST_FILTER} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'
import {ERequestFilter} from 'enums/ERequestFilter'
import {IRequest} from 'types/IRequest'

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

export const fetchRequest = (id: string) => async (dispatch: any) => {
    axios.get(`http://localhost:8080/pa165/rest/requests/${id}`)
        .then((response) => {
            dispatch({
                type: FETCH_REQUEST,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}

export const fetchRecommendedRequests = (heroId: string) => async (dispatch: any) => {
    axios.get(`http://localhost:8080/pa165/rest/requests/match/${heroId}`)
        .then((response) => {
            dispatch({
                type: FETCH_RECOMMENDED_REQUESTS,
                payload: response.data,
            })
        })
        .catch((error) => {
            console.error(error)
            dispatch(showErrorNotification())
        })
}

export const setRequestFilter = (filter: ERequestFilter) => {
    return {
        type: SET_REQUEST_FILTER,
        payload: filter,
    }
}

export const setRequest = (request: IRequest) => {
    return {
        type: FETCH_REQUEST,
        payload: request,
    }
}
