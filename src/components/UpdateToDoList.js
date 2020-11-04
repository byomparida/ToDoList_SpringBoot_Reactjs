import React, { Component } from 'react';
import { Link, Redirect } from "react-router-dom";
import { isLogin} from '../utils/Common';
import ToDoListService from '../services/ToDoListService'


export default class UpdateToDoList extends Component {
    constructor(props) {
        super(props)
        this.state = {
            todolistName: '',
            todolistType: '',
            userId: localStorage.getItem('USER_ID'),
            ifAccountAvailable: localStorage.getItem('IF_ACCOUNT_EXISTS'),
            redirect: false,
            accountsFromStorage: JSON.parse(localStorage.getItem('ACCOUNTS')),
            toDoList: []
        };
    }

    handleTodoListNameChange = event => {
        this.setState({ todolistName: event.target.value });
    };
    handleTodoListTypeChange = event => {
        this.setState({ todolistType: event.target.value });
    };

    handleSubmit = event => {
        event.preventDefault();
        const todolistName = this.state.todolistName;
        const todolistType = this.state.todolistType;
        const userId = this.state.userId;
        const todolistId = this.props.match.params.todolistId;
        let bodyFormData = new FormData();
        bodyFormData.set('todolistName', todolistName);
        bodyFormData.set('todolistType', todolistType);
        bodyFormData.set('userId', userId);
        bodyFormData.set('todolistId', todolistId);

        let todolist = {todolistName: todolistName, todolistType: todolistType, userId: userId, todolistId: todolistId};
        ToDoListService.updateToDoList(todolist, todolistId).then( response => {
            this.setState({toDoList: response.data});
            if(response.status === 200) {
                this.props.history.push('/todolist');
            }
        });
    };

    componentDidMount() {
        this.getToDoListByUserId(this.state.userId);
        this.getToDoListById(this.state.todoListId);
    }

    renderRedirect = () => {
         if (this.state.redirect === true) {
            return <Redirect to="/todolist" />
         }
    };

    getToDoListByUserId(userId) {
        ToDoListService.getToDoListByUserId(userId).then(response => {
            if(response.status === 200) {
                this.setState({toDoList: response.data});
            } 
        }).catch(error => {
            console.log(error);
        });
    }

    getToDoListById(todoListId) {
        ToDoListService.getToDoListById(todoListId).then(response => {
            if(response.status === 200) {
                this.setState({toDoList: response.data});
            } 
        }).catch(error => {
            console.log(error);
        });
    }

    render() {
        if(isLogin()) {
            return(
                <div>
                    <div id="app">
                        <section class="hero is-fullheight is-light" >
                            <div class="hero-head">
                                <div class="tile is-ancestor">
                                    <div class="tile is-parent">
                                        <div class="tile is-child box">
                                            <div class="column is-4 is-offset-4">
                                                <p class="title">Create To Do List</p>
                                                <form onSubmit={this.handleSubmit}>
                                                    <div class="field">
                                                        <label class="label">TODO List Type</label>
                                                        <div class="control">
                                                            <input class="input" type="text" id="inputTodoListName" placeholder="TODO List Name" name="todoListName" onChange={this.handleTodoListNameChange} autoFocus required="required" />
                                                        </div>
                                                    </div>
                                                    <div class="field">
                                                        <label class="label">TODO List Name</label>
                                                        <div class="control">
                                                            <input class="input" type="text" id="inputTodoListType" placeholder="TODO List Type" name="todoListType" onChange={this.handleTodoListTypeChange} required="required" />
                                                        </div>
                                                    </div>
                                                    <br/><br/>
                                                    <div class="control">
                                                        <button className="button is-primary" type="submit"> Submit </button>&nbsp;
                                                        <Link to="/todolist" class="button is-primary">Back</Link>
                                                    </div>
                                                </form>
                                                {this.renderRedirect()}
                                            </div>

                                        </div>
                                    </div>
                                </div> 
                            </div>  
                        </section>
                    </div>          
                </div>
            );
        } else {
            return(
                <div>
                    { <Redirect to="/login" /> }
                </div>
            );
        }
    }
}