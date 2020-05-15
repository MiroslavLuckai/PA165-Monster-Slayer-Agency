import React from 'react'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchHeroes} from 'ducks/actions/heroes'
import {setActiveLayer} from 'ducks/actions/common'
import {IHero} from 'types/IHero'
import HeroCard from 'components/heroes/HeroCard'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'
import SignInPage from 'components/SignInPage'

interface IStateProps {
    heroes: IHero[],
    isSignedIn: boolean,
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

    componentDidUpdate() {
        if (this.props.isSignedIn) {
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
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroList)
