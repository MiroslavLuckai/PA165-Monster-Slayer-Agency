import React from 'react'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchRequests} from 'ducks/actions/requests'
import {IRequest} from 'types/IRequest'

interface IStateProps {
    requests: IRequest[],
}

interface IDispatchProps {
    fetchRequests: any,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        requests: state.requests.requestsList,
    }
}

const mapDispatchToProps = {
    fetchRequests,
}

class RequestList extends React.Component<IProps> {

    componentDidMount() {
        this.props.fetchRequests()
    }

    render() {
        const {requests} = this.props

        return (
            <div className={'scope__RequestList'}>
                {requests.map((request, index) => {
                    return (
                        <>
                            <div>{request.id}</div>
                            <div>{request.location}</div>
                        </>
                    )
                })}
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestList)
