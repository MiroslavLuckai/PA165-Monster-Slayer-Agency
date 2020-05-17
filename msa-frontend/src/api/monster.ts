import {IMonster} from 'types/IMonster'
import axios from 'axios'
import {IResponse} from 'types/IResponse'

export const createMonster = async (monster: IMonster): Promise<IResponse> => {
    return await axios.post('http://localhost:8080/pa165/rest/monsters/create', monster)
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

export const deleteMonster = async (monsterId: string): Promise<IResponse> => {
    return await axios.delete(`http://localhost:8080/pa165/rest/monsters/${monsterId}`)
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

export const findMonsterByName = async (name: string): Promise<IResponse> => {
    return await axios.get(`http://localhost:8080/pa165/rest/monsters/name/${name}`)
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
