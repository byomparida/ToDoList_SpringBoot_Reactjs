import React, { Component } from 'react';
import { NavLink, Redirect } from "react-router-dom";
import { isLogin } from '../utils/Common';

class Home extends Component {

    constructor(props) {
        super(props)
        this.state = {
            listSpaces: [],
            userId: localStorage.getItem('USER_ID'),
            ifAccountAvailable: localStorage.getItem('IF_ACCOUNT_EXISTS'),
            accountsFromStorage: JSON.parse(localStorage.getItem('ACCOUNTS')),
            accountType: this.props.match.params.accountType,
            ifSpaceAvailable: ''
        };
    }




    renderRedirect = () => {
         if (this.state.redirect === true) {
             return <Redirect to="/home" />
         }
    };

    render() {
        return (
            <div>
                <div id="app">

                    <section class="hero main_hero is-fullheight">    
                        <div class="hero-head">
                            <section class="hero is-medium has-text-centered" >
                                <div class="hero-body  p-b-30 ">
                                    <div class="container">
                                        <div class="columns is-centered">
                                            <div class="column is-7">
                                                <div class="search-form">
                                                    <form>
                                                        <div class="field has-addons has-shadow-field">
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                
                            </section>           
                        </div>
                    </section>
                </div>      
            </div>
        )
    }
}
export default Home