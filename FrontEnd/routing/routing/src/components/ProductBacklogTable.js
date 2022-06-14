import React, {useContext, useEffect, useCallback} from 'react';
import TableRow from "@mui/material/TableRow";
import Loading from "./Loading";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableBody from "@mui/material/TableBody";
import {URL_API} from "../services/Services";
import AppContext from "../context/AppContext";
import {fetchProjects} from "../context/actions/ProjectActions";
import {Link, useMatch} from "react-router-dom";

function ProductBacklogTable(props) {
    const {state, dispatch} = useContext(AppContext);
    const {userStories} = state;
    const {loading, error, data} = userStories;
    const match = useMatch('/projects/:projectCode')
    const actualProjectCode = match.params.projectCode;

    useEffect(() => {
        const URL = URL_API + `/projects/${actualProjectCode}/user-stories?` + new URLSearchParams({
            page: 0,
            size: 10,
        });

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchProjects(URL, request, dispatch);


    }, [dispatch]);

    function TableBodyValues({data, renderItem, renderEmpty}) {
        return !data[0].content.length ?
            renderEmpty : <>
                {data[0].content.map((values) => (
                    <TableRow /*key={item.id} id={item.id}
                              style={{cursor: "pointer"}}*/>
                        {/*      onClick={gotToProject}>*/}
                        {/*{renderItem(item)}*/}
                    </TableRow>
                ))}
            </>;
    }

    if (loading === true) {
        return (<Loading/>);
    } else {
        if (error !== null) {
            return (
                <div>
                    <h1>Error ....</h1>
                </div>);
        } else {
            if (data[0].totalElements > 0) {
                return (
                    <div>
                        <hr/>
                        <Table style={{textAlign: "center", padding: "20rem"}}>
                            <TableHead style={{borderBottom: "1px solid #ccc"}}>
                                <>
                                    <th>User story ID</th>
                                    <th>Description</th>
                                    <th>Priority</th>
                                </>
                            </TableHead>
                            <TableBody>
                                <TableBodyValues
                                    data={data}
                                    renderEmpty={"empty"}
                                    renderItem={(item) => (
                                        <>
                                            <td>{item.userStoryID}</td>
                                            <td>{item.description}</td>
                                            <td>{item.priority}</td>
                                        </>
                                    )}
                                />
                            </TableBody>
                        </Table>
                        <hr/>
                    </div>)
            } else {
                return (
                    <div>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}

export default ProductBacklogTable;