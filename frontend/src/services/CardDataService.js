import axios from 'axios';

class CardDataService {

    retrieveAllCards() {
        return axios.get(`http://localhost:8080/retrieveCards`)
    }

    deleteCard(id) {
        return axios.delete(`http://localhost:8080/deleteCards/${id}`)
    }

    updateCard(card) {
        return axios.put(`http://localhost:8080/updateCard`, card)
    }

    createCard(card) {
        return axios.post(`http://localhost:8080/addCard`, card)
    }
}

export default new CardDataService();