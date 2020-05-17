import React from "react";
import 'styles/MonsterCreate.scss'
import 'styles/ui.scss'
import {EMonsterType} from 'enums/EMonsterType'
import {EMonsterFood} from 'enums/EMonsterFood'
import {setActiveLayer} from 'ducks/actions/common'
import {EResistance} from 'enums/EResistance'
import {ELayer} from 'enums/ELayer'
import {connect} from 'react-redux'
import {getDisplayText} from 'utils/common'
import produce from 'immer'

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IDispatchProps {}

interface IState {
    nameInputValue: string,
    sizeInputValue: string,
    resistances: EResistance[],
    monsterType: EMonsterType | null,
    imageInputValue: string,
    food: EMonsterFood | null,
}

const mapDispatchToProps = {
    setActiveLayer,
}

class MonsterCreate extends React.Component<IProps, IState> {

    state: IState = {
        nameInputValue: '',
        sizeInputValue: '',
        resistances: [] as EResistance[],
        monsterType: null,
        imageInputValue: '',
        food: null,
    }

    componentDidMount() {
        setActiveLayer(ELayer.MONSTER)
    }

    render() {
        const {nameInputValue, sizeInputValue, resistances, monsterType, imageInputValue, food} = this.state

        return (
            <div className={'scope__MonsterCreate'}>
                <form className={'create-monster-form'}>
                    <h2 className={'create-monster-form__title'}>Add a new monster</h2>
                    <input
                        className={'ui-input'}
                        type={'text'}
                        value={nameInputValue}
                        onChange={this.onNameInputValueChange}
                        placeholder={'Name'}
                    />
                    <input
                        className={'ui-input'}
                        type={'number'}
                        value={sizeInputValue ? Number(sizeInputValue) : ''}
                        onChange={this.onSizeInputValue}
                        placeholder={'Size'}
                    />
                    <h3 className={'create-monster-form__resistances-title'}>Select resistances:</h3>
                    <div className={'resistances'}>
                        {Object.values(EResistance).map((resistance, index) => {
                            return (
                                <span className={'resistance'}>
                                    <input
                                        id={resistance}
                                        type={'checkbox'}
                                        value={resistance}
                                        key={index}
                                        checked={resistances.includes(resistance)}
                                        onChange={this.onResistanceCheckboxChange}
                                    />
                                    <label htmlFor={resistance}>{getDisplayText(resistance)}</label>
                                </span>
                            )
                        })}
                    </div>
                    <div className={'create-monster-form__confirm-wrapper'}>
                        <button className={'create-monster-form__confirm'} onClick={() => console.log('create hero')}>Submit</button>
                    </div>
                </form>
            </div>
        )
    }

    private onNameInputValueChange = (event: any) => {
        this.setState({
            nameInputValue: event.target.value,
        })
    }

    private onSizeInputValue = (event: any) => {
        this.setState({
            sizeInputValue: event.target.value,
        })
    }

    private onResistanceCheckboxChange = (event: any) => {
        const targetValue = event.target.value as string
        const targetResistance = EResistance[targetValue as EResistance]

        this.setState(produce(this.state, draftState => {
            if (draftState.resistances.includes(targetResistance)) {
                const index = draftState.resistances.findIndex(resistance => resistance === targetResistance)
                draftState.resistances = [...draftState.resistances.slice(0, index), ...draftState.resistances.slice(index + 1, draftState.resistances.length)]
            } else {
                draftState.resistances = [...draftState.resistances, targetResistance]
            }
        }))
    }
}

export default connect(null, mapDispatchToProps)(MonsterCreate)
