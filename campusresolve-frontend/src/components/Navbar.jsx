import { FaUserCircle } from "react-icons/fa";
import "./../css/Navbar.css";

function Navbar() {

    const fullName = localStorage.getItem("fullName");
    const role = localStorage.getItem("role");

    return (

        <div className="navbar">

            <div>

                <h2>
                    {role === "ADMIN"
                        ? "Admin Dashboard"
                        : "Student Dashboard"}
                </h2>

                <p>
                    Welcome back!
                </p>

            </div>

            <div className="navbar-user">

                <FaUserCircle className="user-icon" />

            </div>

        </div>

    );

}

export default Navbar;