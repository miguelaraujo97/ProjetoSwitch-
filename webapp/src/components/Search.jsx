import React from "react";
//import { Input } from "reactstrap";

//import { BiSearch } from "react-icons/bi";

import TextField from '@mui/material/TextField';

export default function Search(props) {


    const filteredItems = (props.totalElements > 0) ? "filtered " + props.elementsPerPage + " / " + props.totalElements : "";
    const searchTop = (props.defaultValue !== "") ? props.defaultValue : "";
    const label = (props.searchTop !== "") ? props.searchTop : "Search";


    return (
        <div className="mt-3">

            <TextField style={{ width: '100%' }}
                id="standard-helperText"
                label={label}
                defaultValue={searchTop}
                variant="filled"
            />

            {/* <small style={{ color: '#bbb' }}>{props.searchTop}</small> */}
            {/* <Input className="form-control" type="search"   /> */}
            <small style={{ float: "right", color: '#aaa' }}>{filteredItems}</small>

        </div>
    )

}

