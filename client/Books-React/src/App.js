import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import './App.css';

import NavigationBar from './Components/NavigationBar'
import Footer from './Components/Footer'

import HomeView from './Views/HomeView'
import ListBooksView from './Views/Book/ListBooksView'
import CreateBookView from './Views/Book/CreateBookView'
import ListCustomersView from './Views/Customer/ListCustomersView'
import CreateCustomerView from './Views/Customer/CreateCustomerView'
import DetailsCustomerView from './Views/Customer/DetailsCustomerView'
import CreateOrderView from './Views/Order/CreateOrderView'
import $ from 'jquery';
import Remote from './remote'
import ListOrdersView from "./Views/Order/ListOrdersView";


export default class App extends Component {  // export default is used for this class to be visible in other modules
    constructor(props) {
        super(props);
    }

    componentDidMount() {   //This method is called before virest rendering of screen
        // Attach global AJAX "loading" event handlers
        $(document).on({
            ajaxStart: function () {
                $("#loadingBox").show()
            },
            ajaxStop: function () {
                $("#loadingBox").hide()
            }
        });

        // Attach a global AJAX error handler
        $(document).ajaxError(
            this.handleAjaxError.bind(this));
        this.showHomeView();
        $('#errorBox, #infoBox').on('click', function () {
            $(this).hide()
        })
    }

    handleAjaxError(event, response) {  //ERROR in case of AJAX
        let errorMsg = JSON.stringify(response);
        if (response.readyState === 0)
            errorMsg = "Cannot connect due to network error.";
        if (response.responseJSON &&
            response.responseJSON.description)
            errorMsg = response.responseJSON.description;
        this.showError(errorMsg);
    }

    showInfo(message) {
        $('#infoBox').text(message).show();
        setTimeout(function () {
            $('#infoBox').fadeOut();
        }, 3000);
    }

    showError(errorMsg) {
        $('#errorBox').text("Error: " + errorMsg).show();
    }

    render() {
        return (
            <div className="App">
                <header>
                    <NavigationBar
                        homeClicked={this.showHomeView.bind(this)}
                        booksClicked={this.showBooksView.bind(this)}
                        createBookClicked={this.showCreateBookView.bind(this)}
                        createCustomerClicked={this.showCreateCustomerView.bind(this)}
                        customersClicked={this.showCustomersView.bind(this)}
                    />
                    <div id="loadingBox">Loading...</div>
                    <div id="infoBox">Info msg</div>
                    <div id="errorBox">Error msg</div>
                </header>
                <div id="main">Main App View.</div>
                <Footer/>
            </div>

        );
    }

    showView(reactComponent) {
        ReactDOM.render(
            reactComponent,
            document.getElementById('main')
        );
        $('#errorBox').hide();
    }

    showHomeView() {
        this.showView(<HomeView/>)
    }

    showBooksView() {
        Remote.findAllBooks()
            .then(loadBooksSuccess.bind(this));

        function loadBooksSuccess(books) {
            this.showInfo("Books loaded.");
            this.showView(
                <ListBooksView
                    books={books}
                    orderBookClicked={this.prepareBookForOrder.bind(this)}
                />
            );
        }
    }

    prepareBookForOrder(bookId) {
        Remote.findBookById(bookId)
            .then(loadBookForOrderSuccess.bind(this));

        function loadBookForOrderSuccess(bookInfo) {
            let orderBookView = <CreateOrderView
                onsubmit={this.orderBook.bind(this)}
                bookId={bookInfo.id}
                title={bookInfo.title}
                availableItems={bookInfo.availableItems}
                fnumber={bookInfo.fnumber}
            />;
            this.showView(orderBookView);
        }
    }

    orderBook(bookId, title, availableItems, fnumber) {
        Remote.orderBook(bookId, fnumber)
            .then(orderBookSuccess.bind(this));

        function orderBookSuccess() {
            this.showBooksView();
            this.showInfo("Book ordered.");
        }
    }

    showCreateBookView() {
        this.showView(<CreateBookView onsubmit={this.createBook.bind(this)}/>);
    }

    createBook(title, author, availableItems, description) {
        Remote.createBook(title, author, availableItems, description)
            .then(createBookSuccess.bind(this));

        function createBookSuccess() {
            this.showBooksView();
            this.showInfo("Book created.");
        }
    }

    returnBook(orderId) {
        Remote.returnBook(orderId)
            .then(returnBookSuccess.bind(this));

        function returnBookSuccess() {
            this.showCustomersView();
            this.showInfo("Book returned.");
        }
    }

    showCreateCustomerView() {
        this.showView(<CreateCustomerView onsubmit={this.createCustomer.bind(this)}/>);
    }

    createCustomer(name, fnumber) {
        Remote.createCustomer(name, fnumber)
            .then(createCustomerSuccess.bind(this));

        function createCustomerSuccess() {
            this.showCustomersView();
            this.showInfo("Customer created.");
        }
    }

    showCustomersView() {
        Remote.findAllCustomers()
            .then(loadCustomersSuccess.bind(this));

        function loadCustomersSuccess(customers) {
            this.showInfo("Customers loaded.");
            this.showView(
                <ListCustomersView
                    customers={customers}
                    detailsCustomerClicked={this.showDetailsCustomer.bind(this, customers)}
                />
            );
        }
    }

    showDetailsCustomer(customers, customerId) {
        let customer = customers.find(e => e.id === customerId);
        Remote.findAllOrdersForCustomer(customerId)
            .then(loadBooksSuccess.bind(this));

        function loadBooksSuccess(orders) {
            let pendingOrders = orders.filter(e => e.status === "PENDING");
            let finishedOrders = orders.filter(e => e.status === "FINISHED");
            this.showInfo("Orders loaded.");
            this.showView(
                <div>
                    <DetailsCustomerView
                        name={customer.name}//
                        fnumber={customer.fnumber}
                    />
                    <ListOrdersView
                        statusOrders={"Pending"}
                        orders={pendingOrders}
                        returnBookClicked={this.returnBook.bind(this)}
                    />
                    <ListOrdersView
                        statusOrders={"Finished"}
                        orders={finishedOrders}
                        returnBookClicked={this.returnBook.bind(this)}
                    />
                </div>
            );
        }
    }
}

