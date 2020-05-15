import React from 'react'
import 'styles/ErrorNotification.scss'
import {connect} from 'react-redux'
import {hideErrorNotification} from 'ducks/actions/common'

interface IDispatchProps {
    hideErrorNotification: typeof hideErrorNotification,
}

interface IProps extends IDispatchProps {}

const mapDispatchToProps = {
    hideErrorNotification,
}

const ErrorNotification: React.FC<IProps> = (props) => {

    const {hideErrorNotification} = props

    return (
        <div className={'scope__ErrorNotification'}>
            <div className={'error-wrapper'}>
                <span className={'error-icon-wrapper'}>
                    <i className={'fas fa-exclamation-circle'} />
                </span>
                <span className={'description'}>Something went wrong...</span>
            </div>
            <span className={'close-icon-wrapper'} onClick={() => hideErrorNotification()}>
                <i className={'fas fa-times'} />
            </span>
        </div>
    )
}

export default connect(null, mapDispatchToProps)(ErrorNotification)
