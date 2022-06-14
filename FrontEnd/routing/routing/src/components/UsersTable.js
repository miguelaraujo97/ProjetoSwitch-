import React from 'react';
//import AppContext from '../context/AppContext';
//import { URL_API } from '../services/Services';
//import { searchUserProfile } from '../context/Actions';

import Table from '@mui/material/Table';
//import { DataGrid } from '@mui/x-data-grid';


import Loading from './Loading';


function UsersTable(props) {
    // const { state, dispatch } = useContext(AppContext);
    // const { users } = state;
    const { loading, error, data } = props.users;

    // useEffect(() => {

    //     const URL = URL_API + "/users/by-profile/Visitor";
    //     const request = {
    //         method: "GET",
    //         headers: {
    //             "Content-Type": "application/json",
    //         }
    //     };
    //     searchUserProfile(URL, request, dispatch);

    // }, [dispatch]);

    // const columns = [
    //     { field: 'name'},
    //     { field: 'email', headerName: 'EMAIL' },
    //     { field: 'function', headerName: 'FUNCTION', width:100 }
    // ];


    if (loading === true) {
        return (<Loading />);
    } else {
        if (error !== null) {
            return (
                <div>
                    <Table style={{ textAlign: "center" }}>

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
                        <Table style={{ textAlign: "center" }}>
                            <thead>
                                <tr>
                                    <th>NAME</th>
                                    <th>EMAIL</th>
                                    <th>FUNCTION</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    data.map((item) => (
                                        <tr key={item.name}>
                                            <td>{item.name}</td>
                                            <td>{item.email}</td>
                                            <td>{item.function}</td>
                                            <td />
                                        </tr>
                                    ))
                                }
                            </tbody>
                        </Table>
                    </div>

                    // <div style={{ height: 400, width: '100%', align: "center" }}>
                    //     <DataGrid
                    //         rows={data}
                    //         columns={columns}
                    //         getRowId={(row) => row.email}
                    //         pageSize={5}
                    //         rowsPerPageOptions={[5]}
                    //         checkboxSelection
                    //     />
                    // </div>

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