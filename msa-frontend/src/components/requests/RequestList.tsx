import React from 'react'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchRequests} from 'ducks/actions/requests'
import {IRequest} from 'types/IRequest'
import RequestCard from 'components/requests/RequestCard'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'
import SignInPage from 'components/SignInPage'
import RequestFilter from 'components/requests/RequestFilter'
import history from "../../history";
import {EPath} from "../../enums/EPath";

interface IStateProps {
    requests: IRequest[],
    isSignedIn: boolean,
}

interface IDispatchProps {
    fetchRequests: any,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        requests: state.requests.requestsList,
        isSignedIn: state.auth.isSignedIn,
    }
}

const mapDispatchToProps = {
    fetchRequests,
    setActiveLayer,
}

class RequestList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.REQUEST)
        if (this.props.isSignedIn) {
            this.props.fetchRequests()
        }
    }

    componentDidUpdate(prevProps: IProps) {
        if (!prevProps.isSignedIn && this.props.isSignedIn) {
            this.props.fetchRequests()
        }
    }

    render() {
        const {requests, isSignedIn} = this.props

        if (!isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__RequestList'}>
                <button
                    className={'create ui-button ui-button--yellow'}
                    onClick={() => history.push(EPath.CREATE_REQUEST)}
                >
                    Create a quest request
                </button>
                <BaseList>
                    <RequestFilter />
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
