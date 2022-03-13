import React, { Component } from 'react';

export default class CreateCustomerView extends Component {
    render() {
        return (
            <form className="create-customer-form" onSubmit={this.submitForm.bind(this)}>
                <h1>Create Customer</h1>
                <label>
                    <div>Name:</div>
                    <input type="text" name="name" required
                           ref={e => this.nameField = e} />
                </label>
                <label>
                    <div>Faculty Number:</div>
                    <input type="text" name="fnumber" required
                           ref={e => this.fnumberField = e} />
                </label>
                <div>
                    <input type="submit" value="Create" />
                </div>
            </form>
        );
    }

    submitForm(event) {
        event.preventDefault();
        this.props.onsubmit(
            this.nameField.value,
            this.fnumberField.value,
        );
    }
}
