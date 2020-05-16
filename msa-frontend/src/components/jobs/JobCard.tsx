import React from 'react'
import 'styles/JobCard.scss'
import 'styles/ui.scss'
import {IJob} from 'types/IJob'
import BaseCard from 'components/common/BaseCard'
import {getDisplayText} from 'utils/common'
import {EJobSeverity} from 'enums/EJobSeverity'
import {Link} from 'react-router-dom'
import {EPath} from 'enums/EPath'
import Icon from 'components/common/Icon'
import {EIcon, EIconStyle} from 'enums/EIcon'
import {IHero} from 'types/IHero'
import ResourceImage from 'components/common/ResourceImage'

interface IProps {
    job: IJob,
}

const JobCard: React.FC<IProps> = (props) => {

    const {request, heroes, evaluation, status, severity} = props.job

    const getSeverityBadgeColorClass = () => {
        if (severity === EJobSeverity.MINOR) {
            return 'bg-confirm'
        } else if (severity === EJobSeverity.MODERATE) {
            return 'bg-warning'
        } else if (severity === EJobSeverity.CRITICAL) {
            return 'bg-error'
        }
    }

    const renderCardTop = () => {
        return (
            <>
                <div className={`job-severity ui-badge ${getSeverityBadgeColorClass()}`}>
                    {getDisplayText(severity)}
                </div>
                <div className={'job-status ui-badge'}>{getDisplayText(status)}</div>
                <Link className={'request-id'} to={`${EPath.REQUESTS}/${request.id}`}>#{request.id}</Link>
                <div className={'job-rating'}>
                    <Icon className={'job-rating__icon'} icon={EIcon.STAR} style={EIconStyle.SOLID} />
                    <span className={'job-rating__value'}>{evaluation.toFixed(2)}</span>
                </div>
            </>
        )
    }

    const Hero: React.FC<{hero: IHero, index: number}> = (props) => {
        const [isHovered, setIsHovered] = React.useState(false)
        const {id, name, image} = props.hero

        return (
            <Link
                className={'hero'}
                to={`${EPath.HEROES}/${id}`}
                key={props.index}
                onMouseEnter={() => setIsHovered(true)}
                onMouseLeave={() => setIsHovered(false)}
            >
                <ResourceImage className={'hero__image'} image={image} alt={'hero'} />
                <span className={'hero__name'}>{name}</span>
                <Icon
                    className={`hero__icon ${isHovered ? 'hero__icon--visible' : ''}`}
                    icon={EIcon.SEARCH}
                    style={EIconStyle.SOLID}
                />
            </Link>
        )
    }

    const renderHeroes = () => {
        return (
            <div className={'heroes'}>
                <h3>Heroes</h3>
                {heroes.map((hero, index) => {
                    return <Hero hero={hero} index={index} />
                })}
            </div>
        )
    }

    const renderCardBody = () => {
        return (
            <>
                {renderHeroes()}
            </>
        )
    }

    return (
        <div className={'scope__JobCard'}>
            <BaseCard top={renderCardTop()}>
                {renderCardBody()}
            </BaseCard>
        </div>
    )
}

export default JobCard
