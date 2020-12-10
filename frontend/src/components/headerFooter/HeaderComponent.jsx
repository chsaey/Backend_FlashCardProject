import React, { Component } from 'react'
import {Link} from 'react-router-dom'
import * as FaIcons from "react-icons/fa";
import * as RiIcons from "react-icons/ri";

class HeaderComponent extends Component {
    render() {
        return(
            <header>
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <ul className="navbar-nav">
                        <li><Link className="nav-link" to="/">
                            <FaIcons.FaHome/> Home</Link></li>
                        <li><Link className="nav-link" to="/SignIn">
                            <RiIcons.RiLoginBoxFill/> Sign In</Link></li>
                        <li><Link className="nav-link" to="/CreateNewUser/:id">
                            <FaIcons.FaUserPlus/> Create New User</Link></li>
                    </ul>
                </nav>
            </header>
        )
    }
}
export default HeaderComponent