import React, { useContext, useEffect, useCallback } from 'react';
import AppContext from '../context/AppContext';
import { fetchProjects } from '../context/actions/ProjectActions';
import { URL_API } from '../services/Services';

import {useNavigate} from 'react-router-dom';

import Table from '@mui/material/Table';
import TableHead from '@mui/material/TableHead';
import TableBody from '@mui/material/TableBody';
import TableRow from '@mui/material/TableRow';
import TablePagination from './TablePagination';


import Loading from './Loading';


function ProjectsTable(props) {

    const { state, dispatch } = useContext(AppContext);
    const { projects } = state;
    const { loading, error, data } = projects;

    const navigate = useNavigate();
    const gotToProject = (e) => {

        navigate("/projects/" + e.currentTarget.id, {replace: true});
    }


    const handleChangePage = (event, newPage) => {

        console.log(event);
    };

    const handleChangeRowsPerPage = (event) => {

        console.log(event);
    };


    useEffect(() => {


        

        const URL = URL_API + "/projects?" + new URLSearchParams({
            page: 0,
            size: 2,
            sort: 'code'
        });

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchProjects(URL, request, dispatch);


    }, [dispatch]);

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        return !data[0].content.length ?
            renderEmpty : <>
                {data[0].content.map((item) => (
                    <TableRow key={item.code} id={item.code} style={{ cursor: "pointer" }} onClick={gotToProject}>
                        {renderItem(item)}
                    </TableRow>
                ))}
            </>;
    }

    if (loading === true) {
        return (<Loading />);
    } else {
        if (error !== null) {
            return (
                <div>
                    <h1>Error ....</h1>
                </div>);
        } else {

            if (data[0].totalElements > 0) {
                return (
                    <div >
                        <hr />
                        <Table style={{ textAlign: "center", padding:"20rem" }}>
                            <TableHead style={{ borderBottom: "1px solid #ccc" }} >
                                <>
                                    <th>CODE</th>
                                    <th>NAME</th>
                                    <th>CUSTOMER</th>
                                </>

                            </TableHead>
                            <TableBody>
                                <TableBodyValues
                                    data={data}
                                    renderEmpty={"empty"}
                                    renderItem={(item) => (
                                        <>
                                            <td>{item.code}</td>
                                            <td>{item.name}</td>
                                            <td>{item.customer}</td>
                                        </>
                                    )}
                                />
                            </TableBody>
                        </Table>
                        <hr />
                        <TablePagination
                            component="div"
                            count={2}
                            page={0}
                            //rowsPerPage={rowsPerPage} 
                            onPageChange={handleChangePage}
                            onRowsPerPageChange={handleChangeRowsPerPage}
                            totalCount={6}
                        />

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
export default ProjectsTable;