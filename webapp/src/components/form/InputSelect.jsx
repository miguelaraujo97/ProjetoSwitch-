import React from "react";

import { FormControl, InputLabel, Select, MenuItem } from "@mui/material";

function InputSelect(props) {

    const sel_id = props.id;
    const sel_name = props.name;
    const sel_label = props.label;
    const option_value = props.data_value ? props.data_value : "value";
    const option_label = props.data_label ? props.data_label : "label";
    const data_select = props.data ? props.data : [];
    const data_onChange = props.onChange;

    const selectValue = props.selectValue ? props.selectValue : "";

    const input_disabled = props.disabled === "true" ? true : false;

    // OPTIONS

    // TextField API
    // https://mui.com/pt/material-ui/api/select/

    // TextField UI
    // https://mui.com/pt/material-ui/react-select/

    return (
        <>
            <FormControl fullWidth>
                <InputLabel id={sel_id}>{sel_label}</InputLabel>
                <Select fullWidth
                    labelId={sel_id}
                    id={sel_id}
                    name={sel_name}
                    label={sel_label}
                    menuplacement="auto"
                    menuposition="fixed"
                    defaultValue={selectValue}
                    onChange={data_onChange} 
                    disabled={input_disabled}
                    size="small"
                                     
                >
                    {data_select.map(option => {
                        return (
                            <MenuItem key={option[option_value]} value={option[option_value]}>
                                {option[option_label] ?? option[option_value]}
                            </MenuItem>
                        );
                    })}
                </Select>
            </FormControl>
        </>
    );

}

export default InputSelect;