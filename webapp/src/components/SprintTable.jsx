import React, { useContext, useEffect } from 'react';
import AppContext from '../context/AppContext';
import { fetchSprint } from '../context/actions/SprintActions';
import { URL_API } from '../services/Service';
import { useMatch } from "react-router-dom";

import { Table } from 'reactstrap';

import Loading from './Loading';


function SprintTable(props) {
    const { state, dispatch } = useContext(AppContext);
    const { sprints } = state;
    const { loading, error, data } = sprints;
    const match = useMatch('/projects/:projectCode')
    const actualProjectCode = match.params.projectCode;
    
    useEffect(() => {

        const URL = URL_API + '/projects/' + actualProjectCode + '/sprints';

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchSprint(URL, request, dispatch);


    }, [dispatch,actualProjectCode]);

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        return data===[] ?
            renderEmpty : <>
                {data.map((item) => (
                    <tr key={item.sprintID} id={item.sprintID} style={{ cursor: "pointer" }} >
                        {renderItem(item)}
                    </tr>
                ))}
            </>;
    }

    if (loading === true) {
        return (<Loading />);
    } else {
        if (error !== null) {
            return (
                <div>
                    <Table hover size='sm' className='tableProjects'>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>STATUS</th>
                                    <th>START DATE</th>
                                    <th>END DATE</th>
                                </tr>
                            </thead>
                        </Table>
                </div>
                );
        } else {
            if (data!== []) {
                return (
                    <div>
                        <Table hover size='sm' className='tableProjects'>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>STATUS</th>
                                    <th>START DATE</th>
                                    <th>END DATE</th>
                                </tr>
                            </thead>
                            <tbody>
                                <TableBodyValues
                                    data={data}
                                    renderEmpty={"empty"}
                                    renderItem={(item) => (
                                        <>
                                            <td>{item.sprintID}</td>
                                            <td>{item.status}</td>
                                            <td>{item.startDate}</td>
                                            <td>{item.endDate}</td>
                                        </>
                                    )}
                                />
                            </tbody>
                        </Table>
                    </div>
                );
            } else {
                return (
                    <div>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}
export default SprintTable;