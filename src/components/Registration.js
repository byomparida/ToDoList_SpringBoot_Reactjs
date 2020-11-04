import React, { Component } from 'react'
import axios from 'axios';
import { Redirect} from "react-router-dom";

class Registration extends Component {
    constructor(props){
        super(props);
        this.state = {
          userName:'',
          password:''
        }
        this.url = 'http://localhost:8080/v1/api/auth/signup';
    }

    handleUserNameChange = event => {
        this.setState({ userName: event.target.value });
    };
    handlePasswordChange = event => {
        this.setState({ password: event.target.value });
    };

    handleSubmit = event => {
        event.preventDefault();
        this.setState({ isLoading: true });
        const userName = this.state.userName;
        const password = this.state.password;
        let bodyFormData = new FormData();
        bodyFormData.set('userName', userName);
        bodyFormData.set('password', password);
        axios.post(this.url, bodyFormData)
        .then(result => {
            if (result.status) {
                this.setState({redirect: true, 
                    isLoading: false,
                    accounts: result.data
                })
            }
        })
        .catch(error => {
            console.log(error);
        });
    };

    renderRedirect = () => {
        console.log(this.state.redirect);
         if (this.state.redirect === true) {
             return <Redirect to="/login" />
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
                                        <div class="column is-8 is-offset-2">
                                            <h3 class="title has-text-gray">Sign Up</h3>
                                            <div class="box">
                                                <div class="title has-text-grey is-5"></div>
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
                                                            <button class="button is-success is-large is-fullwidth" type="submit">Register</button>
                                                        </p>
                                                    </div>
                                                </form>
                                                {this.renderRedirect()}
                                            </div>
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

export default Registration
