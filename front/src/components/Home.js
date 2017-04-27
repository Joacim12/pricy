import React, {Component} from 'react';
import SearchField from '../components/SearchField'
import PriceTable from '../components/PriceTable'

class Home extends Component {
    state = {
        price: [],
        kg: "", country: "", length: 0,
        width: 0, height: 0, weight: 0,

    }

    showResult = false;

    searchData(kg, country) {
        fetch('https://vetterlain.dk/webtwo/api/price/' + kg + '/' + country + '')
            .then(res => res.json())
            .then(price => {
                this.setState({price}, console.log())
            })
    }



    calculateVolWeight() {
        var vol = (this.state.length *
            this.state.height *
            this.state.width) / 5000;
        if (vol > this.state.weight) {
            this.setState({
                kg: vol
            })
        } else {
            this.setState({
                kg: this.state.weight
            })
        }
    }

    handleSearch() {
        if (this.state.country.length > 3) {
        // Rounds kg up to nearest half + replaces ',' with '.'
        this.searchData((Math.round((this.state.kg + "").replace(",", ".") * 2) / 2), this.state.country);
        this.showResult = true;
        }
    }

    handleChange(e) {
        this.setState({[e.target.name]: e.target.value}, this.calculateVolWeight);
    }

    handleChangeCountry(e) {
        this.setState({[e.target.name]: e.target.value}, this.handleSearch);
    }

    render() {
        return (
            <div className="container well">
                <h1>Price Calculator:</h1>
                <h3>Weight calculator:</h3>
                <div className="input-group">
                    <h4>Length:</h4>
                    <input type="number" name="length" className="form-control"
                           onChange={this.handleChange.bind(this) }/>
                </div>
                <div className="input-group">
                    <h4>Width:</h4>
                    <input type="number" name="width" className="form-control"
                           onChange={this.handleChange.bind(this) }/>
                </div>
                <div className="input-group">
                    <h4>Height:</h4>
                    <input type="number" name="height" className="form-control"
                           onChange={this.handleChange.bind(this) }/>
                </div>
                <div className="input-group">
                    <h4>Weight:</h4>
                    <input type="number" name="weight" className="form-control"
                           onChange={ this.handleChange.bind(this)}/>
                </div>
                <h4>{this.state.kg ? "used weight: " + this.state.kg + " kg" : ""}</h4>
                {this.state.kg ? <h3>Country:</h3> : ""}
                {this.state.kg ? <SearchField onClick={this.handleChangeCountry.bind(this)}/> : ""}
                {this.showResult ? <h1>Price:</h1> : ""}
                {this.showResult ? <PriceTable price={this.state.price}/> : ""}
            </div>
        );
    }
}

export default Home;
