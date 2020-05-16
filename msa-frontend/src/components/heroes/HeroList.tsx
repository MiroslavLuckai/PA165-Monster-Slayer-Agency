import React from 'react'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchHeroes} from 'ducks/actions/heroes'
import {setActiveLayer} from 'ducks/actions/common'
import {IHero} from 'types/IHero'
import HeroCard from 'components/heroes/HeroCard'
import {ELayer} from 'enums/ELayer'
import BaseList from 'components/common/BaseList'

interface IStateProps {
    heroes: IHero[],
}

interface IDispatchProps {
    fetchHeroes: any,
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        heroes: state.heroes.heroesList,
    }
}

const mapDispatchToProps = {
    fetchHeroes,
    setActiveLayer,
}

class HeroList extends React.Component<IProps> {

    componentDidMount() {
        const {fetchHeroes, setActiveLayer} = this.props
        setActiveLayer(ELayer.HERO)
        fetchHeroes()
    }

    render() {
        const {heroes} = this.props

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
