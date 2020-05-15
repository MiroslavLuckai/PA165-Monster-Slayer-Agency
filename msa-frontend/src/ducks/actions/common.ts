import {HIDE_ERROR_NOTIFICATION, SET_ACTIVE_LAYER, SHOW_ERROR_NOTIFICATION} from 'ducks/actions/types'
import {ELayer} from 'enums/ELayer'

export const setActiveLayer = (layer: ELayer) => {
    return {
        type: SET_ACTIVE_LAYER,
        payload: layer,
    }
}

export const showErrorNotification = () => {
    return {
        type: SHOW_ERROR_NOTIFICATION,
        payload: true,
    }
}

export const hideErrorNotification = () => {
    return {
        type: HIDE_ERROR_NOTIFICATION,
        payload: false,
    }
}
