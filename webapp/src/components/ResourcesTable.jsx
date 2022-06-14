import React, {
    useContext,
    useEffect, useCallback
} from 'react';
import AppContext from '../context/AppContext';
import { fetchResources } from '../context/actions/ResourcesActions';
import { URL_API } from '../services/Service';
import { useMatch } from "react-router-dom";

import { Table } from 'reactstrap';

import Loading from './Loading';

import { useNavigate } from 'react-router-dom';


/// TIME
import moment from "moment";

function ResourcesTable(props) {
    const { state } = props;
    const { resources } = state;
    const { loading, error, data } = resources;
    const setResourceID = props.setResourceID;
    const cleanResourceToEdit = props.cleanResourceToEdit;

    const goToResource = (e) => {
        // console.log(e.currentTarget.id);
        cleanResourceToEdit("");
        setResourceID(e.currentTarget.id);
    };

    // const handleInputChange = useCallback((event) => {
    //     console.log(event.currentTarget.value)
    //     setResourceID(event.currentTarget.value)
    //   }, [setResourceID])

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        return !data.length ?
            renderEmpty : <>
                {data.map((item) => (
                    <tr key={item.resourceId}
                        id={item.resourceId}
                        style={{ cursor: "pointer" }}
                        onClick={goToResource}>
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
                        <Table hover size='sm' className='tableProjects'
                            style={{}}
                        >
                            <thead>
                                <tr>
                                    <th style={{ textAlign: "left", width: "210px" }}>E-MAIL</th>
                                    <th style={{ textAlign: "left", width: "100px" }}>ROLE</th>
                                    <th>COST<br />PER HOUR</th>
                                    <th>PERCENTAGE<br />OF ALLOCATION</th>
                                    <th>START DATE</th>
                                    <th>END DATE</th>
                                </tr>
                            </thead>
                            <tbody>
                                <TableBodyValues
                                    data={data[0]}
                                    renderEmpty={"No resources are allocated to this project"}
                                    renderItem={(item) => (
                                        <>
                                            <td style={{ textAlign: "left" }}>{item.email}<br /><small style={{ fontSize: "10px" }}>{item.resourceId}</small></td>
                                            <td style={{ textAlign: "left" }}>{item.role}</td>
                                            <td>{item.costPerHour} €</td>
                                            <td>{item.percentageAllocation} %</td>
                                            <td>{moment(item.startDate).format("DD/MM/YYYY")}</td>
                                            <td>{moment(item.endDate).format("DD/MM/YYYY")}</td>
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
export default ResourcesTable;