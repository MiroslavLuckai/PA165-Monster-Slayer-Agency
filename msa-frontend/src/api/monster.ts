import {IMonster} from 'types/IMonster'
import axios from 'axios'

export const createMonster = async (monster: IMonster) => {
    axios.post('http://localhost:8080/pa165/rest/monsters/create', monster)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {error: error.message,}
        })
}
