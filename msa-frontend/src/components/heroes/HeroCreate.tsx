import React from 'react'
import 'styles/HeroCreate.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {signIn} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../../history'
import {ESkill} from 'enums/ESkill'
import {getDisplayText} from 'utils/common'
import produce from 'immer'
import {createHero} from 'api/hero'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import {IHero} from 'types/IHero'
import SignInPage from 'components/SignInPage'

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

interface IState {
    nameInputValue: string,
    skills: ESkill[],
}

const mapStateToProps = (state: IStore) => {
    return {
        user: state.auth.user,
        isSignedIn: state.auth.isSignedIn,
    }
}

const mapDispatchToProps = {
    signIn,
    setActiveLayer,
}

class HeroCreate extends React.Component<IProps, IState> {

    state: IState = {
        nameInputValue: '',
        skills: [] as ESkill[],
    }

    componentDidMount() {
        setActiveLayer(ELayer.HERO)
    }

    render() {
        const {nameInputValue, skills} = this.state

        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__HeroCreate'}>
                <form className={'create-hero-form'}>
                    <h2 className={'create-hero-form__title'}>Become a Hero</h2>
                    <input className={'name-input ui-input'} value={nameInputValue} onChange={this.onNameInputChange} placeholder={'Name'} />
                    <h3 className={'create-hero-form__skills-title'}>Select your skills:</h3>
                    <div className={'skills'}>
                        {Object.values(ESkill).map((skill, index) => {
                            return (
                                <span className={'skill'}>
                                <input
                                    id={skill}
                                    type={'checkbox'}
                                    value={skill}
                                    key={index}
                                    checked={skills.includes(skill)}
                                    onChange={this.onSkillCheckboxChange}
                                />
                                <label htmlFor={skill}>{getDisplayText(skill)}</label>
                            </span>
                            )
                        })}
                    </div>
                    <div className={'create-hero-form__confirm-wrapper'}>
                        <button className={'create-hero-form__confirm'} onClick={this.createHero}>Submit</button>
                    </div>
                </form>
            </div>
        )
    }

    private onNameInputChange = (event: any) => {
        this.setState({
            nameInputValue: event.target.value,
        })
    }

    private onSkillCheckboxChange = (event: any) => {
        const targetValue = event.target.value as string
        const targetSkill = ESkill[targetValue as ESkill]
        this.setState(produce(this.state, draftState => {
            if (draftState.skills.includes(targetSkill)) {
                const index = draftState.skills.findIndex(skill => skill === targetSkill)
                draftState.skills = [...draftState.skills.slice(0, index), ...draftState.skills.slice(index + 1, draftState.skills.length)]
            } else {
                draftState.skills = [...draftState.skills, targetSkill]
            }
        }))
    }

    private createHero = async (event: any) => {
        event.preventDefault()
        const {nameInputValue, skills} = this.state
        const {user} = this.props

        const hero: IHero = {
            skills,
            user: user!,
            name: nameInputValue,
            image: user!.image,
        }

        await createHero(hero).then(() => {history.push(EPath.HEROES)})
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroCreate)
