import React from 'react'
import 'styles/Navigation.scss'
import 'styles/ui.scss'
import {Link} from 'react-router-dom'
import {EPath} from 'enums/EPath'
import {IStore} from 'ducks/reducers'
import {connect} from 'react-redux'
import {ELayer} from 'enums/ELayer'
import {setActiveLayer} from 'ducks/actions/common'

interface IStateProps {
    layer: ELayer,
}

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IOwnProps {}

interface IProps extends IStateProps, IDispatchProps, IOwnProps {}

const mapStateToProps = (state: IStore) => {
    return {
        layer: state.common.layer,
    }
}

const mapDispatchToProps = {
    setActiveLayer,
}

const Navigation: React.FC<IProps> = (props) => {

    const {setActiveLayer, layer} = props

    return (
        <div className={'scope__Navigation'}>
            <Link
                className={`item ${layer === ELayer.HOME && 'item--active'}`}
                to={EPath.HOME}
                onClick={() => setActiveLayer(ELayer.HOME)}
            >
                Home
            </Link>
            <Link
                className={`item ${layer === ELayer.HERO && 'item--active'}`}
                to={EPath.HEROES}
                onClick={() => setActiveLayer(ELayer.HERO)}
            >
                Heroes
            </Link>
            <Link
                className={`item ${layer === ELayer.MONSTER && 'item--active'}`}
                to={EPath.MONSTERS}
                onClick={() => setActiveLayer(ELayer.MONSTER)}
            >
                Monsters
            </Link>
            <Link
                className={`item ${layer === ELayer.REQUEST && 'item--active'}`}
                to={EPath.REQUESTS}
                onClick={() => setActiveLayer(ELayer.REQUEST)}
            >
                Requests
            </Link>
            <Link
                className={`item ${layer === ELayer.JOB && 'item--active'}`}
                to={EPath.JOBS}
                onClick={() => setActiveLayer(ELayer.JOB)}
            >
                Jobs
            </Link>
            <Link className={'sign-in-wrapper'} to={EPath.SIGN_IN}>
                <button className={'sign-in ui-button'}>Sign In</button>
            </Link>
        </div>
    )
}

export default connect(mapStateToProps, mapDispatchToProps)(Navigation)
