import {FETCH_HEROES} from 'ducks/actions/types'

export const fetchHeroes = () => {
    const FAKE_HEROES = [
        {
            name: 'Hero #1',
            skills: ['Magic', 'Sneak'],
            image: 'https://i.picsum.photos/id/1025/4951/3301.jpg'
        },
        {
            name: 'Hero #2',
            skills: ['Melee', 'Blades'],
            image: 'https://i.picsum.photos/id/1084/4579/3271.jpg',
        },
        {
            name: 'Hero #3',
            skills: ['Armor', 'Sneak', 'Acrobatics'],
            image: 'https://i.picsum.photos/id/275/4288/2848.jpg',
        },
    ]

    return {
        type: FETCH_HEROES,
        payload: FAKE_HEROES,
    }
}
