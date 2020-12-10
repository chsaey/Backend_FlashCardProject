import React, { Component } from 'react';
import {Link} from 'react-router-dom';

class HomeComponent extends Component {
    render() {
        return (
            <div className="container">
                    <p>Are you ready to study Hard?</p>
                    <p>Let's Go!!!</p>
                    <br/>
                    <button className="bt first">
                        <Link to="/SignIn">Click Me!</Link>
                    </button>                   
            </div>
        )
    }
}

export default HomeComponent;