import React from 'react'
import 'styles/App.scss'
import {Router, Route, Switch} from 'react-router-dom'
import history from '../history'
import Navigation from 'components/common/Navigation'
import HeroList from 'components/heroes/HeroList'
import JobList from 'components/jobs/JobList'
import RequestList from 'components/requests/RequestList'
import SignInPage from 'components/SignInPage'
import ErrorNotification from 'components/common/ErrorNotification'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {EPath} from 'enums/EPath'
import MonsterList from 'components/monsters/MonsterList'
import MonsterPreview from 'components/monsters/MonsterPreview'
import RequestPreview from 'components/requests/RequestPreview'
import HeroPreview from 'components/heroes/HeroPreview'
import Home from 'components/Home'
import HeroCreate from 'components/heroes/HeroCreate'
import HeroDelete from 'components/heroes/HeroDelete'

interface IStateProps {
    isErrorDisplayed: boolean,
}

interface IProps extends IStateProps {}

const mapStateToProps = (state: IStore) => {
    return {
        isErrorDisplayed: state.common.isErrorDisplayed,
    }
}

class App extends React.Component<IProps> {

    render() {
        const {isErrorDisplayed} = this.props

        return (
            <div className={'scope__App'}>
                <Router history={history}>
                    <Navigation />
                    {isErrorDisplayed && <ErrorNotification />}
                    <Switch>
                        <Route path={EPath.HOME} exact component={Home} />
                        <Route path={EPath.SIGN_IN} exact component={SignInPage} />
                        <Route path={EPath.HEROES} exact component={HeroList} />
                        <Route path={EPath.MONSTERS} exact component={MonsterList} />
                        <Route path={EPath.REQUESTS} exact component={RequestList} />
                        <Route path={EPath.JOBS} exact component={JobList} />
                        <Route path={`${EPath.MONSTERS}/:id`} exact component={MonsterPreview} />
                        <Route path={`${EPath.REQUESTS}/:id`} exact component={RequestPreview} />
                        <Route path={`${EPath.HEROES}/:id`} exact component={HeroPreview} />
                        <Route path={EPath.CREATE_HERO} exact component={HeroCreate} />
                        <Route path={EPath.DELETE_HERO} exact component={HeroDelete} />
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default connect(mapStateToProps)(App)
