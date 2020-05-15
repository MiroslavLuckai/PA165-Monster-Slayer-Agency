import React from 'react'
import {connect} from 'react-redux'
import {RouteComponentProps} from 'react-router'
import {IStore} from 'ducks/reducers'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {fetchMonster} from 'ducks/actions/monsters'
import {IMonster} from 'types/IMonster'
import MonsterCard from 'components/monsters/MonsterCard'
import BaseList from 'components/common/BaseList'

interface IStateProps {
    monster?: IMonster,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchMonster: any,
}

interface IProps extends IStateProps, IDispatchProps, RouteComponentProps<{id: string}> {}

const mapStateToProps = (state: IStore) => {
    return {
        monster: state.monsters.currentMonster,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    fetchMonster,
}

class MonsterPreview extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.MONSTER)
        this.props.fetchMonster(this.props.match.params.id)
    }

    render() {
        const {monster} = this.props

        if (!monster) {
            return null
        }

        return (
            <div className={'scope__MonsterPreview'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <MonsterCard monster={monster} />
                    </div>
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MonsterPreview)
