import {FETCH_REQUESTS} from 'ducks/actions/types'
import axios from 'axios'
import {showErrorNotification} from 'ducks/actions/common'

const FAKE_REQUESTS = [
    {
        id: 'FAKE_ID',
        user: {
            id: 'FAKE_ID',
            email: 'fake@email.com',
            password: '12345',
            userName: 'fakeUsername',
            image: 'https://dummyimage.com/600x400/000/000'
        },
        location: 'FAKE_LOCATION',
        monsters: [
            {
                id: 'FAKE_ID',
                name: 'FAKE_NAME',
                image: 'https://dummyimage.com/600x400/000/000',
            },
        ],
        award: 100,
    }
]

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

// export const fetchRequests = () => {
//     return {
//         type: FETCH_REQUESTS,
//         payload: FAKE_REQUESTS,
//     }
// }
