import { Link } from "react-router-dom";
import { FaPlusCircle, FaListAlt } from "react-icons/fa";

import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";

import "./../css/StudentDashboard.css";

function StudentDashboard() {

    const name = localStorage.getItem("fullName");

    return (

        <div className="dashboard">

            <Sidebar />

            <div className="dashboard-content">

                <Navbar />

                <div className="dashboard-body">

                    <div className="welcome-section">

                        <h2>
                            Welcome, {name ? name : "Student"} 👋
                        </h2>

                        <p>
                            Welcome to CampusResolve. From here you can raise new complaints
                            and track the complaints you have already submitted.
                        </p>

                    </div>

                    <div className="quick-actions">

                        <Link
                            to="/raise-complaint"
                            className="action-card"
                        >

                            <FaPlusCircle />

                            <h3>Raise Complaint</h3>

                            <p>
                                Submit a new complaint regarding campus facilities.
                            </p>

                        </Link>

                        <Link
                            to="/my-complaints"
                            className="action-card"
                        >

                            <FaListAlt />

                            <h3>My Complaints</h3>

                            <p>
                                View and track all complaints submitted by you.
                            </p>

                        </Link>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default StudentDashboard;