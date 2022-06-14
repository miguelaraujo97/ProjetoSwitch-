import React from 'react';
import { useMatch } from 'react-router-dom';

function Topic() {
    const match = useMatch('/topics/:id');
    return (
        <div>
            <h1>Topic</h1>
            <ul>
                <li>URL: {match.pathname}</li>
                <li> PATH: {match.pattern.path}</li>
                <li> ID: {match.params.id}</li>
            </ul>
        </div>
    )
}

export default Topic;