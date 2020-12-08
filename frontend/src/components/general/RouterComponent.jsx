import React, { Component } from 'react'
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import HeaderComponent from '../headerFooter/HeaderComponent'
import FooterComponent from '../headerFooter/FooterComponent'
import HomeComponent from './HomeComponent'

class RouterComponent extends Component {
    render() {
        return (
            <div>
                <Router>
                    <HeaderComponent />
                        <Switch>
                            <Route exact path="/"><HomeComponent /></Route>
                        </Switch>
                </Router>
            </div>
        )
    }
}