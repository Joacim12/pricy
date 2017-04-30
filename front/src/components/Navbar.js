import React, {Component} from 'react';
import logo from '../logo.svg';
import {NavLink} from 'react-router-dom';

class Navbar extends Component {
    render() {
        return (
            <nav className="navbar navbar-default navbar-fixed-top">
                <div className="container">

                    <div className="navbar-header">
                        <NavLink exact activeClassName="isActive" to="/webto/">
                            <span className="navbar-brand">Intelli Cargo<img src={logo} className="App-logo" alt="logo"/></span>
                        </NavLink>
                        <ul className="nav navbar-nav">
                            <li><NavLink exact activeClassName="isActive" to="/webto/">Search</NavLink></li>
                            <li><NavLink activeClassName="active" to="/webto/test/">Edit prices</NavLink></li>
                        </ul>
                    </div>
                </div>
            </nav>
        );
    }
}

export default Navbar;