import {IHero} from 'types/IHero'
import axios from 'axios'
import {IResponse} from 'types/IResponse'

export const createHero = async (hero: IHero): Promise<IResponse> => {
    return await axios.post('http://localhost:8080/pa165/rest/heroes/create', hero)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}

export const deleteHero = async (heroId: string): Promise<IResponse> => {
    return await axios.delete(`http://localhost:8080/pa165/rest/heroes/${heroId}`)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}

export const findHeroByUserId = async (userId: string): Promise<IResponse> => {
    return await axios.get(`http://localhost:8080/pa165/rest/heroes/user/${userId}`)
        .then((response) => {
            return {
                success: true,
                data: response.data,
            }
        })
        .catch((error) => {
            return {
                success: false,
                error: error.message,
            }
        })
}
