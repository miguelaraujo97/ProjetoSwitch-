import React, { Component } from 'react';
import ProductBacklogTable from "../components/ProductBacklogTable";

class ProductBacklog extends Component {
    render() {
        return (
            <div>
                <h1>Product Backlog</h1>
                <ProductBacklogTable/>
            </div>)
    }
}

export default ProductBacklog;