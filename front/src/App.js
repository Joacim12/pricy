import React, {Component} from 'react';

import {BrowserRouter as Router, Route} from 'react-router-dom';
import './App.css';

import Home from './components/Home'
import Navbar from "./components/Navbar";
import Edit from './components/Edit'


class App extends Component {

    render() {
        return (
            <div className="App">
                <Router>
                    <div >
                        <Navbar/>
                        <Route exact path="/webto/" render={props => <Home/>}/>
                        <Route path="/webto/test/" component={Edit}/>
                    </div>
                </Router>
            </div>
        );
    }
}

export default App;
