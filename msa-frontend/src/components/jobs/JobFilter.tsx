import React from 'react'
import 'styles/JobFilter.scss'
import BaseCard from 'components/common/BaseCard'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {IUser} from 'types/IUser'
import {EJobFilter} from 'enums/EJobFilter'
import {fetchJobs, setJobFilter} from 'ducks/actions/jobs'

interface IStateProps {
    filter: EJobFilter,
    user?: IUser,
}

interface IDispatchProps {
    setJobFilter: typeof setJobFilter,
    fetchJobs: any,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        filter: state.jobs.filter,
        user: state.auth.user,
    }
}

const mapDispatchToProps = {
    setJobFilter,
    fetchJobs,
}

class JobFilter extends React.Component<IProps> {

    render() {
        const {filter} = this.props
        const allJobsButtonClass = `all ui-button ${filter !== EJobFilter.ALL ? 'ui-button--reverted' : ''}`
        const ownJobsButtonClass = `own ui-button ${filter !== EJobFilter.OWN ? 'ui-button--reverted' : ''}`

        return (
            <div className={'scope__JobFilter'}>
                <div className={'card-wrapper'}>
                    <BaseCard>
                        <h2>Filters</h2>
                        <div className={'filters'}>
                            <button
                                className={allJobsButtonClass}
                                onClick={this.onAllJobsClick}
                            >
                                All jobs
                            </button>
                            <button
                                className={ownJobsButtonClass}
                                onClick={this.onOwnJobsClick}
                            >
                                My jobs
                            </button>
                        </div>
                    </BaseCard>
                </div>
            </div>
        )
    }

    private onAllJobsClick = () => {
        this.props.setJobFilter(EJobFilter.ALL)
    }

    private onOwnJobsClick = () => {
        this.props.setJobFilter(EJobFilter.OWN)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(JobFilter)
