import React from 'react'
import 'styles/HeroCard.scss'
import {IHero} from 'types/IHero'
import BaseCard from 'components/common/BaseCard'
import ResourceImage from 'components/common/ResourceImage'
import {getDisplayText} from 'utils/common'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'

interface IProps {
    hero: IHero,
}

const HeroCard = (props: IProps) => {
    const {name, image, skills} = props.hero
    
    const renderCardTop = () => {
        return (
            <>
                <ResourceImage className={'profile-picture'} image={image} alt={'hero'}/>
                <span className={'name'}>{name}</span>
            </>
        )
    }

    const renderCardBody = () => {
        return (
            <div className={'skills'}>
                <h3>Skills</h3>
                {skills.map((skill, index) => {
                    return (
                        <div className={'skill'}>
                            <Icon className={'skill__icon'} icon={EIcon.CHECK} style={EIconStyle.SOLID} />
                            <div className={'skill__value'}>{getDisplayText(skill)}</div>
                        </div>
                    )
                })}
            </div>
        )
    }

    return (
        <div className={'scope__HeroCard'}>
            <BaseCard top={renderCardTop()}>
                {renderCardBody()}
            </BaseCard>
        </div>
    )
}

export default HeroCard
