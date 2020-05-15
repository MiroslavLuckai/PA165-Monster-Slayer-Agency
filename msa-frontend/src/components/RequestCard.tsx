import React from 'react'
import 'styles/RequestCard.scss'
import 'styles/ui.scss'
import {IRequest} from 'types/IRequest'

interface IProps {
    request: IRequest,
}

const RequestCard = (props: IProps) => {

    const {customer, location, monsters, award} = props.request
    const {image, userName, email} = customer

    const isClaimed = true

    return (
        <div className={'scope__RequestCard'}>
            <div className={'top'}>
                <img className={'profile-picture'} src={require(`../../../msa-sample-data/src/main/resources/${image}`)} alt={'user'} />
                <div className={'user-credentials-wrapper'}>
                    <div className={'username'}>{userName}</div>
                    <div className={'email'}>{email}</div>
                </div>
                <div className={'claim-button-wrapper'}>
                    <button className={'claim ui-button ui-button--green'}>
                        {isClaimed
                            ? <>
                                <i className={'claim__icon fas fa-check'} /> Claimed
                            </>
                            : 'Claim'
                        }
                    </button>
                </div>
            </div>
            <div className={'body'}>
                <div className={'wrapper'}>
                    <div className={'location'}>
                        <i className={'location__icon fas fa-map-marker-alt'} />
                        <span className={'location__name'}>{location}</span>
                    </div>
                    <div className={'award'}>
                        <i className={'award__icon fas fa-dollar-sign'} />
                        <span className={'award__value'}>{award}</span>
                    </div>
                </div>
                <div className={'monsters'}>
                    <h3>Monsters</h3>
                    {monsters.map((monster, index) => {
                        const {image, name} = monster

                        return (
                            <div className={'monster'}>
                                <img className={'monster__image'} src={require(`../../../msa-sample-data/src/main/resources/${image}`)} alt={'monster'} />
                                <div className={'monster__name'}>{name}</div>
                            </div>
                        )
                    })}
                </div>
            </div>
        </div>
    )
}

export default RequestCard
