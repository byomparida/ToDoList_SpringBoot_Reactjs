import React, { Component } from 'react';
import { Redirect } from "react-router-dom";
import { isLogin} from '../utils/Common';
import ToDoListService from '../services/ToDoListService'


export default class CreateToDoList extends Component {
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
        this.removeTodolist= this.removeTodolist.bind(this);
        this.updateTodolist= this.updateTodolist.bind(this);
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
        let bodyFormData = new FormData();
        bodyFormData.set('todolistName', todolistName);
        bodyFormData.set('todolistType', todolistType);
        bodyFormData.set('userId', userId);
        ToDoListService.createToDoList(bodyFormData).then((response) => {
            if (response.status) {
                this.getToDoListByUserId(userId);
                if(response.status === 200) {
                    this.setState({toDoList: response.data});
                }
                this.setState({redirect: true});
            }
        }).catch(error => {
            console.log(error);
        });
    };

    componentDidMount() {
        this.getToDoListByUserId(this.state.userId);
    }

    updateTodolist(todolistId){
        this.props.history.push('/updatetodolist/'+todolistId);
    }

    removeTodolist(todolistId){
        ToDoListService.deleteTodoList(todolistId).then(response => {
            if(response.status === 200) {
                this.getToDoListByUserId(this.state.userId);
            } 
        }).catch(error => {
            console.log(error);
        });
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
                                                        <button className="button is-link is-light" type="submit"> Submit </button>
                                                    </div>
                                                </form>
                                                {this.renderRedirect()}
                                            </div>


                                            <div class="container column is-11.5">
                                        <p class="panel-heading">
                                            TODO List Details
                                        </p>
                                        <table class="table is-bordered is-striped is-narrow is-hoverable is-fullwidth">
                                            <thead>
                                                <th>TODO List Name</th>
                                                <th>TODO List Type</th>
                                                <th>Date Created</th>
                                                <th>Date Updated</th>
                                                <th>Actions</th>
                                            </thead>
                                            <tbody>
                                            {
                                                this.state.toDoList.map(
                                                    todolist => 
                                                    <tr key = {todolist.todolistId}>
                                                            <td> {todolist.todolistName} </td>
                                                            <td> {todolist.todolistType} </td>   
                                                            <td> {todolist.createdTime}</td>
                                                            <td> {todolist.updatedTime}</td>
                                                            
                                                            <td>
                                                                <button onClick={ () => this.updateTodolist(todolist.todolistId) } className="button is-primary is-small"> Update </button> &nbsp;
                                                                <button onClick={ () => this.removeTodolist(todolist.todolistId) } className="button is-primary is-small"> Remove </button>
                                                            </td>
                                                    </tr>
                                                )
                                            }
                                            </tbody>
                                        </table>
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