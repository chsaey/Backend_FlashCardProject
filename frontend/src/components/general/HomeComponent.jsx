import React, { Component } from 'react'
import {Link} from 'react-router-dom'

class HomeComponent extends Component {
    render() {
        return (
            <div className="container">
                <br />
                <br />
                <div className="jumbotron" style={{textAlign:"center", backgroundColor:"Black"}}>
                    <h1 style={{color:"Green"}}>Welcome to the FlashCards app!</h1>
                    <br />
                    <h2 style={{color:"white"}}>Please sign in <Link to="/SignIn">here</Link>.</h2>
                </div>
            </div>
        )
    }
}

export default HomeComponent