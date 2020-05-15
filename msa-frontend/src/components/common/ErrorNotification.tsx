import React from 'react'
import 'styles/ErrorNotification.scss'
import {connect} from 'react-redux'
import {hideErrorNotification} from 'ducks/actions/common'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'

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
                    <Icon icon={EIcon.WARNING} style={EIconStyle.SOLID} />
                </span>
                <span className={'description'}>Something went wrong...</span>
            </div>
            <span className={'close-icon-wrapper'} onClick={() => hideErrorNotification()}>
                <Icon icon={EIcon.CLOSE} style={EIconStyle.SOLID} />
            </span>
        </div>
    )
}

export default connect(null, mapDispatchToProps)(ErrorNotification)
