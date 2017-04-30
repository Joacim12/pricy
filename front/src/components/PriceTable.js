import React, {Component} from 'react';

class Table extends Component {

    render() {
        return (
            <div >
                <table className="table table-striped table-hover table-bordered ">
                    <tbody>
                    <tr>
                        <th>Weight</th>
                        <th>Fedex Economy</th>
                        <th>Fedex Priority</th>
                        <th>GLS</th>
                        <th>UPS Express</th>
                        <th>UPS Saver</th>
                        <th>UPS Standard</th>
                        <th>Cheapest</th>
                    </tr>
                    <tr className="success">
                        <td>{this.props.price.weight + "kg"}</td>
                        <td>{this.props.price.fedexE}</td>
                        <td>{this.props.price.fedexP}</td>
                        <td>{this.props.price.glsS}</td>
                        <td>{this.props.price.upsE}</td>
                        <td>{this.props.price.upsSA}</td>
                        <td>{this.props.price.upsSt}</td>
                        <td>{this.props.price.cheapest}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

export default Table;
