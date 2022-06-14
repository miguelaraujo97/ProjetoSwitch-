import React from "react";

import { FormControl, TextField, FormHelperText } from "@mui/material";

function InputText(props) {

    const input_id = props.id;
    const input_name = props.name;
    const input_type = props.type;
    const input_label = props.label;
    //const input_defaultValue = props.defaultValue ? props.defaultValue : "";
    const input_value = props.value ? props.value : "";
    
    const input_min = props.min;
    const input_max = props.max;
    const input_placeholder = props.placeholder;
    const help_message = props.helpMessage;
    const data_onChange = props.onChange;

    const input_color = props.color ? props.color : "primary";

    const ariaDescribedBy = input_id + "-" + input_type;

    const input_disabled = props.disabled;

    // OPTIONS

    // TextField API
    // https://mui.com/pt/material-ui/api/text-field/

    // TextField UI
    // https://mui.com/pt/material-ui/react-text-field/

    return (
        <>
            <FormControl fullWidth>
                <TextField id={input_id} aria-describedby={ariaDescribedBy}
                    sx={{ mb: 2 }}
                    type={input_type}
                    name={input_name}
                    label={input_label}
                    //defaultValue={input_defaultValue}
                    value={input_value}
                    color={input_color}
                    placeholder={input_placeholder}
                    onChange={data_onChange}
                    disabled={input_disabled}
                    InputProps={{ 
                        inputProps: { 
                            min: input_min, 
                            max: input_max
                        } 
                    }}

                    size="small"  // normal | small
                />
                <FormHelperText
                    id={ariaDescribedBy}
                    style={{ marginTop: "-14px", marginBottom: "18px" }}
                >{help_message}</FormHelperText>
            </FormControl>
        </>
    );

}

export default InputText;