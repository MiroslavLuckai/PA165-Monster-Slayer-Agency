import React from 'react'
import 'styles/BaseList.scss'

const BaseList: React.FC = (props) => {
    return (
        <div className={'scope__BaseList'}>
            {props.children}
        </div>
    )
}

export default BaseList
