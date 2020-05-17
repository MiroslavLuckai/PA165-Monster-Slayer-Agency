import {IHero} from 'types/IHero'
import axios from 'axios'

export const createHero = async (hero: IHero) => {
    axios.post('http://localhost:8080/pa165/rest/heroes/create', hero)
        .then((response) => {
            return {success: true}
        })
        .catch((error) => {
            return {error: error.message,}
        })
}
