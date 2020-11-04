import React from 'react';
import { BrowserRouter, Switch, Route } from "react-router-dom";
import './scss/common.scss';
import Header from "./components/Header"; 
import Footer from "./components/Footer"; 
import Login from './components/Login';
import Logout from './components/Logout';
import Registration from './components/Registration';
import CreateToDoList from './components/CreateToDoList';
import UpdateToDoList from './components/UpdateToDoList';
import Home from './components/Home';
import PrivateRoute from './utils/PrivateRoute';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Header />
        <Switch>
            <Route path="/home" component = {Home} />
            <Route path="/login" component = {Login} />
            <Route path="/logout" component = {Logout} />
            <Route path="/signup" component = {Registration} />
            <Route path="/signup" component = {CreateToDoList} />
            <PrivateRoute path="/todolist" component = {CreateToDoList} exact />
            <PrivateRoute path="/updatetodolist/:todolistId" component = {UpdateToDoList} exact />
        </Switch>
      </BrowserRouter>
      {/* <Footer /> */}
    </div>
  );
}

export default App;
