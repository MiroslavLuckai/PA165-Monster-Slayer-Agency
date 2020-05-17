import React from 'react'
import 'styles/HeroList.scss'
import 'styles/ui.scss'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchHeroes} from 'ducks/actions/heroes'
import {setActiveLayer} from 'ducks/actions/common'
import {IHero} from 'types/IHero'
import HeroCard from 'components/heroes/HeroCard'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'
import SignInPage from 'components/SignInPage'
import history from '../../history'
import {EPath} from 'enums/EPath'
import {IUser} from 'types/IUser'

interface IStateProps {
    heroes: IHero[],
    isSignedIn: boolean,
    user?: IUser,
}

interface IDispatchProps {
    fetchHeroes: any,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        heroes: state.heroes.heroesList,
        isSignedIn: state.auth.isSignedIn,
        user: state.auth.user,
    }
}

const mapDispatchToProps = {
    fetchHeroes,
    setActiveLayer,
}

class HeroList extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.HERO)
        if (this.props.isSignedIn) {
            this.props.fetchHeroes()
        }
    }

    componentDidUpdate(prevProps: IProps) {
        if (!prevProps.isSignedIn && this.props.isSignedIn) {
            this.props.fetchHeroes()
        }
    }

    render() {
        const {heroes, isSignedIn} = this.props

        if (!isSignedIn) {
            return <SignInPage />
        }

        return (
            <div className={'scope__HeroList'}>
                {this.renderButton()}
                <BaseList>
                    {heroes.map((hero, index) => {
                        return (
                            <div className={'card-wrapper'}>
                                <HeroCard hero={hero} key={index} />
                            </div>
                        )
                    })}
                </BaseList>
            </div>
        )
    }

    private renderButton = () => {
        if (this.props.user!.hero) {
            return (
                <button
                    className={'create ui-button ui-button--yellow'}
                    onClick={() => history.push(EPath.DELETE_HERO)}
                >
                    Cancel being a Hero
                </button>
            )
        } else {
            return (
                <button
                    className={'create ui-button ui-button--yellow'}
                    onClick={() => history.push(EPath.CREATE_HERO)}
                >
                    Become a Hero
                </button>
            )
        }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroList)
