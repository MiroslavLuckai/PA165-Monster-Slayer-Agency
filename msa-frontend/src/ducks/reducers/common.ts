import {HIDE_ERROR_NOTIFICATION, SET_ACTIVE_LAYER, SHOW_ERROR_NOTIFICATION} from 'ducks/actions/types'
import {ELayer} from 'enums/ELayer'
import produce from 'immer'

export interface ICommonState {
    layer: ELayer,
    isErrorDisplayed: boolean,
}

const initialState: ICommonState = {
    layer: ELayer.HOME,
    isErrorDisplayed: false,
}

const setActiveLayer = (state: ICommonState, layer: ELayer) => {
    return produce(state, draft => {
        draft.layer = layer
    })
}

const setIsErrorDisplayed = (state: ICommonState, isDisplayed: boolean) => {
    return produce(state, draft => {
        draft.isErrorDisplayed = isDisplayed
    })
}

export default (state: ICommonState = initialState, action: any) => {
    switch (action.type) {
        case SET_ACTIVE_LAYER:
            return setActiveLayer(state, action.payload)
        case SHOW_ERROR_NOTIFICATION:
            return setIsErrorDisplayed(state, action.payload)
        case HIDE_ERROR_NOTIFICATION:
            return setIsErrorDisplayed(state, action.payload)
        default:
            return state
    }
}
