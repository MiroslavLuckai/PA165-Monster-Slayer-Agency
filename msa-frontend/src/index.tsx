import React from 'react'
import ReactDOM from 'react-dom'
import {Provider} from 'react-redux'
import App from 'components/App'
import {createStore} from 'redux'
import { composeWithDevTools } from 'redux-devtools-extension'
import reducers from 'ducks/reducers'

const store = createStore(reducers, composeWithDevTools())

ReactDOM.render(
    <Provider store={store}>
        <App />
    </Provider>,
    document.querySelector('#root')
)
