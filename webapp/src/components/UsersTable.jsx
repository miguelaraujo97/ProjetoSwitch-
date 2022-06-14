import React from 'react';

//import Table from '@mui/material/Table';
import { Table } from 'reactstrap';

import { useNavigate } from 'react-router-dom';

import Loading from './Loading';


function UsersTable(props) {
    const { loading, error, data } = props.users;

    const navigate = useNavigate();
    const goToUser = (e) => {
        navigate("/users/" + e.currentTarget.id, { replace: true });
    }

    function TableBodyValues({ data, renderItem, renderEmpty }) {
        return data === [] ?
            renderEmpty : <>
                {data.map((item) => (
                    <tr key={item.email} id={item.email} style={{ cursor: "pointer" }} onClick={goToUser} >
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
                    <Table hover size='sm' className='tableUsers' style={{ textAlign: "center" }}>

                        <thead>
                            <tr>
                                <th>NAME</th>
                                <th>EMAIL</th>
                                <th>FUNCTION</th>
                            </tr>
                        </thead>
                    </Table>

                </div>);
        } else {
            if (data !== []) {
                return (
                    <div>
                        <Table hover size='sm' className='tableUsers' style={{ textAlign: "center" }}>
                            <thead>
                                <tr>
                                    <th>NAME</th>
                                    <th>EMAIL</th>
                                    <th>FUNCTION</th>
                                </tr>
                            </thead>
                            <tbody>
                                <TableBodyValues
                                    data={data}
                                    renderEmpty={"empty"}
                                    renderItem={(item) => (
                                        <>
                                            <td>{item.name}</td>
                                            <td>{item.email}</td>
                                            <td>{item.function}</td>
                                        </>
                                    )}
                                />

                                {/* {
                                    data.map((item) => (
                                        <tr key={item.email} id={item.email} onClick={() => goToUser(item.email)} style={{ cursor: "pointer" }}>
                                            <td>{item.name}</td>
                                            <td>{item.email}</td>
                                            <td>{item.function}</td>
                                            <td />
                                        </tr>
                                    ))
                                } */}
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
export default UsersTable;