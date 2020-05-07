import React from 'react'
import 'styles/App.scss'
import {Router, Route, Switch} from 'react-router-dom'
import history from '../history'
import Navigation from 'components/Navigation'
import HeroList from 'components/HeroList'
import JobList from 'components/JobList'
import RequestList from 'components/RequestList'
import Home from 'components/Home'

class App extends React.Component {
    render() {
        return (
            <div className={''}>
                <Router history={history}>
                    <Navigation />
                    <Switch>
                        <Route path={'/'} exact component={Home} />
                        <Route path={'/heroes'} exact component={HeroList} />
                        <Route path={'/requests'} exact component={RequestList} />
                        <Route path={'/jobs'} exact component={JobList} />
                    </Switch>
                </Router>
            </div>
        )
    }
}

export default App
