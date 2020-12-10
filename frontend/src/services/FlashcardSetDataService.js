import axios from 'axios';

class FlashcardSetDataService {

    retrieveAllFlashcardSets() {
        return axios.get(`http://localhost:8080/retrieveAllFlashcardSets`)
    }

    deleteCard(id) {
        return axios.delete(`http://localhost:8080/deleteFlashcard/${id}`)
    }

    updateCard(cardSet) {
        return axios.put(`http://localhost:8080/updateFlashcardSet`, cardSet)
    }

    createCard(cardSet) {
        return axios.post(`http://localhost:8080/addFlashcardSet`, cardSet)
    }
}

export default new FlashcardSetDataService();