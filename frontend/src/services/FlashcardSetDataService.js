import axios from 'axios';

class FlashcardSetDataService {

    retrieveAllFlashcardSets() {
        return axios.get(`http://localhost:8080/retrieveAllFlashcardSets`)
    }

    deleteFlashcardSet(id) {
        return axios.delete(`http://localhost:8080/deleteFlashcardSet/${id}`)
    }

    updateFlashcardSet(cardSet) {
        return axios.put(`http://localhost:8080/updateFlashcardSet`, cardSet)
    }

    createFlashcardSet(cardSet) {
        return axios.post(`http://localhost:8080/addFlashcardSet`, cardSet)
    }
}

export default new FlashcardSetDataService();