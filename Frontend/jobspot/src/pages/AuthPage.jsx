import { useState } from "react";
import Login from "./Login";
import Register from "./Register";
import "./AuthPage.css";
import { useLocation, useNavigate } from "react-router-dom";

function AuthPage() {
    const [isGrowing, setIsGrowing] = useState(false);
    const navigate = useNavigate();
    const location = useLocation();

    const isLogin = location.pathname==="/login";

    function changeForm() {
        if(isGrowing) return;
        setIsGrowing(true);
        navigate(isLogin ?"/register":"login",{
            replace: true
        });
        
        setTimeout(()=>{
            setIsGrowing(false);
        },1250);
    }

    return (
        <div className="auth-page">
            <div className="auth-main">
                <div
                    className={`auth-container auth-a-container ${
                        isLogin ? "auth-is-txl auth-is-inactive" : ""
                    }`}
                >
                    <Register />
                </div>
                <div
                    className={`auth-container auth-b-container ${
                        isLogin ? "auth-is-txl auth-is-z200" : "auth-is-inactive"
                    }`}
                >
                    <Login />
                </div>
                <div
                    className={`auth-switch ${isLogin ? "auth-is-txr" : ""} ${
                        isGrowing ? "auth-is-gx" : ""
                    }`}
                >
                    <div className={`auth-switch__circle ${isLogin ? "auth-is-txr" : ""}`} />
                    <div
                        className={`auth-switch__circle auth-switch__circle--t ${
                            isLogin ? "auth-is-txr" : ""
                        }`}
                    />

                    <div
                        className={`auth-switch__container ${
                            isLogin ? "auth-is-hidden" : ""
                        }`}
                    >
                        <h2 className="auth-title">Welcome Back !</h2>
                        <p className="auth-description">
                            To keep connected with us please login with your personal info
                        </p>
                        <button
                            type="button"
                            className="auth-button auth-switch__button"
                            onClick={
                                    changeForm        
                            }
                        >
                            SIGN IN
                        </button>
                    </div>

                    <div
                        className={`auth-switch__container ${
                            !isLogin ? "auth-is-hidden" : ""
                        }`}
                    >
                        <h2 className="auth-title">Hello Friend !</h2>
                        <p className="auth-description">
                            Enter your personal details and start journey with us
                        </p>
                        <button
                            type="button"
                            className="auth-button auth-switch__button"
                            onClick={
                                changeForm
                            }
                        >
                            SIGN UP
                        </button>
                    </div>
                </div>

            </div>
        </div>
    );
}

export default AuthPage;