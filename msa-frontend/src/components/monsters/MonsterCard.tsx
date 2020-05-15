import React from 'react'
import 'styles/MonsterCard.scss'
import {IMonster} from 'types/IMonster'
import BasicInfoWrapper from 'components/common/BasicInfoWrapper'
import ResourceImage from 'components/common/ResourceImage'
import BaseCard from 'components/common/BaseCard'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'
import {getDisplayText} from 'utils/common'

interface IProps {
    monster: IMonster,
}

const MonsterCard: React.FC<IProps> = (props) => {

    const {name, size, resistances, monsterType, image, food} = props.monster

    const renderCardTop = () => {
        return (
            <>
                <ResourceImage className={'profile-picture'} image={image} alt={'monster'}/>
                <span className={'name'}>{name}</span>
            </>
        )
    }

    const renderBasicInfo = () => {
        return (
            <BasicInfoWrapper>
                <div className={'size'}>
                    <Icon className={'size__icon'} icon={EIcon.SIZE} style={EIconStyle.SOLID} />
                    <span className={'size__value'}>{size}</span>
                </div>
                <div className={'type'}>
                    <Icon className={'type__icon'} icon={EIcon.MONSTER} style={EIconStyle.SOLID} />
                    <span className={'type__value'}>{getDisplayText(monsterType)}</span>
                </div>
                <div className={'food'}>
                    <Icon className={'food__icon'} icon={EIcon.FOOD} style={EIconStyle.SOLID} />
                    <span className={'food__value'}>{getDisplayText(food)}</span>
                </div>
            </BasicInfoWrapper>
        )
    }

    const renderResistances = () => {
        return (
            <div className={'resistances'}>
                <h3>{resistances.length ? 'Resistances' : 'This monster has no resistances.'}</h3>
                {resistances.map((resistance, index) => {
                    return (
                        <div className={'resistance'} key={index}>
                            <Icon className={'resistance__icon'} icon={EIcon[resistance]} style={EIconStyle.SOLID} />
                            <div className={'resistance__value'}>{getDisplayText(resistance)}</div>
                        </div>
                    )
                })}
            </div>
        )
    }

    const renderCardBody = () => {
        return (
            <>
                {renderBasicInfo()}
                {renderResistances()}
            </>
        )
    }

    return (
        <div className={'scope__MonsterCard'}>
            <BaseCard top={renderCardTop()}>
                {renderCardBody()}
            </BaseCard>
        </div>
    )
}

export default MonsterCard
