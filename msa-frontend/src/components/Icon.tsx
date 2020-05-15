import React from 'react'
import {EIcon, EIconStyle} from 'enums/EIcon'

interface IProps {
    className?: string,
    icon: EIcon,
    style: EIconStyle,
}

const Icon: React.FC<IProps> = (props) => {
    const {className} = props
    return (
        <i className={`${className ?? ''} ${props.style} fa-${props.icon}`} />
    )
}

export default Icon
