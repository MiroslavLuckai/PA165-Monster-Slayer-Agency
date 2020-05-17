import React from 'react'
import 'styles/RequestCreate.scss'
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
import {createRequest} from 'api/request'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import SignInPage from 'components/SignInPage'
import {IRequest} from "../../types/IRequest";

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

interface IState {
    locationInputValue: string,
    awardInputValue: string,

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
        locationInputValue: '',
        awardInputValue: '',
    }

    componentDidMount() {
        setActiveLayer(ELayer.REQUEST)
    }

    render() {
        const {locationInputValue, awardInputValue} = this.state

        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__RequestCreate'}>
                <form className={'create-request-form'}>
                    <h2 className={'create-request-form__title'}>Add a request</h2>
                    <input className={'location-input ui-input'} value={locationInputValue} onChange={this.onLocationInputChange} placeholder={'Location'} />
                    <input className={'award-input ui-input'} value={awardInputValue} onChange={this.onAwardInputChange} placeholder={'Award'} />
                    <div className={'create-request-form__confirm-wrapper'}>
                        <button className={'create-request-form__confirm'} onClick={this.createRequest}>Submit</button>
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
        this.setState({
            awardInputValue: event.target.value,
        })
    }

    private createRequest = async (event: any) => {
        event.preventDefault()
        const {locationInputValue, awardInputValue} = this.state
        const {user} = this.props

        const request: IRequest = {
            customer: user!,
            location: locationInputValue,
            award: Number(awardInputValue),
        }

        await createRequest(request).then(() => {history.push(EPath.REQUESTS)})
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(RequestCreate)
