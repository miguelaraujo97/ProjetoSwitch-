import React, { useContext, useEffect } from 'react';
import AppContext from '../context/AppContext';
import { fetchProductBacklog } from '../context/actions/ProductBacklogActions';
import { URL_API } from '../services/Service';
import { useMatch } from "react-router-dom";
import { IconButton } from "@mui/material";
import { BiFile } from "react-icons/bi";
import { Table, UncontrolledTooltip } from 'reactstrap';
import { useNavigate } from 'react-router-dom';


import Loading from './Loading';


function ProductBacklogTable(props) {
    const { state, dispatch } = useContext(AppContext);
    const { productBacklog } = state;
    const { loading, error, data } = productBacklog;
    const match = useMatch('/projects/:projectCode')
    const actualProjectCode = match.params.projectCode;
    const navigate = useNavigate();

    const goToUserStoryDetails = (e) => {
        /// NAVIGATE TO NEW PAGE
        navigate("/projects/" + actualProjectCode + "/user-stories", { replace: true });
    }



    useEffect(() => {

        const URL = URL_API + '/projects/' + actualProjectCode + '/user-stories';

        const request = {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            }
        };

        fetchProductBacklog(URL, request, dispatch);


    }, [dispatch]);

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        // console.log(state);
        return !data.length ?
            renderEmpty : <>
                {data.map((item) => (
                    <tr key={item.userStoryID} id={item.userStoryID} style={{ cursor: "pointer" }} >
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

            if (data.length > 0) {
                return (
                    <div>

                        <Table hover size='sm' className='tableProjects'>
                            <thead>
                                <tr>
                                    <th style={{ textAlign: "left", width: "230px" }}>CODE</th>
                                    <th style={{ textAlign: "left" }}>DESCRIPTION</th>
                                    <th style={{ width: "100px" }}>PRIORITY</th>
                                    <th style={{ width: "100px" }}>STATUS</th>
                                </tr>

                            </thead>
                            <tbody>
                                <TableBodyValues
                                    data={data[0]}
                                    renderEmpty={"No user stories found with this id."}
                                    renderItem={(item) => (
                                        <>
                                            <td style={{ textAlign: "left" }}>{item.projectCode}<br /><small style={{ fontSize: "10px" }}>{item.userStoryID}</small></td>
                                            <td style={{ textAlign: "left" }}>{item.description}</td>
                                            <td>{item.priority}</td>
                                            <td>{item.status}</td>
                                            <td>
                                            <IconButton aria-label="delete" size="small" color="primary" id={item.code} onClick={goToUserStoryDetails}>
                                            <BiFile id="DetailsButton" fontSize="inherit" />
                                            <UncontrolledTooltip placement="top" target="DetailsButton">
                                                    User Story details
                                            </UncontrolledTooltip>
                                            </IconButton>
                                            </td>
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
export default ProductBacklogTable;