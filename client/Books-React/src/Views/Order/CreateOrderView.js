import React, { Component } from 'react';

export default class CreateOrderView extends Component {
    render() {
        return (
            <form className="create-order-form" onSubmit={this.submitForm.bind(this)}>
                <h1>Create Order</h1>
                <label>
                    <div>Title:</div>
                    <input type="text" name="title" disabled
                           defaultValue={this.props.title}
                           ref={e => this.titleField = e}/>
                </label>
                <label>
                    <div>Available Items:</div>
                    <input type="text" name="availableItems" disabled
                           defaultValue={this.props.availableItems}
                           ref={e => this.availableItemsField = e}/>
                </label>
                <label>
                    <div>Faculty Number:</div>
                    <input type="text" name="fnumber" required
                           defaultValue={this.props.fnumber}
                           ref={e => this.fnumberField = e} />
                </label>
                <div>
                    <input type="submit" value="Order" />
                </div>
            </form>
        );
    }

    submitForm(event) {
        event.preventDefault();
        this.props.onsubmit(
            this.props.bookId,
            this.titleField.value,
            this.availableItemsField.value,
            this.fnumberField.value,
        );
    }
}
