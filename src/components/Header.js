import React, { Component } from 'react'
import { NavLink, withRouter } from "react-router-dom";
import { isLogin } from '../utils/Common';

class Header extends Component {

    render() {
        let displayLogInOrLogoutButton;
        if(isLogin()) {
            displayLogInOrLogoutButton = <NavLink className="navbar-item" to="/logout"> Logout </NavLink>
        } else {
            displayLogInOrLogoutButton = <NavLink className="navbar-item" to="/login"> Login </NavLink>
        }
        return (
            <div>
                <div id="app">
                    <div class="first_nav">
                        <div class="hero is-light">
                            <nav class="navbar">
                                <div class="container">

                                    <div class="navbar-brand">
                                        <NavLink to="/home" exact class="navbar-item">
                                            Home
                                        </NavLink>
                                        
                                        <a role="button" class="navbar-burger burger" aria-label="menu" aria-expanded="false" data-target="navbarTopMain">
                                            <span aria-hidden="true"></span>
                                            <span aria-hidden="true"></span>
                                            <span aria-hidden="true"></span>
                                        </a>
                                    </div>

                                    <div class="navbar-menu" id="navbarTopMain">
                                        <div class="navbar-end">

                                            <div class="navbar-item has-dropdown is-hoverable">
                                                <a class="navbar-link">
                                                    TODO List
                                                </a>
                                                <div class="navbar-dropdown is-boxed">
                                                    <NavLink className="navbar-item" to="/todolist"> List Your TODO </NavLink>
                                                    {displayLogInOrLogoutButton}    
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                            </nav>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

export default withRouter(Header)
