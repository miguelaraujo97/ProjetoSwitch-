import React, { useContext, useEffect, useState } from 'react';
import AppContext from '../context/AppContext';
import { fetchProjects } from '../context/actions/ProjectActions';
import { URL_API } from '../services/Service';
import Search from '../components/Search';
import { useNavigate } from 'react-router-dom';
import PaginationTable from '../components/PaginationTable';

import { Table, UncontrolledTooltip } from 'reactstrap';
import { IconButton } from "@mui/material";

import Loading from './Loading';

import { BiEditAlt, BiFile } from "react-icons/bi";

/// TIME
import moment from "moment";

function ProjectsTable() {
    const { state, dispatch } = useContext(AppContext);
    const { projects } = state;
    const { loading, error, data } = projects;

    const [activeProjectCode, setActiveProjectCode] = useState("");

    const navigate = useNavigate();
    const gotToViewProject = (e) => {

        /// SET ACTIVE PROJECT
        setActiveProjectCode(e.currentTarget.id);
        /// NAVIGATE TO NEW PAGE
        navigate("/projects/" + e.currentTarget.id, { replace: true });
    }

    const gotToEditProject = (e) => {

        /// SET ACTIVE PROJECT
        setActiveProjectCode(e.currentTarget.id);
        /// NAVIGATE TO NEW PAGE
        navigate("/projects/" + e.currentTarget.id + "/edit", { replace: true });
    }

    useEffect(() => {

        const URL = URL_API + "/projects?" + new URLSearchParams({
            page: 0,
            size: 50,
            sort: 'code,desc'
        });

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchProjects(URL, request, dispatch);
        //console.log("fetchProjects", dispatch)

    }, [dispatch]);

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        return !data[0].content.length ?
            renderEmpty : <>
                {data[0].content.map((item) => (
                    <tr key={item.code} style={{ cursor: "pointer" }} >
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
                    <h1>No Data ....</h1>
                </div>);
        } else {

            if (data[0].totalElements > 0) {
                return (
                    <div>


                        <Search
                            searchTop="Find your project"
                            elementsPerPage={data[0].numberOfElements}
                            totalElements={data[0].totalElements} />

                        <Table
                            hover
                            size='sm'
                            className='tableProjects'
                            style={{ textAlign: "left" }}
                        >
                            <thead>
                                <tr>
                                    <th style={{ width: "100px" }}>CODE</th>
                                    <th>NAME</th>
                                    <th style={{ width: "200px" }}>CUSTOMER</th>
                                    <th style={{ width: "110px" }}>START DATE</th>
                                    <th style={{ width: "110px" }}>END DATE</th>
                                    <th style={{ width: "100px" }}>STATUS</th>
                                    <th style={{ width: "70px" }}></th>
                                </tr>

                            </thead>
                            <tbody>
                                <TableBodyValues
                                    data={data}
                                    renderEmpty={"empty"}
                                    renderItem={(item) => (
                                        <>
                                            <td>{item.code}</td>
                                            <td>{item.name}</td>
                                            <td>{item.customer}</td>
                                            <td>{moment(item.startDate).format("DD/MM/YYYY")}</td>
                                            <td>{moment(item.endDate).format("DD/MM/YYYY")}</td>
                                            <td>{item.status}</td>
                                            <td >
                                                <IconButton aria-label="delete" size="small" id={item.code} onClick={gotToEditProject}>
                                                    <BiEditAlt id="EditButton" fontSize="inherit" />
                                                    <UncontrolledTooltip placement="top" target="EditButton">
                                                        Edit project 
                                                    </UncontrolledTooltip>
                                                </IconButton>

                                                <IconButton aria-label="delete" size="small" color="primary" id={item.code} onClick={gotToViewProject}>
                                                    <BiFile id="DetailsButton" fontSize="inherit" />
                                                    <UncontrolledTooltip placement="top" target="DetailsButton">
                                                        Project details
                                                    </UncontrolledTooltip>
                                                </IconButton>

                                            </td>
                                        </>
                                    )}
                                />
                            </tbody>
                        </Table>
                        <PaginationTable
                            totalElements={data[0].totalElements}
                            totalPages={data[0].totalPages}
                            size={data[0].size}
                            number={data[0].number}
                            first={data[0].first}
                            last={data[0].last}
                            empty={data[0].empty}
                        />
                    </div>
                );
            } else {
                console.log(state);
                return (
                    <div>
                        <h1>No data ....</h1>
                    </div>);
            }
        }
    }
}
export default ProjectsTable;