import React, {Component} from 'react';

class Edit extends Component {

    state = {
        state: {
            countries: [],
        },
        country: {
            name: "",
            prices: {}
        },
    }

    getCountries() {
        fetch('https://vetterlain.dk/webto/api/price/')
            .then(res => res.json())
            .then(countries => {
                this.setState({countries})
            })
    }

    getCountry(country) {
        fetch('https://vetterlain.dk/webto/api/price/' + country)
            .then(res => res.json())
            .then(country => {
                this.setState({country}, () => {
                    document.getElementById("0").value = this.state.country.prices[0];
                    document.getElementById("1").value = this.state.country.prices[1];
                    document.getElementById("2").value = this.state.country.prices[2];
                    document.getElementById("3").value = this.state.country.prices[3];
                    document.getElementById("4").value = this.state.country.prices[4];
                    document.getElementById("5").value = this.state.country.prices[5];
                })
            })

    }

    save() {
        let country = {
            name: this.state.country.name,
            prices: [
                [document.getElementById("0").value],
                [document.getElementById("1").value],
                [document.getElementById("2").value],
                [document.getElementById("3").value],
                [document.getElementById("4").value],
                [document.getElementById("5").value],
            ]
        }


        let data = JSON.stringify(country);
        fetch("http://localhost:8084/webto/api/price",
            {
                method: "PUT",
                headers: {'Accept': 'application/json', 'Content-Type': 'application/json'},
                body: data
            })
    }


    componentDidMount() {
        this.getCountries();
    }

    fillSelect() {
        let items = [];
        for (var i = 0; i < this.state.countries.length; i++) {
            items.push(<option key={i} value={this.state.countries[i].name}>{this.state.countries[i].name}</option>)
        }
        return items;
    }


    handleChange(event) {
        this.setState({[event.target.name]: event.target.value}, () => {
            this.getCountry(this.state.value);
        });

    }

    render() {
        return (
            <div className="container well">

                <select name="value" onChange={this.handleChange.bind(this)}>
                    {this.state.countries ? this.fillSelect() : "Loading"}
                </select>
                <div className="input-group">
                    <h3>Country: {this.state.country.name}</h3>
                    <h5>Fedex Economy:</h5>
                    <input className="form-control"  type="text" id="0"></input>
                    <h5>Fedex Priority:</h5>
                    <input className="form-control" type="text" id="1"></input>
                    <h5>Ups Saver:</h5>
                    <input className="form-control" type="text" id="2"></input>
                    <h5>Ups Express:</h5>
                    <input className="form-control" type="text" id="3"></input>
                    <h5>Ups Standard:</h5>
                    <input className="form-control" type="text" id="4"></input>
                    <h5>Gls:</h5>
                    <input className="form-control" type="text" id="5"></input>
                </div>
                <button onClick={this.save.bind(this)}>Save</button>
            </div>
        );
    }
}

export default Edit;