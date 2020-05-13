import React from 'react'
import 'styles/HeroCard.scss'
import {IHero} from 'types/IHero'

interface IProps {
    hero: IHero,
}

const HeroCard = (props: IProps) => {
    const {name, image, skills} = props.hero

    return (
        <div className={'scope__HeroCard'}>
            <div className={'top'}>
                <img className={'profile-picture'} src={image} />
                <span className={'name'}>{name}</span>
            </div>
        </div>
    )
}

export default HeroCard
