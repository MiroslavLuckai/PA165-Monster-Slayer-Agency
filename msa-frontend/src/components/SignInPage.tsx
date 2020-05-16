import React from 'react'
import 'styles/SignInPage.scss'

interface IState {
    emailInputValue: string,
    passwordInputValue: string,
}

class SignInPage extends React.Component<{}, IState> {

    state: IState = {
        emailInputValue: '',
        passwordInputValue: '',
    }

    render() {
        const {emailInputValue, passwordInputValue} = this.state

        return (
            <div className={'scope__SignInPage'}>
                <form className={'sign-in-form'}>
                    <h2 className={'sign-in-form__title'}>Sign In</h2>
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

    private signInUser = () => {
        console.log('sign-in')
    }
}

export default SignInPage
