import React from 'react'
import 'styles/MonsterDelete.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {setUser} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../../history'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import SignInPage from 'components/SignInPage'
import BaseCard from 'components/common/BaseCard'
import BaseList from 'components/common/BaseList'
import {IRequest} from 'types/IRequest'
import {fetchRequests} from 'ducks/actions/requests'
import {deleteRequest} from 'api/request'

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
    request?: IRequest,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    setUser: typeof setUser,
    fetchRequests: any,
}

interface IOwnProps {}

interface IProps extends IStateProps, IDispatchProps, IOwnProps {}

const mapStateToProps = (state: IStore) => {
    return {
        user: state.auth.user,
        isSignedIn: state.auth.isSignedIn,
        request: state.requests.currentRequest,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    setUser,
    fetchRequests,
}

class RequestDelete extends React.Component<IProps> {

    componentDidMount() {
        setActiveLayer(ELayer.REQUEST)
    }

    render() {
        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__MonsterDelete'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <BaseCard top={<h3>Are you sure you want to delete this request?</h3>}>
                            <div className={'button-wrapper'}>
                                <button
                                    className={'no ui-button ui-button--reverted'}
                                    onClick={this.onNoButtonClick}
                                >
                                    No
                                </button>
                                <button
                                    className={'yes ui-button'}
                                    onClick={this.onYesButtonClick}
                                >
                                    Yes
                                </button>
                            </div>
                        </BaseCard>
                    </div>
                </BaseList>
            </div>
        )
    }

    private onNoButtonClick = () => {
        history.push(EPath.REQUESTS)
    }

    private onYesButtonClick = () => {
        deleteRequest(this.props.request!.id!).then(() => {
            this.props.fetchRequests()
            history.push(EPath.REQUESTS)
        })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestDelete)
