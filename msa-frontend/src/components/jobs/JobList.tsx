import React from 'react'
import {fetchJobs} from 'ducks/actions/jobs'
import BaseList from 'components/common/BaseList'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {IJob} from 'types/IJob'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import JobCard from 'components/jobs/JobCard'

interface IStateProps {
    jobs: IJob[],
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchJobs: any,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        jobs: state.jobs.jobsList,
    }

}

const mapDispatchToProps = {
    setActiveLayer,
    fetchJobs,
}

class JobList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.JOB)
        this.props.fetchJobs()
    }

    render() {
        return (
            <div className={'scope__JobList'}>
                <BaseList>
                    {this.props.jobs.map((job, index) => {
                        return (
                            <div className={'card-wrapper'}>
                                <JobCard job={job} key={index} />
                            </div>
                        )
                    })}
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(JobList)
