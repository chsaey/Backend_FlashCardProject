import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HeaderComponent from '../headerFooter/HeaderComponent';
import FooterComponent from '../headerFooter/FooterComponent';
import HomeComponent from './HomeComponent';
import SignInComponent from './SignInComponent';
import CreateNewUserComponent from './CreateNewUserComponent';

class RouterComponent extends Component {
    render() {
        return (
            <div>
                <Router>
                    <HeaderComponent />
                        <Switch>
                            <Route exact path="/"><HomeComponent /></Route>
                            <Route path="/SignIn"><SignInComponent /></Route>
                            <Route path="/CreateNewUser/:id" component={CreateNewUserComponent} />
                        </Switch>
                    <FooterComponent />
                </Router>
            </div>
        )
    }
}

export default RouterComponent