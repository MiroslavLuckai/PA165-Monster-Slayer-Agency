import React from 'react'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchRequests} from 'ducks/actions/requests'
import {IRequest} from 'types/IRequest'
import RequestCard from 'components/requests/RequestCard'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'

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
                <BaseList>
                    {requests.map((request, index) => {
                        return (
                            <div className={'card-wrapper'}>
                                <RequestCard request={request} key={index} />
                            </div>
                        )
                    })}
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestList)
