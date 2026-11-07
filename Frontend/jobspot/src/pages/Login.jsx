import { useState } from "react";
import api, { setAuthToken } from "../config/axiosConfig";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const { setAccessToken } = useAuth();
    const navigate = useNavigate();

    async function handleSubmit(e) {
        e.preventDefault();
        try {
            const response = await api.post("/auth/login", { email, password });

            setAccessToken(response.data.token);
            setAuthToken(response.data.token);
            navigate("/home");

        } catch (error) {
            console.log(error);
        }
    }

    return (
        <form className="auth-form" onSubmit={handleSubmit}>
            <h2 className="auth-title">Sign in to JobSpot</h2>

            <input
                className="auth-form__input"
                type="email"
                placeholder="Email"
                autoComplete="username"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
            />

            <input
                className="auth-form__input"
                type="password"
                placeholder="Password"
                autoComplete="current-password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button className="auth-button" type="submit">
                SIGN IN
            </button>
        </form>
    );
}

export default Login;