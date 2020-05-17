import {IRequest} from 'types/IRequest'
import axios from 'axios'

export const createRequest = async (request: IRequest) => {
    axios.post('http://localhost:8080/pa165/rest/requests/create', request)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {error: error.message,}
        })
}
