import React from "react";
import {setActiveLayer} from "../../ducks/actions/common";
import {EResistance} from "../../enums/EResistance";

export const CheckBox = props => {
    return (
        <li>
            <input key={props.id} onClick={props.handleCheckChieldElement} type="checkbox" checked={props.isChecked} value={props.value} /> {props.value}
        </li>
    )
}

export default CheckBox

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IDispatchProps {}

interface IState {
    nameInputValue: string,
    sizeInputValue: string,
    typeInputValue: string,
    resistancesInputValue: any,
    foodInputValue: string,
    imageInputValue: string
}

const mapDispatchToProps = {
    setActiveLayer,
}

class MonsterPreview extends React.Component<IProps, IState> {

    state: IState = {
        nameInputValue: '',
        sizeInputValue: '',
        typeInputValue: string,
        resistancesInputValue: [
            {id: 1, value: EResistance.FIRE, isChecked: false},
            {id: 2, value: EResistance.ICE, isChecked: false},
            {id: 3, value: EResistance.WATER, isChecked: false},
            {id: 5, value: EResistance.WIND, isChecked: false},
            {id: 6, value: EResistance.LIGHTNING, isChecked: false},
            {id: 7, value: EResistance.ROCK, isChecked: false},
            {id: 8, value: EResistance.DARK, isChecked: false},
            {id: 9, value: EResistance.PSYCHIC, isChecked: false},
            {id: 10, value: EResistance.GHOST, isChecked: false},
            {id: 11, value: EResistance.GRASS, isChecked: false},
            {id: 12, value: EResistance.POISON, isChecked: false},
            {id: 13, value: EResistance.BLEEDING, isChecked: false}
        ],
        foodInputValue: string,
        imageInputValue: ''
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
}
