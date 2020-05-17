import React from 'react'
import 'styles/HeroDelete.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {setUser, signIn} from 'ducks/actions/auth'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import {EPath} from 'enums/EPath'
import history from '../../history'
import {ESkill} from 'enums/ESkill'
import {deleteHero, findHeroByUserId} from 'api/hero'
import {IUser} from 'types/IUser'
import {IStore} from 'ducks/reducers'
import SignInPage from 'components/SignInPage'
import BaseCard from 'components/common/BaseCard'
import BaseList from 'components/common/BaseList'

interface IStateProps {
    user?: IUser,
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    setUser: typeof setUser,
}

interface IProps extends IStateProps, IDispatchProps {}

interface IState {
    nameInputValue: string,
    skills: ESkill[],
}

const mapStateToProps = (state: IStore) => {
    return {
        user: state.auth.user,
        isSignedIn: state.auth.isSignedIn,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    setUser,
}

class HeroDelete extends React.Component<IProps, IState> {

    state: IState = {
        nameInputValue: '',
        skills: [] as ESkill[],
    }

    componentDidMount() {
        setActiveLayer(ELayer.HERO)
    }

    render() {
        if (!this.props.isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__HeroDelete'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <BaseCard top={<h3>Are you sure you don't want to be a hero anymore?</h3>}>
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
        history.push(EPath.HEROES)
    }

    private onYesButtonClick = async () => {
        const response = await findHeroByUserId(this.props.user!.id)
        if (response.success) {
            const hero = response.data
            const {id} = hero
            const deleteResponse = await deleteHero(id)
            if (deleteResponse.success) {
                this.props.setUser({
                    ...this.props.user!,
                    hero: false,
                })
                history.push(EPath.HEROES)
            }
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroDelete)
