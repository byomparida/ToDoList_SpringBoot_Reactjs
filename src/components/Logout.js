import React, { Component } from 'react'
import { Redirect } from "react-router-dom";
import { logout } from '../utils/Common';

class Logout extends Component {

    renderRedirect = () => {
        logout();
        return <Redirect to="/home" />
    };

    render() {
        return (
            <div>
                <div id="app">
                    <section class="hero is-fullheight is-light" >
                        <div class="hero-head">
                            <div class="container is-widescreen">
                                {this.renderRedirect()}
                            </div>
                        </div>
                    </section>
                </div>            
            </div>
        )
    }
}

export default Logout
