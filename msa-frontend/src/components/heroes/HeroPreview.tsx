import React from 'react'
import {connect} from 'react-redux'
import {RouteComponentProps} from 'react-router'
import {IStore} from 'ducks/reducers'
import {setActiveLayer} from 'ducks/actions/common'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'
import {IHero} from 'types/IHero'
import HeroCard from 'components/heroes/HeroCard'
import {fetchHero} from 'ducks/actions/heroes'
import SignInPage from 'components/SignInPage'

interface IStateProps {
    hero?: IHero,
    isSignedIn: boolean,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
    fetchHero: any,
}

interface IProps extends IStateProps, IDispatchProps, RouteComponentProps<{id: string}> {}

const mapStateToProps = (state: IStore) => {
    return {
        hero: state.heroes.currentHero,
        isSignedIn: state.auth.isSignedIn,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
    fetchHero,
}

class HeroPreview extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.HERO)
        if (this.props.isSignedIn) {
            this.props.fetchHero(this.props.match.params.id)
        }
    }

    componentDidUpdate() {
        if (this.props.isSignedIn) {
            this.props.fetchHero(this.props.match.params.id)
        }
    }

    render() {
        const {hero, isSignedIn} = this.props

        if (!isSignedIn) {
            return <SignInPage />
        }

        if (!hero) {
            return null
        }

        return (
            <div className={'scope__HeroPreview'}>
                <BaseList>
                    <div className={'card-wrapper'}>
                        <HeroCard hero={hero} />
                    </div>
                </BaseList>
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroPreview)
