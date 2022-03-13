import React, { Component } from 'react';
import './NavigationBar.css';

export default class NavigationBar extends Component {  // export default is used for this class to be visible in other modules
    render() {
            return (
                <div className="navigation-bar">
                    <a href="#" onClick={this.props.homeClicked}>Home</a>
                    <a href="#" onClick={this.props.booksClicked}>Books</a>
                    <a href="#" onClick={this.props.createBookClicked}>Create Book</a>
                    <a href="#" onClick={this.props.createCustomerClicked}>Create Customer</a>
                    <a href="#" onClick={this.props.customersClicked}>Customers</a>
                    <span className="loggedInUser">
                        Welcome!
                    </span>
                </div>
            );
    }
}