import React from 'react'
import ReactDOM from 'react-dom'
import {Provider} from 'react-redux'
import App from 'components/App'
import {applyMiddleware, compose, createStore} from 'redux'
import thunk from 'redux-thunk'
import { composeWithDevTools } from 'redux-devtools-extension'
import reducers from 'ducks/reducers'

const store = createStore(reducers, compose(applyMiddleware(thunk), composeWithDevTools()))

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.querySelector('#root')
)
