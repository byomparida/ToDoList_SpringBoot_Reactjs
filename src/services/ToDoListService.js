import axios from 'axios';

const TODOLIST_API_BASE_URL = "http://localhost:8080/v1/todolist";

class ToDoListService {

    getTodoList() {
        return axios.get(TODOLIST_API_BASE_URL);
    }

    createToDoList(todolist) {
        return axios.post(TODOLIST_API_BASE_URL, todolist);
    }

    getToDoListById(todoListId) {
        return axios.get(TODOLIST_API_BASE_URL + '/' + todoListId);
    }

    getToDoListByUserId(userId) {
        return axios.get(TODOLIST_API_BASE_URL + '/user/' + userId);
    }

    updateToDoList(todolist, todoListId) {
        return axios.put(TODOLIST_API_BASE_URL + '/' + todoListId, todolist);
    }

    deleteTodoList(todoListId){
        return axios.delete(TODOLIST_API_BASE_URL + '/' + todoListId);
    }
}

export default new ToDoListService()