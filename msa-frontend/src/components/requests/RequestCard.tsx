import React from 'react'
import 'styles/RequestCard.scss'
import 'styles/ui.scss'
import {IRequest} from 'types/IRequest'
import BasicInfoWrapper from 'components/common/BasicInfoWrapper'
import BaseCard from 'components/common/BaseCard'
import ResourceImage from 'components/common/ResourceImage'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'
import {Link} from 'react-router-dom'
import {EPath} from 'enums/EPath'
import {IMonster} from 'types/IMonster'
import {IJob} from 'types/IJob'
import {IStore} from 'ducks/reducers'
import {fetchJobs} from 'ducks/actions/jobs'
import {connect} from 'react-redux'
import {createJob, deleteJob} from 'api/job'
import {findHeroByUserId} from 'api/hero'
import {IUser} from 'types/IUser'
import {EJobStatus} from 'enums/EJobStatus'
import {EJobSeverity} from 'enums/EJobSeverity'
import {getHeroByUserId} from 'utils/hero'
import history from '../../history'
import {setRequest} from 'ducks/actions/requests'

interface IStateProps {
    jobs: IJob[],
    user?: IUser,
}

interface IDispatchProps {
    fetchJobs: any,
    setRequest: typeof setRequest,
}

interface IOwnProps {
    request: IRequest,
}

interface IProps extends IStateProps, IDispatchProps, IOwnProps {}

const mapStateToProps = (state: IStore) => {
    return {
        jobs: state.jobs.jobsList,
        user: state.auth.user,
    }
}

const mapDispatchToProps = {
    fetchJobs,
    setRequest,
}

class RequestCard extends React.Component<IProps> {

    componentDidMount() {
        this.props.fetchJobs()
    }

    render() {
        return (
            <div className={'scope__RequestCard'}>
                <BaseCard top={this.renderCardTop()}>
                    {this.renderCardBody()}
                </BaseCard>
            </div>
        )
    }

    private renderUserCredentials = () => {
        const {customer} = this.props.request
        const {userName, email} = customer

        return (
            <div className={'user-credentials-wrapper'}>
                <div className={'username'}>{userName}</div>
                <div className={'email'}>{email}</div>
            </div>
        )
    }

    private getIsClaimed = (requestId: string): boolean => {
        const {jobs} = this.props
        return jobs.find(job => job.request.id === requestId) !== undefined
    }

    private renderClaimButton = () => {
        const {id} = this.props.request
        const isClaimed = this.getIsClaimed(id!)

        return (
            <div className={'claim-button-wrapper'}>
                <button
                    className={`claim ui-button ${isClaimed ? 'ui-button--green' : ''}`}
                    onClick={!isClaimed ? this.onClaimClick: this.onUnclaimClick}
                >
                    {isClaimed
                        ? <>
                            <Icon className={'claim__icon'} icon={EIcon.CHECK} style={EIconStyle.SOLID} /> Claimed
                        </>
                        : 'Claim'
                    }
                </button>
            </div>
        )
    }

    private onDeleteClick = () => {
        this.props.setRequest(this.props.request)
        history.push(EPath.DELETE_REQUEST)
    }

    private renderCardTop = () => {
        const {customer} = this.props.request
        const {image} = customer

        return (
            <>
                <ResourceImage className={'profile-picture'} image={image} alt={'user'} />
                {this.renderUserCredentials()}
                {this.renderClaimButton()}
                {customer.id === this.props.user!.id &&
                    <button
                        className={'delete ui-button ui-button--reverted'}
                        onClick={this.onDeleteClick}
                    >
                        Delete
                    </button>
                }
            </>
        )
    }

    private renderBasicInfo = () => {
        const {location, award} = this.props.request

        return (
            <BasicInfoWrapper>
                <div className={'location'}>
                    <Icon className={'location__icon'} icon={EIcon.LOCATION} style={EIconStyle.SOLID} />
                    <span className={'location__name'}>{location}</span>
                </div>
                <div className={'award'}>
                    <Icon className={'award__icon'} icon={EIcon.MONEY} style={EIconStyle.SOLID} />
                    <span className={'award__value'}>{award}</span>
                </div>
            </BasicInfoWrapper>
        )
    }

    private renderMonsters = () => {
        const Monster: React.FC<{monster: IMonster, index: number}> = (props) => {
            const [isHovered, setIsHovered] = React.useState(false)
            const {id, image, name} = props.monster

            return (
                <Link
                    className={'monster'}
                    to={`${EPath.MONSTERS}/${id}`}
                    key={props.index}
                    onMouseEnter={() => setIsHovered(true)}
                    onMouseLeave={() => setIsHovered(false)}
                >
                    <ResourceImage className={'monster__image'} image={image} alt={'monster'} />
                    <div className={'monster__name'}>{name}</div>
                    <Icon
                        className={`monster__icon ${isHovered ? 'monster__icon--visible' : ''}`}
                        icon={EIcon.SEARCH}
                        style={EIconStyle.SOLID}
                    />
                </Link>
            )
        }

        const {monsters} = this.props.request

        return (
            <div className={'monsters'}>
                <h3>Monsters</h3>
                {monsters.map((monster, index) => {
                    return <Monster monster={monster} index={index} />
                })}
            </div>
        )
    }

    private renderCardBody = () => {
        return (
            <>
                {this.renderBasicInfo()}
                {this.renderMonsters()}
            </>
        )
    }

    private onClaimClick = async () => {
        const {request} = this.props

        const heroResponse = await findHeroByUserId(this.props.user!.id)
        let hero
        if (heroResponse.success) {
            hero = heroResponse.data
        }

        const job: IJob = {
            request,
            heroes: [hero],
            evaluation: 0,
            status: EJobStatus.ASSIGNED,
            severity: request.severity,
        }

        const response = await createJob(request.id!, job)
        if (response.success) {
            this.props.fetchJobs()
        }
    }

    private onUnclaimClick = async () => {
        const {jobs} = this.props
        const job = jobs.find(job => job.request.id === this.props.request.id)
        const response = await deleteJob(job!.id!)
        if (response.success) {
            this.props.fetchJobs()
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestCard)
