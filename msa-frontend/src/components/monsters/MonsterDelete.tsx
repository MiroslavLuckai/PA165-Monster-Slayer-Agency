import React from 'react'
import 'styles/MonsterDelete.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {setUser} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../../history'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import SignInPage from 'components/SignInPage'
import BaseCard from 'components/common/BaseCard'
import BaseList from 'components/common/BaseList'
import {IMonster} from 'types/IMonster'
import {deleteMonster} from 'api/monster'
import {fetchMonsters} from 'ducks/actions/monsters'

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
    monster?: IMonster,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    setUser: typeof setUser,
    fetchMonsters: any,
}

interface IOwnProps {}

interface IProps extends IStateProps, IDispatchProps, IOwnProps {}

const mapStateToProps = (state: IStore) => {
    return {
        user: state.auth.user,
        isSignedIn: state.auth.isSignedIn,
        monster: state.monsters.currentMonster,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    setUser,
    fetchMonsters,
}

class MonsterDelete extends React.Component<IProps> {

    componentDidMount() {
        setActiveLayer(ELayer.MONSTER)
    }

    render() {
        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__MonsterDelete'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <BaseCard top={<h3>Are you sure you want to delete this monster?</h3>}>
                            <div className={'button-wrapper'}>
                                <button
                                    className={'no ui-button ui-button--reverted'}
                                    onClick={this.onNoButtonClick}
                                >
                                    No
                                </button>
                                <button
                                    className={'yes ui-button'}
                                    onClick={this.onYesButtonClick}
                                >
                                    Yes
                                </button>
                            </div>
                        </BaseCard>
                    </div>
                </BaseList>
            </div>
        )
    }

    private onNoButtonClick = () => {
        history.push(EPath.MONSTERS)
    }

    private onYesButtonClick = () => {
        deleteMonster(this.props.monster!.id!).then(() => {
            this.props.fetchMonsters()
            history.push(EPath.MONSTERS)
        })
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(MonsterDelete)
