import {findHeroByUserId} from 'api/hero'
import {IHero} from 'types/IHero'

export const getHeroByUserId = (userId: string): IHero | undefined => {
    let hero
    findHeroByUserId(userId).then((response) => {
        if (response.success) {
            hero = response.data
        } else {
            hero = undefined
        }
    })

    return hero;
}
