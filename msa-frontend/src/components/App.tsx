import React from 'react'
import 'styles/App.scss'
import {Router, Route, Switch} from 'react-router-dom'
import history from '../history'
import Navigation from 'components/Navigation'
import HeroList from 'components/HeroList'
import JobList from 'components/JobList'
import RequestList from 'components/RequestList'
import Home from 'components/Home'
import SignInPage from 'components/SignInPage'
import ErrorNotification from 'components/ErrorNotification'
import {connect} from 'react-redux'
import {IStore} from 'ducks/reducers'
import {EPath} from 'enums/EPath'
import MonsterList from 'components/MonsterList'
import MonsterPreview from 'components/MonsterPreview'

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
            <div className={''}>
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
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default connect(mapStateToProps)(App)
