import React, { Component } from 'react';
import FlashcardSetDataService from '../../services/FlashcardSetDataService';
import { Redirect } from "react-router-dom";

class FlashcardSetComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            flashcardSets: [],
            id:this.props.location.state.id,
            renderCards: false,
            setID:0
        }    

        this.refreshFlashcardSetRegistry = this.refreshFlashcardRegistry.bind(this)
        this.deleteFlashcardSetClicked = this.deleteFlashcardSetClicked.bind(this)
        this.updateFlashcardSetClicked = this.updateFlashcardSetClicked.bind(this)
        this.addFlashcardSetClicked = this.addFlashcardSetClicked.bind(this)
        this.studyFlashcardSetClicked = this.studyFlashcardSetClicked.bind(this)
    }

    componentDidMount() {
        this.refreshFlashcardSetRegistry();
    }

    refreshFlashcardRegistry() {
        FlashcardSetDataService.retrieveAllFlashcardSets()
            .then(
                response => {
                    this.setState({
                        flashcardSets: response.data
                    })
                }
            )
    }

    deleteFlashcardSetClicked(id) {
        console.log("Delete Flashcard Set Clicked")
        FlashcardSetDataService.deleteFlashcardSet(id)
            .then(
                response => {
                    this.setState({ message: `Deleted Set: ${id}` })
                    alert(this.state.message)
                    this.refreshFlashcardSetRegistry();
                }
            )
    }

    updateFlashcardSetClicked(id) {
        this.props.history.push(`/theFlashcardSet/`)
        
    }

    addFlashcardSetClicked(id) {
        console.log("Add Flashcard Set Clicked")
        this.props.history.push(`/theFlashcardSet/1`)
    }

    
    studyFlashcardSetClicked(id) {
        this.setState({
            setID:id,
            renderCards: true
        })           

    }

    render() {
        console.log(this.state.renderCards)
        console.log(this.state.setID)
        if(this.state.renderCards){
                //return <Redirect to={"/FlashcardSets"} />                  
                return <Redirect
                to={{
                pathname: "/cardSet",
                state: { id: this.state.setID }
              }}
            />           

        }
        return (
            <div className="container">
                <h1 style={{ textAlign: "center" }}>Flashcard Set Registry</h1><br></br>
                <div className="jumbotron" style={{ backgroundColor: "gray", color: "white" }}>
                    <table className="table">
                        <thead>
                            <tr style={{ textAlign: "center", color: "black" }}>
                                <th>ID</th>
                                <th>Flashcard Set</th>
                                <th>User ID</th>
                            </tr>
                        </thead>
                        <tbody>
                            {                              
                                this.state.flashcardSets.filter(elements => elements.userID === this.state.id).map(
                                    flashcardSets =>
                                        <tr style={{ textAlign: "center" }} key={flashcardSets.id}>
                                            <td>{flashcardSets.id}</td>
                                            <td>{flashcardSets.name}</td>
                                            <td>{flashcardSets.userID}</td>
                                            <td><button className="btn btn-success" onClick={() => this.studyFlashcardSetClicked(flashcardSets.id)}>Study</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.deleteFlashcardSetClicked(flashcardSets.id, flashcardSets.name)}>Delete</button></td>
                                            <td><button className="btn btn-success" onClick={() => this.updateFlashcardSetClicked(flashcardSets)}>Update</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <br />
                        <button className="btn btn-success" onClick={this.addFlashcardSetClicked}>Add Flashcard Set</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default FlashcardSetComponent;