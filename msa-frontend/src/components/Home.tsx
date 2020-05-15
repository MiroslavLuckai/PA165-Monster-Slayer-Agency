import React from 'react'
import 'styles/Home.scss'
import {setActiveLayer} from 'ducks/actions/common'
import {connect} from 'react-redux'
import {ELayer} from 'enums/ELayer'

interface IDispatchProps {
    setActiveLayer: typeof setActiveLayer,
}

interface IProps extends IDispatchProps {}

const mapDispatchToProps = {
    setActiveLayer,
}

class Home extends React.Component<IProps> {

    componentDidMount() {
        this.props.setActiveLayer(ELayer.HOME)
    }

    render() {
        return (
            <div className={'scope__Home'}>
                <h1>Monster Slayer Agency</h1>
                <p>
                    Monster Slayer Agency is a web application focused on monsters.
                    Are you a mighty hero looking for some easy coins? Poor peasant who is not
                    capable of harvesting his plants? Or just nerd who is interested in learning
                    fact or two about monsters? If your answer to any of this questions is yes,
                    our web application is for you. Create your own account and discover all the
                    options our application have yourself.
                </p>
                <p>
                    Monster Slayer Agency will allow its users to create new job offers or take
                    the existing one. If you have problem with slaying monsters full list of well
                    know beasts with all the details(weaknesses, strengths, resistance, ...) is
                    available. Have you discovered a new beast? Feel free to add it into our database
                    and earn some coins. Do you have enough of paying gold for things you can do
                    yourself? Update your profile and try monster hunting adventure yourself.
                </p>
            </div>
        )
    }
}

export default connect(null, mapDispatchToProps)(Home)
