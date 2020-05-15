import React from 'react'
import 'styles/MonsterList.scss'
import {connect} from 'react-redux'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {fetchMonsters} from 'ducks/actions/monsters'
import {IStore} from 'ducks/reducers'
import {IMonster} from 'types/IMonster'
import MonsterCard from 'components/MonsterCard'
import BaseList from 'components/BaseList'

interface IStateProps {
    monsters: IMonster[],
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchMonsters: any,
}

const mapStateToProps = (state: IStore) => {
    return {
        monsters: state.monsters.monstersList,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    fetchMonsters,
}

interface IProps extends IStateProps, IDispatchProps {}

class MonsterList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.MONSTER)
        this.props.fetchMonsters()
    }

    render() {
        return (
            <div className={'scope__MonsterList'}>
                <BaseList>
                    {this.props.monsters.map((monster, index) => {
                        return (
                            <div className={'card-wrapper'}>
                                <MonsterCard monster={monster} key={index} />
                            </div>
                        )
                    })}
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MonsterList)
