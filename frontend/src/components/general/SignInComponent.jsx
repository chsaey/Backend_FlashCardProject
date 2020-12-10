import React, { Component } from 'react';
import UserDataService from '../../services/UserDataService';
import { Formik, Form, Field } from 'formik';

class SignInComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userName: '',
            password: ''
        }
        this.onSubmit = this.onSubmit.bind(this)
    }

    onSubmit(values) {

        let user = {
            username: values.userName,
            password: values.password
        }

        UserDataService.retrieveAllUser()
        .then(
            (response) => {
                var obj = JSON.parse(response);
                const json = JSON.stringify(response);
                console.log(json)
                for(var prop in obj) {
                    var item = obj[prop];
                    console.log(prop);
                }
                
            }
        )

        
    }

    render() {
        let {userName, password} = this.state
        return (
            <div className="base-container">
                <br/>
                <div className="header">Sign In</div>
                <div className="content">
                <div className="image">
                    <img src="https://images-na.ssl-images-amazon.com/images/I/61CfipGxS-L._AC_SL1001_.jpg" alt="new"/>
                </div>
                    
                    <Formik
                        initialValues={{userName, password}}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form className="form">
                                    <fieldset className="form-group">
                                        <label>Username</label>
                                        <Field className="form-control" type="text" name="userName" placeholder="username" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Password</label>
                                        <Field className="form-control" type="text" name="password" placeholder="password" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <button className="btn" type="submit">Sign In</button>
                                    </fieldset>
                                    <br/>
                                    <br/>
                                </Form>
                            )
                        }
                    </Formik>
                </div>
            </div>
        )
    }
}

export default SignInComponent;