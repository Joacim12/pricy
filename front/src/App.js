import React, {Component} from 'react';

import {BrowserRouter as Router, Route} from 'react-router-dom';
import './App.css';

import Home from './components/Home'
import Navbar from "./components/Navbar";

const Test = (props) => (
    <div className="container"><well><h1>empty</h1></well></div>
)

class App extends Component {

    render() {
        return (
            <div className="App">
                <Router>
                    <div >
                        <Navbar/>
                        <Route exact path="/webto/" render={props => <Home/>}/>
                        <Route path="/webto/test/" component={Test}/>
                    </div>
                </Router>
            </div>
        );
    }
}

export default App;
