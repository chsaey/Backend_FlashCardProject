import React, { Component } from 'react';
import { Formik, Form, Field } from 'formik';
import UserDataService from '../../services/UserDataService';


class CreateNewUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            id: this.props.match.params.id,
            firstName: '',
            lastName: '',
            userName: '',
            password: ''
        }
        this.onSubmit = this.onSubmit.bind(this)
    }

    onSubmit(values) {
        let user = {
            userName: values.userName,
            password: values.password
        }

        UserDataService.createUser(user)
        .then(() => this.props.history.push('/SignIn'))
    }

    render() {
        let {id, firstName, lastName, userName, password} = this.state
        return (
            <div className="base-container">
                <br/>
                <div className="header">Register</div>
                <div className="content">
                <div className="image">
                    <img src="https://images-na.ssl-images-amazon.com/images/I/61CfipGxS-L._AC_SL1001_.jpg" alt="new"/>
                </div>
                    
                    <Formik
                        initialValues={{id, firstName, lastName, userName, password}}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form className="form">
                                    {/* <fieldset className="form-group">
                                        <label >ID:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset> */}
                                    <fieldset className="form-group">
                                        <label>Username</label>
                                        <Field className="form-control" type="text" name="userName" placeholder="userName" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Password</label>
                                        <Field className="form-control" type="text" name="password" placeholder="password" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <button className="btn" type="submit">Register</button>
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

export default CreateNewUserComponent;