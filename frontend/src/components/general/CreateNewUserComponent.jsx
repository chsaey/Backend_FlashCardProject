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
            username: '',
            password: ''
        }
        this.onSubmit = this.onSubmit.bind(this)
    }

    onSubmit(values) {
        let user = {
            id: this.state.id,
            firstName: values.firstName,
            lastName: values.lastName,
            username: values.username,
            password: values.password
        }

        UserDataService.updateUser(user)
        .then(() => this.props.history.push('/UserRegistry'))
    }

    render() {
        let {id, firstName, lastName, username, password} = this.state
        return (
            <div className="base-container">
                <br/>
                <div className="header">Register</div>
                <div className="content">
                <div className="image">
                    <img src="https://images-na.ssl-images-amazon.com/images/I/61CfipGxS-L._AC_SL1001_.jpg" alt="new"/>
                </div>
                    
                    <Formik
                        initialValues={{id, firstName, lastName, username, password}}
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
                                        <label>First Name</label>
                                        <Field className="form-control" type="text" name="firstName" placeholder="firstname" />
                                    </fieldset >
                                    <fieldset className="form-group">
                                        <label>Last Name</label>
                                        <Field className="form-control" type="text" name="lastName" placeholder="lastname" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Username</label>
                                        <Field className="form-control" type="text" name="username" placeholder="username" />
                                    </fieldset>
                                    <fieldset className="form-group">
                                        <label>Password</label>
                                        <Field className="form-control" type="text" name="password" placeholder="password" />
                                    </fieldset>
                                    <div className="footer1">
                                        <button className="btn" type="submit">Register</button>
                                    </div>
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