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
                        <Route path={'/'} exact component={Home} />
                        <Route path={'/sign-in'} exact component={SignInPage} />
                        <Route path={'/heroes'} exact component={HeroList} />
                        <Route path={'/requests'} exact component={RequestList} />
                        <Route path={'/jobs'} exact component={JobList} />
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default connect(mapStateToProps)(App)
