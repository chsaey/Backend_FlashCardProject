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
            <div className="container">
                <br />
                <br />
                <div className="jumbotron" style={{backgroundColor:"Black"}}>
                    <h2 style={{color:"Green"}}>Add your info...</h2>
                    <br />
                    <Formik
                        initialValues={{id, firstName, lastName, username, password}}
                        onSubmit={this.onSubmit}
                        enableReinitialize={true}
                    >
                        {
                            (props) => (
                                <Form>
                                    <fieldset className="form-group">
                                        <label style={{color:"white"}}>ID:</label>
                                        <Field className="form-control" type="text" name="id" disabled />
                                    </fieldset>
                                    <fieldset>
                                        <label style={{color:"white"}}>First Name:</label>
                                        <Field className="form-control" type="text" name="firstName" />
                                    </fieldset>
                                    <fieldset>
                                        <label style={{color:"white"}}>Last Name:</label>
                                        <Field className="form-control" type="text" name="lastName" />
                                    </fieldset>
                                    <fieldset>
                                        <label style={{color:"white"}}>Username:</label>
                                        <Field className="form-control" type="text" name="username" />
                                    </fieldset>
                                    <fieldset>
                                        <label style={{color:"white"}}>Password:</label>
                                        <Field className="form-control" type="text" name="password" />
                                    </fieldset>
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