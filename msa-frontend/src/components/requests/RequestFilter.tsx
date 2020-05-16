import React from 'react'
import 'styles/RequestFilter.scss'
import BaseCard from 'components/common/BaseCard'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {ERequestFilter} from 'enums/ERequestFilter'
import {fetchRecommendedRequests, fetchRequests, setRequestFilter} from 'ducks/actions/requests'
import {IUser} from 'types/IUser'

interface IStateProps {
    filter: ERequestFilter,
    user?: IUser,
}

interface IDispatchProps {
    setRequestFilter: typeof setRequestFilter,
    fetchRequests: any,
    fetchRecommendedRequests: any,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        filter: state.requests.filter,
        user: state.auth.user,
    }
}

const mapDispatchToProps = {
    setRequestFilter,
    fetchRequests,
    fetchRecommendedRequests,
}

class RequestFilter extends React.Component<IProps> {

    render() {
        const {filter} = this.props
        const allRequestsButtonClass = `all ui-button ${filter !== ERequestFilter.ALL ? 'ui-button--reverted' : ''}`
        const recommendedButtonClass = `recommended ui-button ${filter !== ERequestFilter.RECOMMENDED ? 'ui-button--reverted' : ''}`

        return (
            <div className={'scope__RequestFilter'}>
                <div className={'card-wrapper'}>
                    <BaseCard>
                        <h2>Filters</h2>
                        <div className={'filters'}>
                            <button
                                className={allRequestsButtonClass}
                                onClick={this.onAllRequestsClick}
                            >
                                All requests
                            </button>
                            <button
                                className={recommendedButtonClass}
                                onClick={this.onRecommendedClick}
                            >
                                Recommended
                            </button>
                        </div>
                    </BaseCard>
                </div>
            </div>
        )
    }

    private onAllRequestsClick = () => {
        this.props.setRequestFilter(ERequestFilter.ALL)
        this.props.fetchRequests()
    }

    private onRecommendedClick = () => {
        this.props.setRequestFilter(ERequestFilter.RECOMMENDED)
        this.props.fetchRecommendedRequests(this.props.user?.id)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestFilter)
