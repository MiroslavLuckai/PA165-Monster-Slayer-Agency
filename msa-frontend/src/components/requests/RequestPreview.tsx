import React from 'react'
import {RouteComponentProps} from 'react-router-dom'
import {IRequest} from 'types/IRequest'
import {setActiveLayer} from 'ducks/actions/common'
import {IStore} from 'ducks/reducers'
import {connect} from 'react-redux'
import RequestCard from 'components/requests/RequestCard'
import BaseList from 'components/common/BaseList'
import {ELayer} from 'enums/ELayer'
import {fetchRequest} from 'ducks/actions/requests'

interface IStateProps {
    request?: IRequest,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchRequest: any,
}

interface IProps extends IStateProps, IDispatchProps, RouteComponentProps<{id: string}> {}

const mapStateToProps = (state: IStore) => {
    return {
        request: state.requests.currentRequest,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    fetchRequest,
}

class RequestPreview extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.REQUEST)
        this.props.fetchRequest(this.props.match.params.id)
    }

    render() {
        const {request} = this.props

        if (!request) {
            return null
        }

        return (
            <div className={'scope__RequestPreview'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <RequestCard request={request} />
                    </div>
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestPreview)
