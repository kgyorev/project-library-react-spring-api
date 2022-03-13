import $ from 'jquery';

const Remote = (function() {
       const baseUrl = "http://127.0.0.1:8000";
    //const baseUrl = "http://87.246.58.64:8000";

    function findAllBooks() {
        return $.ajax({
            method: "GET",
            url: baseUrl + "/books/all",
            headers: {}
        });
    }

    function findBookById(id) {
        return $.ajax({
            method: "GET",
            url: baseUrl + "/books/details",
            data: {id},
            headers: {}
        });
    }

    function createBook(title, author, availableItems,description) {
        return $.ajax({
            method: "POST",
            url: baseUrl + "/books/add",
            headers: {},
            data: { title, author, availableItems,description }
        });
    }
    function orderBook(id, fnumber) {
        return $.ajax({
            method: "POST",
            url: baseUrl +"/orders/add",
            headers: {},
            data: { id, fnumber}
        });
    }
    function createCustomer(name, fnumber) {
        return $.ajax({
            method: "POST",
            url: baseUrl + "/customers/add",
            headers: {},
            data: {name, fnumber}
        });
    }

    function findAllCustomers() {
        return $.ajax({
            method: "GET",
            url: baseUrl + "/customers/all",
            headers: {}
        });
    }
    function findAllOrdersForCustomer(id) {
        return $.ajax({
            method: "GET",
            url: baseUrl +"/orders/customer",
            headers: {},
            data: { id }
        });
    }
    function findAllPendingOrdersForCustomer(id) {
        return $.ajax({
            method: "GET",
            url: baseUrl +"/orders/customer/status",
            headers: {},
            data: { id,status:"PENDING"}
        });
    }
    function findAllFinishedOrdersForCustomer(id) {
        return $.ajax({
            method: "GET",
            url: baseUrl +"/orders/customer/status",
            headers: {},
            data: { id,status:"FINISHED"}
        });
    }
    function returnBook(id) {
        return $.ajax({
            method: "POST",
            url: baseUrl +"/orders/return",
            headers: {},
            data: { id }
        });
    }


    return {
          findAllBooks, createBook, findBookById,createCustomer,findAllCustomers,orderBook,findAllOrdersForCustomer,returnBook,findAllPendingOrdersForCustomer,findAllFinishedOrdersForCustomer
    }
})();

export default Remote;
