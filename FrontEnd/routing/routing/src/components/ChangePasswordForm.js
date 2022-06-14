import * as React from 'react';
import Box from '@mui/material/Box';
import IconButton from '@mui/material/IconButton';
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import InputAdornment from '@mui/material/InputAdornment';
import FormControl from '@mui/material/FormControl';
import Visibility from '@mui/icons-material/Visibility';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Button from "@mui/material/Button";
import {useForm, Controller} from 'react-hook-form';
import {yupResolver} from '@hookform/resolvers/yup';
import * as Yup from 'yup';
import {Typography} from "@mui/material";
import {Fragment} from "react";




function clickSubmit() {

}

export default function InputAdornments() {

    const [values, setValues] = React.useState({
        showCurrentPassword: false,
        currentPassword: '',
        showNewPassword: false,
        newPassword: '',
        showConfirmPassword: false,
        confirmPassword: ''

    });

    const handleChange = (prop) => (event) => {
        setValues({...values, [prop]: event.target.value});
    };

    const handleClickShowCurrentPassword = () => {
        setValues({
            ...values,
            showCurrentPassword: !values.showCurrentPassword
        });
    }

    const handleClickShowNewPassword = () => {
        setValues({
            ...values,
            showNewPassword: !values.showNewPassword
        });
    }


    const handleClickShowConfirmPassword = () => {
        setValues({
            ...values,
            showConfirmPassword: !values.showConfirmPassword
        });
    }


    const handleMouseDownPassword = (event) => {
        event.preventDefault();
    };

        const validationSchema = Yup.object().shape({
            email: Yup.string()
                .required('Email is required')
                .email('Email is invalid'),
            password: Yup.string()
                .required('Password is required')
                .min(6, 'Password must be at least 6 characters')
                .max(40, 'Password must not exceed 40 characters'),
            confirmPassword: Yup.string()
                .required('Confirm Password is required')
                .oneOf([Yup.ref('password'), null], 'Confirm Password does not match'),
            acceptTerms: Yup.bool().oneOf([true], 'Accept Terms is required')
        });
        const {
            register,
            control,
            handleSubmit,
            formState: {errors}
        } = useForm({
            resolver: yupResolver(validationSchema)
        });
        const onSubmit = data => {
            console.log(JSON.stringify(data, null, 2));
        };


    return (
        <Box sx={{display: 'flex', flexWrap: 'wrap', m: 5}}>
            <div>
                <FormControl sx={{m: 1, width: '25ch'}} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Email</InputLabel>
                    <OutlinedInput
                        required
                        id="email"
                        name="email"
                        onChange={handleChange('email')}
                        label="Email"
                        defaultValue="bill.sander@example.com"
                        {...register('email')}
                        error={errors.email ? true : false}
                    />
                    <Typography variant="inherit" color="textSecondary">
                        {errors.email?.message}
                    </Typography>
                </FormControl>
                <FormControl sx={{m: 1, width: '25ch'}} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Current Password</InputLabel>
                    <OutlinedInput
                        id="outlined-adornment-password"
                        type={values.showCurrentPassword ? 'text' : 'password'}
                        value={values.currentPassword}
                        onChange={handleChange('currentPassword')}

                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowCurrentPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showCurrentPassword ? <VisibilityOff/> : <Visibility/>}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="Current Password"
                    />
                </FormControl>
                <FormControl sx={{m: 1, width: '25ch'}} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">New Password</InputLabel>
                    <OutlinedInput
                        required
                        id="outlined-adornment-password"
                        name="newPassword"
                        type={values.showNewPassword ? 'text' : 'password'}
                        value={values.password}
                        onChange={handleChange('newPassword')}
                        {...register('password')}
                        error={errors.password ? true : false}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowNewPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showNewPassword ? <VisibilityOff/> : <Visibility/>}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="New Password"
                    />
                    <Typography variant="inherit" color="textSecondary">
                        {errors.password?.message}
                    </Typography>
                </FormControl>
                <FormControl sx={{m: 1, width: '25ch'}} variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Confirm Password</InputLabel>
                    <OutlinedInput
                        required
                        id="outlined-adornment-password"
                        type={values.showConfirmPassword ? 'text' : 'password'}
                        value={values.confirmPassword}
                        onChange={handleChange('confirmPassword')}
                        endAdornment={
                            <InputAdornment position="end">
                                <IconButton
                                    aria-label="toggle password visibility"
                                    onClick={handleClickShowConfirmPassword}
                                    onMouseDown={handleMouseDownPassword}
                                    edge="end"
                                >
                                    {values.showConfirmPassword ? <VisibilityOff/> : <Visibility/>}
                                </IconButton>
                            </InputAdornment>
                        }
                        label="Confirm Password"
                    />
                </FormControl>
                <Button sx={{m: 1, maxWidth: '25ch', maxHeight: '7ch', minWidth: '25ch', minHeight: '7ch'}}
                        variant="contained" onClick={handleSubmit(onSubmit)}> Submit</Button>


            </div>
        </Box>
    );
}
