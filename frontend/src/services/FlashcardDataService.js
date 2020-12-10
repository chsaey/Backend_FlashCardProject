import axios from 'axios';

class FlashcardDataService {

    retrieveAllCards() {
        return axios.get(`http://localhost:8080/retrieveAllFlashcards`)
    }

    deleteCard(id) {
        return axios.delete(`http://localhost:8080/deleteFlashcard/${id}`)
    }

    updateCard(card) {
        return axios.put(`http://localhost:8080/updateFlashcard`, card)
    }

    createCard(card) {
        return axios.post(`http://localhost:8080/addFlashcard`, card)
    }
}

export default new FlashcardDataService();