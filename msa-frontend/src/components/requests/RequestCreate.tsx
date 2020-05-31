import React from 'react'
import 'styles/RequestCreate.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {signIn} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../../history'
import {createRequest} from 'api/request'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import SignInPage from 'components/SignInPage'
import {IRequest} from 'types/IRequest'
import {IMonster} from 'types/IMonster'
import {EJobSeverity} from 'enums/EJobSeverity'
import {fetchMonsters} from 'ducks/actions/monsters'
import {getDisplayText} from 'utils/common'
import produce from 'immer'
import {findMonsterByName} from 'api/monster'

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
    monsters: IMonster[],
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchMonsters: any,
}

interface IProps extends IStateProps, IDispatchProps {}

interface IState {
    locationInputValue: string,
    awardInputValue: string,
    monsters: IMonster[],
    severity: EJobSeverity | null,
    monsterNames: string[],
}

const mapStateToProps = (state: IStore) => {
    return {
        user: state.auth.user,
        isSignedIn: state.auth.isSignedIn,
        monsters: state.monsters.monstersList,
    }
}

const mapDispatchToProps = {
    signIn,
    setActiveLayer,
    fetchMonsters,
}

class RequestCreate extends React.Component<IProps, IState> {

    state: IState = {
        locationInputValue: '',
        awardInputValue: '',
        monsters: [] as IMonster[],
        severity: null,
        monsterNames: [] as string[],
    }

    componentDidMount() {
        setActiveLayer(ELayer.REQUEST)
        this.props.fetchMonsters()
    }

    render() {
        const {locationInputValue, awardInputValue, severity, monsters} = this.state

        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__RequestCreate'}>
                <form className={'create-request-form'}>
                    <h2 className={'create-request-form__title'}>Create a Request</h2>
                    <input
                        className={'location-input ui-input'}
                        type={'text'}
                        value={locationInputValue}
                        onChange={this.onLocationInputChange}
                        placeholder={'Location'}
                    />
                    <input
                        className={'award-input ui-input'}
                        type={'number'}
                        value={awardInputValue}
                        onChange={this.onAwardInputChange}
                        placeholder={'Award'}
                    />
                    <h3 className={'create-request-form__monsters-title'}>Select monsters:</h3>
                    <div className={'monsters'}>
                        {this.props.monsters.map((monsterInput, index) => {
                            return (
                                <span className={'monster'}>
                                <label htmlFor={monsterInput.id}>
                                    <input
                                        id={monsterInput.id}
                                        type={'checkbox'}
                                        value={monsterInput.name}
                                        key={index}
                                        checked={monsters.find(monster => monster.id === monsterInput.id) !== undefined}
                                        onChange={this.onMonsterCheckboxChange}
                                    />
                                    <span className={'label'}>{monsterInput.name}</span>
                                </label>
                            </span>
                            )
                        })}
                    </div>
                    <h3 className={'create-request-form__severity-title'}>Select severity:</h3>
                    <div className={'severity-wrapper'}>
                        {Object.values(EJobSeverity).map((severityInput, index) => {
                            return (
                                <span className={'severity'}>
                                    <label htmlFor={severityInput}>
                                        <input
                                            id={severityInput}
                                            type={'radio'}
                                            value={severityInput}
                                            key={index}
                                            checked={severityInput === severity}
                                            onChange={this.onSeverityChange}
                                        />
                                        <span className={'label'}>{getDisplayText(severityInput)}</span>
                                    </label>
                                </span>
                            )
                        })}
                    </div>
                    <div className={'create-request-form__confirm-wrapper'}>
                        <button
                            className={'create-request-form__confirm'}
                            onClick={this.createRequest}
                            disabled={!locationInputValue || !awardInputValue || !monsters || !severity}
                        >
                            Submit
                        </button>
                    </div>
                </form>
            </div>
        )
    }

    private onLocationInputChange = (event: any) => {
        this.setState({
            locationInputValue: event.target.value,
        })
    }

    private onAwardInputChange = (event: any) => {
        if (event.target.value >= 0) {
            this.setState({
                awardInputValue: event.target.value,
            })
        }
    }

    private onMonsterCheckboxChange = async (event: any) => {
        const targetValue = event.target.value as string
        const monsterResponse = await findMonsterByName(targetValue)
        if (monsterResponse.success) {
            this.setState(produce(this.state, draftState => {
                const foundMonster = monsterResponse.data
                if (draftState.monsters.find(monster => monster.id === foundMonster.id) !== undefined) {
                    const index = draftState.monsters.findIndex(monster => monster.id === foundMonster.id)
                    draftState.monsters = [...draftState.monsters.slice(0, index), ...draftState.monsters.slice(index + 1, draftState.monsters.length)]
                } else {
                    draftState.monsters = [...draftState.monsters, foundMonster]
                }
            }))
        }
    }

    private onSeverityChange = (event: any) => {
        this.setState({
            severity: event.target.value,
        })
    }

    private createRequest = async (event: any) => {
        event.preventDefault()
        const {locationInputValue, awardInputValue, monsters, severity} = this.state
        const {user} = this.props

        const request: IRequest = {
            customer: user!,
            location: locationInputValue,
            award: Number(awardInputValue),
            monsters,
            severity: severity ?? EJobSeverity.MINOR,
        }

        createRequest(request).then(() => {history.push(EPath.REQUESTS)})
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestCreate)
