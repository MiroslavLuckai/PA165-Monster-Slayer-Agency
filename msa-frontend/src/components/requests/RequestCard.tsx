import React from 'react'
import 'styles/RequestCard.scss'
import 'styles/ui.scss'
import {IRequest} from 'types/IRequest'
import BasicInfoWrapper from 'components/common/BasicInfoWrapper'
import BaseCard from 'components/common/BaseCard'
import ResourceImage from 'components/common/ResourceImage'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'
import {Link} from 'react-router-dom'
import {EPath} from 'enums/EPath'
import {IMonster} from 'types/IMonster'

interface IProps {
    request: IRequest,
}

const RequestCard = (props: IProps) => {

    const {customer, location, monsters, award} = props.request
    const {image, userName, email} = customer

    const isClaimed = true

    const renderUserCredentials = () => {
        return (
            <div className={'user-credentials-wrapper'}>
                <div className={'username'}>{userName}</div>
                <div className={'email'}>{email}</div>
            </div>
        )
    }

    const renderClaimButton = () => {
        return (
            <div className={'claim-button-wrapper'}>
                <button className={'claim ui-button bg-confirm'}>
                    {isClaimed
                        ? <>
                            <Icon className={'claim__icon'} icon={EIcon.CHECK} style={EIconStyle.SOLID} /> Claimed
                        </>
                        : 'Claim'
                    }
                </button>
            </div>
        )
    }

    const renderCardTop = () => {
        return (
            <>
                <ResourceImage className={'profile-picture'} image={image} alt={'user'} />
                {renderUserCredentials()}
                {renderClaimButton()}
            </>
        )
    }

    const renderBasicInfo = () => {
        return (
            <BasicInfoWrapper>
                <div className={'location'}>
                    <Icon className={'location__icon'} icon={EIcon.LOCATION} style={EIconStyle.SOLID} />
                    <span className={'location__name'}>{location}</span>
                </div>
                <div className={'award'}>
                    <Icon className={'award__icon'} icon={EIcon.MONEY} style={EIconStyle.SOLID} />
                    <span className={'award__value'}>{award}</span>
                </div>
            </BasicInfoWrapper>
        )
    }

    const Monster: React.FC<{monster: IMonster, index: number}> = (props) => {
        const [isHovered, setIsHovered] = React.useState(false)
        const {id, image, name} = props.monster

        return (
            <Link
                className={'monster'}
                to={`${EPath.MONSTERS}/${id}`}
                key={props.index}
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
            >
                <ResourceImage className={'monster__image'} image={image} alt={'monster'} />
                <div className={'monster__name'}>{name}</div>
                <Icon
                    className={`monster__icon ${isHovered ? 'monster__icon--visible' : ''}`}
                    icon={EIcon.SEARCH}
                    style={EIconStyle.SOLID}
                />
            </Link>
        )
    }

    const renderMonsters = () => {
        return (
            <div className={'monsters'}>
                <h3>Monsters</h3>
                {monsters.map((monster, index) => {
                    return <Monster monster={monster} index={index} />
                })}
            </div>
        )
    }

    const renderCardBody = () => {
        return (
            <>
                {renderBasicInfo()}
                {renderMonsters()}
            </>
        )
    }

    return (
        <div className={'scope__RequestCard'}>
            <BaseCard top={renderCardTop()}>
                {renderCardBody()}
            </BaseCard>
        </div>
    )
}

export default RequestCard
