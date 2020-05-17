import React from 'react'
import 'styles/JobFilter.scss'
import BaseCard from 'components/common/BaseCard'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {IUser} from 'types/IUser'
import {EJobFilter} from 'enums/EJobFilter'
import {fetchJobs, fetchJobsByStatus, setJobFilter} from 'ducks/actions/jobs'
import DropdownButton from 'components/common/DropdownButton'
import {EJobStatus} from 'enums/EJobStatus'
import {IDropdownItem} from 'types/IDropdownItem'

interface IStateProps {
    filter: EJobFilter,
    user?: IUser,
}

interface IDispatchProps {
    setJobFilter: typeof setJobFilter,
    fetchJobs: any,
    fetchJobsByStatus: any,
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
    fetchJobsByStatus,
}

class JobFilter extends React.Component<IProps> {

    render() {
        const {filter} = this.props
        const allJobsButtonClass = `all ui-button ${filter !== EJobFilter.ALL ? 'ui-button--reverted' : ''}`
        const ownJobsButtonClass = `own ui-button ${filter !== EJobFilter.OWN ? 'ui-button--reverted' : ''}`

        const statusDropdownList: IDropdownItem[] = Object.values(EJobStatus).map(value => {
                return {
                    value,
                    onClick: this.onStatusSelect,
                }
            }
        )

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
                            <DropdownButton
                                displayText={'Status'}
                                dropdownList={statusDropdownList}
                                isSelected={filter === EJobFilter.STATUS}
                            />
                        </div>
                    </BaseCard>
                </div>
            </div>
        )
    }

    private onAllJobsClick = () => {
        this.props.setJobFilter(EJobFilter.ALL)
        this.props.fetchJobs()
    }

    private onOwnJobsClick = () => {
        this.props.setJobFilter(EJobFilter.OWN)
    }

    private onStatusSelect = (status: EJobStatus) => {
        this.props.setJobFilter(EJobFilter.STATUS)
        this.props.fetchJobsByStatus(status)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(JobFilter)
