import React from 'react'
import 'styles/JobFilter.scss'
import BaseCard from 'components/common/BaseCard'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {IUser} from 'types/IUser'
import {EJobFilter} from 'enums/EJobFilter'
import {fetchJobs, fetchJobsBySeverity, fetchJobsByHero, fetchJobsByStatus, setJobFilter} from 'ducks/actions/jobs'
import DropdownButton from 'components/common/DropdownButton'
import {EJobStatus} from 'enums/EJobStatus'
import {IDropdownItem} from 'types/IDropdownItem'
import {EJobSeverity} from "../../enums/EJobSeverity";
import {findHeroByUserId} from 'api/hero'

interface IStateProps {
    filter: EJobFilter,
    user?: IUser,
}

interface IDispatchProps {
    setJobFilter: typeof setJobFilter,
    fetchJobs: any,
    fetchJobsByHero: any,
    fetchJobsByStatus: any,
    fetchJobsBySeverity: any
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
    fetchJobsByHero,
    fetchJobsByStatus,
    fetchJobsBySeverity
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

        const severityDropdownList: IDropdownItem[] = Object.values(EJobSeverity).map(value => {
                return {
                    value,
                    onClick: this.onSeveritySelect,
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
                            <DropdownButton
                                displayText={'Severity'}
                                dropdownList={severityDropdownList}
                                isSelected={filter === EJobFilter.SEVERITY}
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

    private onOwnJobsClick = async () => {
        this.props.setJobFilter(EJobFilter.OWN)

        const response = await findHeroByUserId(this.props.user!.id)
        if (response.success) {
            const {id: heroId} = response.data
            this.props.fetchJobsByHero(heroId)
        }
    }

    private onStatusSelect = (status: EJobStatus) => {
        this.props.setJobFilter(EJobFilter.STATUS)
        this.props.fetchJobsByStatus(status)
    }

    private onSeveritySelect = (severity: EJobSeverity) => {
        this.props.setJobFilter(EJobFilter.SEVERITY)
        this.props.fetchJobsBySeverity(severity)
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(JobFilter)
