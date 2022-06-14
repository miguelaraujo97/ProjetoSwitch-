import React from "react";

import { FormControl, TextField, FormHelperText } from "@mui/material";
import { DesktopDatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterMoment } from '@mui/x-date-pickers/AdapterMoment';

/// TIME
import moment from "moment";

function InputDate(props) {

    const input_id = props.id;
    const input_name = props.name;
    const input_type = "date";
    const input_label = props.label;
    const input_defaultValue = props.defaultValue ? moment(props.defaultValue) : "";
    const input_value = props.value ? moment(props.value) : "";
    const input_maxdate = moment(props.maxDate);
    const input_mindate = moment(props.minDate);
    const input_placeholder = props.placeholder;
    const help_message = props.helpMessage;
    const data_onChange = props.onChange;

    const input_color = props.color ? props.color : "primary";

    const ariaDescribedBy = input_id + "-" + input_type;

    const input_disabled = props.disabled;

    // OPTIONS
    // https://mui.com/pt/x/react-date-pickers/getting-started/

    return (
        <>
            <FormControl fullWidth>
                <LocalizationProvider dateAdapter={AdapterMoment}>
                    <DesktopDatePicker
                        name={input_name}
                        label={input_label}
                        inputFormat="DD/MM/YYYY"
                        //mask="__/__/____"
                        //mask=""
                        //autoOk="false"
                        value={input_value}
                        defaultValue=""
                        //maxDate={input_maxdate}
                        //minDate={input_mindate}
                        color={input_color}
                        onChange={data_onChange}
                        renderInput={(params) => <TextField
                            {...params}
                            sx={{ mb: 2 }}
                            size="small"
                        />}
                    />
                </LocalizationProvider>

                <FormHelperText
                    id={ariaDescribedBy}
                    style={{ marginTop: "-14px", marginBottom: "18px" }}
                >{help_message}</FormHelperText>
            </FormControl>
        </>
    );

}

export default InputDate;