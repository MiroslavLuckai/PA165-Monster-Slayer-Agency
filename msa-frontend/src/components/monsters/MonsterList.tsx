import React from 'react'
import {connect} from 'react-redux'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {fetchMonsters} from 'ducks/actions/monsters'
import {IStore} from 'ducks/reducers'
import {IMonster} from 'types/IMonster'
import MonsterCard from 'components/monsters/MonsterCard'
import BaseList from 'components/common/BaseList'
import SignInPage from 'components/SignInPage'

interface IStateProps {
    monsters: IMonster[],
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchMonsters: any,
}

const mapStateToProps = (state: IStore) => {
    return {
        monsters: state.monsters.monstersList,
        isSignedIn: state.auth.isSignedIn,
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
        if (this.props.isSignedIn) {
            this.props.fetchMonsters()
        }
    }

    componentDidUpdate() {
        if (this.props.isSignedIn) {
            this.props.fetchMonsters()
        }
    }

    render() {
        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

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
