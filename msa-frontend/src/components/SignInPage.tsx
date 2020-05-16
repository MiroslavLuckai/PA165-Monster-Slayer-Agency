import React from 'react'
import 'styles/SignInPage.scss'
import {connect} from 'react-redux'
import {signIn} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../history'
import {ICredentials} from 'types/ICredentials'
import {areCredentialsValid} from 'utils/auth'
import {IStore} from 'ducks/reducers'

interface IStateProps {
    isAuthenticated: boolean | null,
}

interface IDispatchProps {
    signIn: any,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

interface IState {
    emailInputValue: string,
    passwordInputValue: string,
    isErrorDisplayed: boolean,
}

const mapStateToProps = (state: IStore) => {
    return {
        isAuthenticated: state.auth.isAuthenticated,
    }
}

const mapDispatchToProps = {
    signIn,
    setActiveLayer,
}

class SignInPage extends React.Component<IProps, IState> {

    state: IState = {
        emailInputValue: '',
        passwordInputValue: '',
        isErrorDisplayed: false,
    }

    componentDidMount() {
        setActiveLayer(ELayer.HOME)
    }

    componentDidUpdate(prevProps: IProps, prevState: IState) {
        const {isAuthenticated} = this.props

        if (isAuthenticated) {
            history.push(EPath.HOME)
        } else if (!isAuthenticated && isAuthenticated !== null && !prevState.isErrorDisplayed) {
            this.setState({
                isErrorDisplayed: true,
            })
        }
    }

    render() {
        const {emailInputValue, passwordInputValue, isErrorDisplayed} = this.state

        return (
            <div className={'scope__SignInPage'}>
                <form className={'sign-in-form'}>
                    <h2 className={'sign-in-form__title'}>Welcome</h2>
                    <input
                        className={'sign-in-form__email-input'}
                        value={emailInputValue}
                        onChange={this.onEmailInputValueChange}
                        type={'text'}
                        placeholder={'Email'}
                    />
                    <input
                        className={'sign-in-form__password-input'}
                        value={passwordInputValue}
                        onChange={this.onPasswordInputValueChange}
                        type={'password'}
                        placeholder={'Password'}
                    />
                    {isErrorDisplayed && <div className={'sign-in-form__error'}>Invalid email or password.</div>}
                    <div className={'sign-in-form__confirm-wrapper'}>
                        <button className={'sign-in-form__confirm'} onClick={this.signInUser}>Sign In</button>
                    </div>
                </form>
            </div>
        )
    }

    private onEmailInputValueChange = (event: any) => {
        this.setState({
            emailInputValue: event.target.value,
        })
    }

    private onPasswordInputValueChange = (event: any) => {
        this.setState({
            passwordInputValue: event.target.value,
        })
    }

    private signInUser = (event: any) => {
        event.preventDefault()

        const credentials: ICredentials = {
            email: this.state.emailInputValue,
            password: this.state.passwordInputValue,
        }

        if (areCredentialsValid(credentials)) {
            this.setState({
                isErrorDisplayed: false,
            })
            this.props.signIn(credentials)
        } else {
            this.setState({
                isErrorDisplayed: true,
            })
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(SignInPage)
