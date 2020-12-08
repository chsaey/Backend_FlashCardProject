import axios from 'axios'

class UserDataService {

    retrieveAllUser() {
        return axios.get('http://localhost:8080/retrieveAllUsers')
    }

    deleteUser(id) {
        return axios.delete('http://localhost:8080/deleteUser/${id}')
    }

    updateUser(user) {
        return axios.put('http://localhost:8080/updateUser/', user)
    }

    createUser(user) {
        return axios.post('http://localhost:8080/addUser/', user)
    }
}

export default new UserDataService();