import React, { Component } from 'react';

export default function ListCustomersView(props) {
        let customerRows = props.customers.map((customer,index) =>
            <tr key={customer.id}>
                <td>{index+1}</td>
                <td>{customer.name}</td>
                <td>{customer.fnumber}</td>
                <td>
                    <input type="button" value="Details"
                           onClick={props.detailsCustomerClicked.bind(this, customer.id)} />
                </td>
            </tr>
        );
        return (
            <div className="customers-view">
                <h1>Customers</h1>
                <table className="customers-table">
                    <thead>
                    <tr>
                        <th>Count</th>
                        <th>Name</th>
                        <th>Faculty Number</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {customerRows}
                    </tbody>
                </table>
            </div>
        );
}
