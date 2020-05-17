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
import history from '../../history'
import {EPath} from 'enums/EPath'
import {IMonster} from 'types/IMonster'
import {createMonster} from "api/monster"

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
                    <h3 className={'create-monster-form__monster-type-title'}>Select monster type:</h3>
                    <div className={'monster-type-wrapper'}>
                        {Object.values(EMonsterType).map((type, index) => {
                            return (
                                <span className={'monster-type'}>
                                    <label htmlFor={type}>
                                        <input
                                            id={type}
                                            type={'radio'}
                                            value={type}
                                            key={index}
                                            checked={monsterType === type}
                                            onChange={this.onMonsterTypeCheckboxChange}
                                        />
                                        <span className={'label'}>{getDisplayText(type)}</span>
                                    </label>
                                </span>
                            )
                        })}
                    </div>
                    <h3 className={'create-monster-form__food-title'}>Select monster food:</h3>
                    <div className={'food-wrapper'}>
                        {Object.values(EMonsterFood).map((foodInput, index) => {
                            return (
                                <span className={'food'}>
                                    <label htmlFor={foodInput}>
                                        <input
                                            id={foodInput}
                                            type={'radio'}
                                            value={foodInput}
                                            key={index}
                                            checked={foodInput === food}
                                            onChange={this.onFoodCheckboxChange}
                                        />
                                        <span className={'label'}>{getDisplayText(foodInput)}</span>
                                    </label>
                                </span>
                            )
                        })}
                    </div>
                    <h3 className={'create-monster-form__resistances-title'}>Select resistances:</h3>
                    <div className={'resistances'}>
                        {Object.values(EResistance).map((resistance, index) => {
                            return (
                                <span className={'resistance'}>
                                    <label htmlFor={resistance}>
                                        <input
                                            id={resistance}
                                            type={'checkbox'}
                                            value={resistance}
                                            key={index}
                                            checked={resistances.includes(resistance)}
                                            onChange={this.onResistanceCheckboxChange}
                                        />
                                        <span className={'label'}>{getDisplayText(resistance)}</span>
                                    </label>
                                </span>
                            )
                        })}
                    </div>
                    <input
                        className={'ui-input'}
                        type={'text'}
                        value={imageInputValue}
                        onChange={this.onImageInputValueChange}
                        placeholder={'Image path'}
                    />
                    <div className={'create-monster-form__confirm-wrapper'}>
                        <button className={'create-monster-form__confirm'} onClick={this.createMonster}>Submit</button>
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
        if (event.target.value >= 0) {
            this.setState({
                sizeInputValue: event.target.value,
            })
        }
    }

    private onImageInputValueChange = (event: any) => {
        this.setState({
            imageInputValue: event.target.value,
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

    private onMonsterTypeCheckboxChange= (event: any) => {
        this.setState({
            monsterType: event.target.value,
        })
    }

    private onFoodCheckboxChange= (event: any) => {
        this.setState({
            food: event.target.value,
        })
    }

    private createMonster = async (event: any) => {
        event.preventDefault()
        const {nameInputValue, sizeInputValue, resistances, monsterType, imageInputValue, food} = this.state

        const monster: IMonster= {
            name: nameInputValue,
            size: Number(sizeInputValue),
            resistances: resistances,
            monsterType: monsterType!,
            image: imageInputValue,
            food: food!
        }

         createMonster(monster).then(() => {history.push(EPath.MONSTERS)})
    }
}

export default connect(null, mapDispatchToProps)(MonsterCreate)
