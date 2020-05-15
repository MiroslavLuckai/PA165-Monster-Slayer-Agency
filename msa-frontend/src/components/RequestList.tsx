import React from 'react'
import 'styles/RequestList.scss'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchRequests} from 'ducks/actions/requests'
import {IRequest} from 'types/IRequest'
import RequestCard from 'components/RequestCard'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'

interface IStateProps {
    requests: IRequest[],
}

interface IDispatchProps {
    fetchRequests: any,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        requests: state.requests.requestsList,
    }
}

const mapDispatchToProps = {
    fetchRequests,
    setActiveLayer,
}

class RequestList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.REQUEST)
        this.props.fetchRequests()
    }

    render() {
        const {requests} = this.props

        return (
            <div className={'scope__RequestList'}>
                {requests.map((request, index) => {
                    return (
                        <div className={'card-wrapper'}>
                            <RequestCard request={request} key={index} />
                        </div>
                    )
                })}
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestList)
