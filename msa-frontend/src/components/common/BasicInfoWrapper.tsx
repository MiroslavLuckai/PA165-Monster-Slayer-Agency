import React from 'react'
import 'styles/BasicInfoWrapper.scss'

const BasicInfoWrapper: React.FC = (props) => {
    return (
        <div className={'scope__BasicInfoWrapper'}>
            {props.children}
        </div>
    )
}

export default BasicInfoWrapper
