import React from 'react'
import 'styles/BaseCard.scss'

interface IProps {
    top?: React.ReactNode,
}

const BaseCard: React.FC<IProps> = (props) => {
    const {top} = props

    return (
        <div className={'scope__BaseCard'}>
            {top &&
                <div className={'top'}>
                    {props.top}
                </div>
            }
            <div className={'body'}>
                {props.children}
            </div>
        </div>
    )
}

export default BaseCard
