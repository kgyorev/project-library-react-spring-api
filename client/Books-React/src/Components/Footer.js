import React, { Component } from 'react';
import './Footer.css';


export default class Footer extends Component {  // export default is used for this class to be visible in other modules
    render() {
        return (
            <div className="footer-view">
               (c) 2022 - ReactJS Book Library Project
            </div>
        );
    }
}