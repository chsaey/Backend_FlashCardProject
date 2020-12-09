import React, { Component } from 'react';

class SignInComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('Your data was submitted: ' + this.state.value);
        event.preventDefault();
    }

    render() {
        return (
            <div className="container">
                <br />
                <br />
                <div className="jumbotron" style={{backgroundColor:"Black"}}>
                    <form>
                        <h2 style={{color:"Green"}}>Add your info...</h2>
                        <br />
                        <h6 style={{color:"white", textAlign:"left"}}>First Name:</h6>
                        <input type="text" firstName="firstName" style={{float:"left"}} />
                        <br />
                        <br />
                        <h6 style={{color:"white", textAlign:"left"}}>Last Name:</h6>
                        <input type="text" lastName="lastName" style={{float:"left"}} />
                        <br />
                        <br />
                        <h6 style={{color:"white", textAlign:"left"}}>Username:</h6>
                        <input type="text" username="username" style={{float:"left"}} />
                        <br />
                        <br />
                        <h6 style={{color:"white", textAlign:"left"}}>Password:</h6>
                        <input type="text" password="password" style={{float:"left"}} />
                        <br />
                        <br />
                        <button type="submit" style={{float:"left"}}>Submit</button>
                    </form>    
                </div>
            </div>
        )
    }
}

export default SignInComponent;