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
            setID:0,
            updatedName:""
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

    const enteredName = prompt('Rename set')
    id.name = enteredName    
    FlashcardSetDataService.updateFlashcardSet(id)
    .then(
        response =>{
            this.setState({message: "udpated"})
            alert(this.state.message)
            this.refreshFlashcardSetRegistry();
        }
    )   
    }

    addFlashcardSetClicked() {
        let set = {
            name: "",
            userID: this.state.id
        }

        set.name = prompt('Add a new set')  

        FlashcardSetDataService.createFlashcardSet(set)
        .then(
            response =>{
                this.setState({message: "add"})
                alert(this.state.message)
                this.refreshFlashcardSetRegistry();
            }
        )         
    }

    
    studyFlashcardSetClicked(id) {
        this.setState({
            setID:id,
            renderCards: true
        })          

    }

    render() {
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

                            </tr>
                        </thead>
                        <tbody>
                            {                              
                                this.state.flashcardSets.filter(elements => elements.userID === this.state.id).map(
                                    flashcardSets =>
                                        <tr style={{ textAlign: "center" }} key={flashcardSets.id}>
                                            <td>{flashcardSets.id}</td>
                                            <td>{flashcardSets.name}</td>
                                            <td><button className="btn btn-success" onClick={() => this.studyFlashcardSetClicked(flashcardSets)}>Study</button></td>
                                            <td><button className="btn btn-danger" onClick={() => this.deleteFlashcardSetClicked(flashcardSets.id, flashcardSets.name)}>Delete</button></td>
                                            <td><button className="btn btn-warning" onClick={() => this.updateFlashcardSetClicked(flashcardSets)}>Update</button></td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                    <div className="row">
                        <br />
                        <button className="btn btn-info" onClick={this.addFlashcardSetClicked}>Add Flashcard Set</button>
                    </div>
                </div>
            </div>
        )
    }
}

export default FlashcardSetComponent;