import { ErrorMessage, Field, Form, Formik } from "formik";
import * as Yup from "yup";
import { useDispatch } from "react-redux";
import {Link, useNavigate} from "react-router-dom";
import {forgotPasswordAction, loginUserAction} from "../../Redux/Auth/auth.action";
import { TextField } from "@mui/material";
import React from "react";

const initialValues = { email: "" };

const validationSchema = Yup.object({
    email: Yup.string()
        .email("Không đúng định dạng email")
        .required("Email không được để trống"),
});

const ForgotPassword = () => {
    const dispatch = useDispatch();

    const handleSubmit = (value) => {
        console.log("handle submit", value);
        const loginData = {
            data: value
        };
        dispatch(forgotPasswordAction(loginData));
    };

    return (
        <>
            <Formik
                initialValues={initialValues}
                validationSchema={validationSchema}
                onSubmit={handleSubmit}
            >
                <Form className="space-y-5">
                    <div>
                        <Field
                            as={TextField}
                            name="email"
                            placeholder="Nhập email"
                            type="email"
                            variant="outlined"
                            fullWidth
                        />
                        <ErrorMessage
                            name="email"
                            component="div"
                            className="text-red-500"
                        />
                    </div>
                    <p className="justify-end flex">Remember your password? <Link className="text-blue-500" to={'/'}>Login here</Link> </p>

                    <button
                        type="submit"
                        className="bg-blue-500 text-white px-4 py-2 rounded w-full"
                    >
                        Reset Password
                    </button>
                </Form>
            </Formik>

        </>
    );
};

export default ForgotPassword;
