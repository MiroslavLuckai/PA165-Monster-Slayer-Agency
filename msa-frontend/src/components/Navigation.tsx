import React from 'react'
import 'styles/Navigation.scss'
import {Link} from 'react-router-dom'
import {ENavigationItem} from 'enums/ENavigationItem'

interface IState {
    activeItem: ENavigationItem,
}

class Navigation extends React.Component<{}, IState> {

    state: IState = {
        activeItem: ENavigationItem.HOME,
    }

    render() {
        const {activeItem} = this.state

        return (
            <div className={'scope__Navigation'}>
                <Link
                    className={`item ${activeItem === ENavigationItem.HOME && 'item--active'}`}
                    to={'/'}
                    onClick={() => this.onLinkClick(ENavigationItem.HOME)}
                >
                    <i className={'home icon'}/> Home
                </Link>
                <Link
                    className={`item ${activeItem === ENavigationItem.HEROES && 'item--active'}`}
                    to={'/heroes'}
                    onClick={() => this.onLinkClick(ENavigationItem.HEROES)}
                >
                    Heroes
                </Link>
                <Link
                    className={`item ${activeItem === ENavigationItem.REQUESTS && 'item--active'}`}
                    to={'/requests'}
                    onClick={() => this.onLinkClick(ENavigationItem.REQUESTS)}
                >
                    Requests
                </Link>
                <Link
                    className={`item ${activeItem === ENavigationItem.JOBS && 'item--active'}`}
                    to={'/jobs'}
                    onClick={() => this.onLinkClick(ENavigationItem.JOBS)}
                >
                    Jobs
                </Link>
            </div>
        )
    }

    private onLinkClick = (clickedItem: ENavigationItem) => {
        this.setState({
            activeItem: clickedItem,
        })
    }
}

export default Navigation
