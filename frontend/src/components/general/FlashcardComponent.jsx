import React, { Component } from 'react';
import FlashcardDataService from '../../services/FlashcardDataService';

class FlashcardComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: []
        }
        this.refreshCardRegistry = this.refreshCardRegistry.bind(this)
        this.deleteCardClicked = this.deleteCardClicked.bind(this)
        this.updateCardClicked = this.updateCardClicked.bind(this)
        this.addCardClicked = this.addCardClicked.bind(this)
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

    updateCardClicked(id) {
        console.log('Update Card Clicked')
        this.props.history.push(`/card/${id}`)
    }

    addCardClicked() {
        console.log('Add Card Clicked')
        this.props.history.push(`/theCard/-1`)
    }

    render() {
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
                               <th>Set ID</th>
                           </tr>
                       </thead>
                       <tbody>
                           {
                               this.state.cards.map (
                                   cards => 
                                   <tr style={{textAlign: "center"}} key={cards.id}>
                                       <td>{cards.id}</td>
                                       <td>{cards.question}</td>
                                       <td>{cards.answer}</td>
                                       <td>{cards.setID}</td>
                                       <td><button className="btn btn-warning" onClick={() => this.deleteCardClicked(cards.id, cards.question)}>Delete</button></td>
                                       <td><button className="btn btn-success" onClick={() => this.updateCardClicked(cards.id)}>Update</button></td>
                                   </tr>
                               )
                           }
                       </tbody>
                   </table>
                   <div className="row">
                       <br/>
                       <button className="btn btn-success" onClick={this.addLindsayClicked}>Add Lindsay</button>
                   </div>
               </div>
           </div>
        )
    }
}

export default FlashcardComponent;