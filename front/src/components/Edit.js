import React, {Component} from 'react';

class Edit extends Component {

    state = {
        state: {
            countries: [],
            value: "test"
        },
        country: {
            name: "",
            prices: {}
        },
        a0: ""
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
                this.setState({country},()=>{
                    document.getElementById("name").value = this.state.country.name;
                    document.getElementById("0").value = this.state.country.prices[0];
                    document.getElementById("1").value = this.state.country.prices[1];
                    document.getElementById("2").value = this.state.country.prices[2];
                    document.getElementById("3").value = this.state.country.prices[3];
                    document.getElementById("4").value = this.state.country.prices[4];
                    document.getElementById("5").value = this.state.country.prices[5];
                })
            })

    }

    save(){



        fetch("http://localhost:8084/webto/api/price",
            {
                method: "PUT",
                body: {name:"hej"}
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
        this.setState({value: event.target.value}, () => {
            this.getCountry(this.state.value);
        });

    }



    render() {
        return (
            <div className="container well">

                <select value={this.state.value} onChange={this.handleChange.bind(this)}>
                    {this.state.countries ? this.fillSelect() : ""}
                </select>
                <div className="input-group">
                    <h5>Name:</h5>
                    <input className="form-control"type="text" id="name"></input>
                    <h5>Fedex Economy:</h5>
                    <input className="form-control"type="text" id="0"></input>
                    <h5>Fedex Priority:</h5>
                    <input className="form-control"type="text" id="1"></input>
                    <h5>Ups Saver:</h5>
                    <input className="form-control"type="text" id="2"></input>
                    <h5>Ups Express:</h5>
                    <input className="form-control"type="text" id="3"></input>
                    <h5>Ups Standard:</h5>
                    <input className="form-control"type="text" id="4"></input>
                    <h5>Gls:</h5>
                    <input className="form-control"type="text" id="5"></input>
                </div>
                <button onClick={this.save}>Save</button>
            </div>
        );
    }
}

export default Edit;