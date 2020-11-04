import React, { Component } from 'react';
import axios from 'axios';
import { Redirect, NavLink } from "react-router-dom";
import { loginDetails } from '../utils/Common';

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userId: '',
            userName:'',
            password:'',
            errorMessage: ''
        }
        this.url = 'http://localhost:8080/v1/api/auth/signin';
    }

    handleUserNameChange = event => {
        this.setState({ userName: event.target.value });
    };
    handlePasswordChange = event => {
        this.setState({ password: event.target.value });
        
    };
    handleSubmit = event => {
        event.preventDefault();
        const userName = this.state.userName;
        const password = this.state.password;
        let bodyFormData = new FormData();
        bodyFormData.set('userName', userName);
        bodyFormData.set('password', password);
        axios.post(this.url, bodyFormData)
        .then(result => {
            if (result.status === 200) {
                loginDetails(result.data.token, result.data.userId, result.data.userName);
                this.setState({redirect: true})
            }
        })
        .catch(error => {
            console.log(error);
            if(error.response.status === 401) {
                this.setState({errorMessage: "User/Password Invalid."});
            }
        });

    };

    renderRedirect = () => {
         if (this.state.redirect === true) {
             return <Redirect to="/home" />
         }
    };

    render() {
        return (
            <div>
                <div id="app">
                    <section class="hero is-fullheight is-light" >
                        <div class="hero-head">
                            <div class="hero is-fullheight">
                                <div class="hero-body">
                                    <div class="container has-text-centered">
                                        <div class="column is-4 is-offset-4">
                                            <h3 class="title has-text-gray">Login</h3>
                                            <hr class="login-hr"/>
                                            <div class="box">
                                                <div class="title has-text-grey is-5">Please enter your user name and password.</div>
                                                <form onSubmit={this.handleSubmit}>
                                                    <div class="field">
                                                        <p class="control has-icons-left has-icons-right">
                                                            <input class="input" type="text" id="inputUserName" placeholder="User Name" name="userName" onChange={this.handleUserNameChange} autoFocus required="required" />
                                                            <span class="icon is-small is-left">
                                                            <i class="fa fa-envelope"></i>
                                                            </span>
                                                            <span class="icon is-small is-right">
                                                            <i class="fa fa-check"></i>
                                                            </span>
                                                        </p>
                                                    </div>
                                                    <div class="field">
                                                        <div class="control">
                                                            <p class="control has-icons-left">
                                                            <input class="input" type="password" id="inputPassword" name="password" placeholder="Password" onChange={this.handlePasswordChange} required="required"/>
                                                                <span class="icon is-small is-left">
                                                                <i class="fa fa-lock"></i>
                                                                </span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="field">
                                                        <p class="control">
                                                            <button class="button is-success is-medium is-fullwidth" type="Submit"> Login </button>
                                                        </p>
                                                    </div>
                                                    { this.state.errorMessage && <h3 className="error"> { this.state.errorMessage } </h3> }
                                                </form>
                                                {this.renderRedirect()}
                                            </div>
                                            <p class="has-text-grey">
                                                <NavLink to="/signup">
                                                    Sign Up
                                                </NavLink>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </section>
                </div>      
            </div>
        )
    }
}
export default Login