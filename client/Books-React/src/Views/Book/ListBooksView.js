import React, { Component } from 'react';

export default function ListBooksView(props) {
        let bookRows = props.books.map((book,index) =>
            <tr key={book.id}>
                <td>{index+1}</td>
                <td>{book.title}</td>
                <td>{book.author}</td>
                <td>{book.availableItems+book.orderedItems}</td>
                <td>{book.availableItems}</td>
                <td>{book.description}</td>
                <td>
                    <input type="button" value="Order"
                           onClick={props.orderBookClicked.bind(this, book.id)} />
                </td>
            </tr>
        );
        return (
            <div className="books-view">
                <h1>Books</h1>
                <table className="books-table">
                    <thead>
                    <tr>
                        <th>Count</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>All Items</th>
                        <th>Available Items</th>
                        <th>Description</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {bookRows}
                    </tbody>
                </table>
            </div>
        );
}
