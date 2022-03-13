import React, { Component } from 'react';

export default function ListOrdersView(props) {
        let status = props.statusOrders;
        let ordersRows = props.orders.map((order,index) =>
            <tr key={order.id}>
                <td>{index+1}</td>
                <td>{order.status}</td>
                <td>{order.book.title}</td>
                {getActions(order)}
            </tr>
        );
    function getActions(order) {
        if (order.status=="PENDING")
            return (
                <td>
                    <input type="button" value="Return"
                           onClick={props.returnBookClicked.bind(this, order.id)} />
                </td>
            );
        else
            return <td></td>;
    }
        return (
            <div className="books-view">
                <h1>{status} Orders</h1>
                <table className="books-table">
                    <thead>
                    <tr>
                        <th>Count</th>
                        <th>Status</th>
                        <th>Book Title</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {ordersRows}
                    </tbody>
                </table>
            </div>
        );
}
