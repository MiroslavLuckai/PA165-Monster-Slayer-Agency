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
import SignInPage from 'components/SignInPage'

interface IStateProps {
    monster?: IMonster,
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchMonster: any,
}

interface IProps extends IStateProps, IDispatchProps, RouteComponentProps<{id: string}> {}

const mapStateToProps = (state: IStore) => {
    return {
        monster: state.monsters.currentMonster,
        isSignedIn: state.auth.isSignedIn,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    fetchMonster,
}

class MonsterPreview extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.MONSTER)
        if (this.props.isSignedIn) {
            this.props.fetchMonster(this.props.match.params.id)
        }
    }

    componentDidUpdate() {
        if (this.props.isSignedIn) {
            this.props.fetchMonster(this.props.match.params.id)
        }
    }

    render() {
        const {monster, isSignedIn} = this.props

        if (!isSignedIn) {
            return <SignInPage />
        }

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
