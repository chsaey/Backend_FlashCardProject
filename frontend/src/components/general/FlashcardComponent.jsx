import React, { Component } from 'react';
import FlashcardDataService from '../../services/FlashcardDataService';
import { Redirect } from "react-router-dom";

class FlashcardComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: [],
            id: this.props.location.state.id.id,
            entity: this.props.location.state.id,
            back: false
        }

        console.log(this.state.entity)
        this.refreshCardRegistry = this.refreshCardRegistry.bind(this)
        this.deleteCardClicked = this.deleteCardClicked.bind(this)
        this.updateCardClicked = this.updateCardClicked.bind(this)
        this.addCardClicked = this.addCardClicked.bind(this)
        this.showAnswer = this.showAnswer.bind(this)
        this.backToSets = this.backToSets.bind(this)
    }

    componentDidMount() {
        this.refreshCardRegistry();
    }

    refreshCardRegistry() {
        FlashcardDataService.retrieveAllCards()
        .then(
            response => {
                this.setState({
                    cards: response.data
                })
            }
        )
    }

    deleteCardClicked(id, question) {
        console.log('Delete Card Clicked')
        FlashcardDataService.deleteCard(id)
        .then(
            response => {
                this.setState({message: `Deleted Card: ${question}`})
                alert(this.state.message)
                this.refreshCardRegistry();
            }
        )
    }

    showAnswer(answer) {
        alert(answer) 
    }


    updateCardClicked(id) {
        console.log('Update Card Clicked')
        let card = {
            id: id.id,
            question: id.question,
            answer: id.answer,
            flashcardSets: id.flashcardSets,
            setID: 0
        }
        let tempQ = prompt('Edit question')
        let tempA = prompt('Edit Answer')

        card.question = tempQ ? tempQ:id.question
        card.answer = tempA ? tempA:id.answer

 
        FlashcardDataService.updateCard(card)
        .then(
            response =>{
                this.setState({message: "udpated"})
                alert(this.state.message)
                this.refreshCardRegistry();     
            }
        )   

    }

    addCardClicked() {
        console.log('Add Card Clicked')
        let card = {
            question:"" ,
            answer:"" ,
            flashcardSets: this.state.entity,
            setID: 0
        }
        card.question = prompt('Add question')
        card.answer = prompt('Add Answer')
 
        FlashcardDataService.createCard(card)
        .then(
            response =>{
                this.setState({message: "added"})
                alert(this.state.message)
                this.refreshCardRegistry();     
            }
        )   
    }

    backToSets() {
        this.setState({back:true})

        
    }

    render() {
        console.log(this.state.back)
        if(this.state.back){
            return <Redirect
            to={{
            pathname: "/FlashcardSets",
            state: { id: this.state.entity.userID }
          }}
        />  
        }
        
        
        return (

            <div className="container">
               <h1 style={{textAlign:"center"}}>Card Registry</h1><br></br>
               <div className="jumbotron"  style={{backgroundColor: "gray", color: "white"}}>
                   <table className="table">
                       <thead>
                           <tr style={{textAlign: "center" , color: "black"}}>
                               <th>ID</th>
                               <th>Question</th>
                               <th>Answer</th>
                           </tr>
                       </thead>
                       <tbody>
                           {
                               this.state.cards.filter(set => set.flashcardSets.id === this.state.id).map (
                                   cards => 
                                   <tr style={{textAlign: "center"}} key={cards.id}>
                                       <td>{cards.id}</td>
                                       <td>{cards.question}</td>
                                       <td><button className="btn btn-success" onClick={() => this.showAnswer(cards.answer)}>Show Answer</button></td>
                                       <td>{cards.setID}</td>
                                       <td><button className="btn btn-danger" onClick={() => this.deleteCardClicked(cards.id, cards.question)}>Delete</button></td>
                                       <td><button className="btn btn-warning" onClick={() => this.updateCardClicked(cards)}>Update</button></td>
                                   </tr>
                               )
                           }
                       </tbody>
                   </table>
                   <div className="row">
                       <br/>
                       <button className="btn btn-info" style={{margin: 10}} onClick={this.addCardClicked}>Add Flashcard</button>
                       <br/>
                       <button className="btn btn-dark" style={{margin: 10}}  onClick={this.backToSets}>Back to sets</button>
                   </div>
               </div>
           </div>
        )
    }
}

export default FlashcardComponent;