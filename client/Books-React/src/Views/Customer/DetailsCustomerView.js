import React, { Component } from 'react';

export default class DetailsCustomerView extends Component {
    render() {
        return (
            <div className="details-customer-form">
                <h1>Details for Customer</h1>
                <label>
                    <div>Name: {this.props.name}</div>
                    <div>Faculty Number: {this.props.fnumber}</div>
                </label>
            </div>
        );
    }

}
