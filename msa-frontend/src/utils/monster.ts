import {findMonsterByName} from 'api/monster'
import {IMonster} from 'types/IMonster'

export const getMonstersFromMonsterNames = (monsterNames: string[]) => {
    let monsters: IMonster[] = []

    monsterNames.forEach(name => {
        findMonsterByName(name).then((response) => {
            monsters.push(response.data)
        })
    })

    return monsters
}
