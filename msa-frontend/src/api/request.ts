import {IRequest} from 'types/IRequest'
import axios from 'axios'
import {IResponse} from 'types/IResponse'

export const createRequest = async (request: IRequest) => {
    axios.post('http://localhost:8080/pa165/rest/requests/create', request)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {error: error.message,}
        })
}

export const deleteRequest = async (requestId: string): Promise<IResponse> => {
    return await axios.delete(`http://localhost:8080/pa165/rest/requests/${requestId}`)
        .then((response) => {
            return {
                success: true,
            }
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}
