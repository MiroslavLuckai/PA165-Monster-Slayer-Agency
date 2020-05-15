import React from 'react'
import 'styles/SignInPage.scss'
import {connect} from 'react-redux'
import {signIn} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../history'

interface IDispatchProps {
    signIn: typeof signIn,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IDispatchProps {}

interface IState {
    emailInputValue: string,
    passwordInputValue: string,
}

const mapDispatchToProps = {
    signIn,
    setActiveLayer,
}

class SignInPage extends React.Component<IProps, IState> {

    state: IState = {
        emailInputValue: '',
        passwordInputValue: '',
    }

    componentDidMount() {
        setActiveLayer(ELayer.HOME)
    }

    render() {
        const {emailInputValue, passwordInputValue} = this.state

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

        // const email = this.state.emailInputValue
        // const password = this.state.passwordInputValue

        this.props.signIn()
    }
}

export default connect(null, mapDispatchToProps)(SignInPage)
