import React from 'react'
import {fetchJobs} from 'ducks/actions/jobs'
import BaseList from 'components/common/BaseList'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {IJob} from 'types/IJob'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import JobCard from 'components/jobs/JobCard'
import SignInPage from 'components/SignInPage'
import JobFilter from 'components/jobs/JobFilter'

interface IStateProps {
    jobs: IJob[],
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchJobs: any,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        jobs: state.jobs.jobsList,
        isSignedIn: state.auth.isSignedIn,
    }

}

const mapDispatchToProps = {
    setActiveLayer,
    fetchJobs,
}

class JobList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.JOB)
        if (this.props.isSignedIn) {
            this.props.fetchJobs()
        }
    }

    componentDidUpdate(prevProps: IProps) {
        if (!prevProps.isSignedIn && this.props.isSignedIn) {
            this.props.fetchJobs()
        }
    }

    render() {
        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__JobList'}>
                <BaseList>
                    <JobFilter />
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
