import React from 'react'
import 'styles/HeroList.scss'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {fetchHeroes} from 'ducks/actions/heroes'
import {IHero} from 'types/IHero'
import HeroCard from 'components/HeroCard'

interface IStateProps {
    heroes: IHero[],
}

interface IDispatchProps {
    fetchHeroes: typeof fetchHeroes,
}

interface IProps extends IStateProps, IDispatchProps {}

const mapStateToProps = (state: IStore) => {
    return {
        heroes: state.heroes.heroesList,
    }
}

const mapDispatchToProps = {
    fetchHeroes,
}

class HeroList extends React.Component<IProps> {

    componentDidMount() {
        const {fetchHeroes} = this.props
        fetchHeroes()
    }

    render() {
        const {heroes} = this.props

        return (
            <div className={'scope__HeroList'}>
                {heroes.map((hero, index) => {
                    return (
                        <div className={'card-wrapper'}>
                            <HeroCard hero={hero} key={index} />
                        </div>
                    )
                })}
            </div>
        )
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(HeroList)
