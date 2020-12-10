import React, { Component} from 'react';
import { Redirect } from "react-router-dom";
import UserDataService from '../../services/UserDataService';
import { Formik, Form, Field } from 'formik';
class SignInComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            userName: '',
            password: '',
            redirect: false,
            id:0
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

                response.data.forEach((element) => {
                    if(element.userName === user.username && element.password === user.password){
                        this.setState({
                            id: element.id,
                            redirect : true
                          });       
     

                    }
            
                });
            }
        )        
    }

    render() {
        let {userName, password} = this.state
        if (this.state.redirect) {
            //return <Redirect to={"/FlashcardSets"} />  
            
            return <Redirect
            to={{
            pathname: "/FlashcardSets",
            state: { id: this.state.id }
          }}
        />
        }

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