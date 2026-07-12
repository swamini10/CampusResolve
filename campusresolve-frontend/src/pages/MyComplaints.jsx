import { useEffect, useState } from "react";
import axios from "axios";
import Sidebar from "../components/Sidebar";
import Navbar from "../components/Navbar";
import "../css/MyComplaints.css";

function MyComplaints() {

    const [complaints, setComplaints] = useState([]);

    useEffect(() => {
        fetchComplaints();
    }, []);

    const fetchComplaints = async () => {

        try {

            const response = await axios.get(
                "http://localhost:8080/api/complaints/my",
                {
                    headers: {
                        Authorization:
                            "Bearer " + localStorage.getItem("token")
                    }
                }
            );

            setComplaints(response.data);

        } catch (error) {
            console.log(error);
            alert("Unable to load complaints");
        }

    };

    const getStatusClass = (status) => {

        switch (status) {
            case "PENDING":
                return "pending";

            case "IN_PROGRESS":
                return "progress";

            case "RESOLVED":
                return "resolved";

            default:
                return "";
        }

    };

    return (

        <div className="dashboard">

            <Sidebar />

            <div className="dashboard-content">

                <Navbar />

                <div className="complaints-container">

                    <div className="complaints-card">

                        <h2>My Complaints</h2>

                        <p className="subtitle">
                            View all complaints submitted by you.
                        </p>

                        {
                            complaints.length === 0 ?

                                (
                                    <div className="empty-state">

                                        <h3>No Complaints Found</h3>

                                        <p>
                                            You haven't raised any complaints yet.
                                        </p>

                                    </div>
                                )

                                :

                                (

                                    <table>

                                        <thead>

                                            <tr>

                                                <th>ID</th>
                                                <th>Title</th>
                                                <th>Description</th>
                                                <th>Status</th>
                                                <th>Created</th>

                                            </tr>

                                        </thead>

                                        <tbody>

                                            {
                                                complaints.map((item) => (

                                                    <tr key={item.id}>

                                                        <td>{item.id}</td>

                                                        <td>{item.title}</td>

                                                        <td>{item.description}</td>

                                                        <td>

                                                            <span className={`status ${getStatusClass(item.status)}`}>

                                                                {item.status}

                                                            </span>

                                                        </td>

                                                        <td>

                                                            {item.createdAt
                                                                ?.replace("T", " ")
                                                                ?.substring(0, 16)}

                                                        </td>

                                                    </tr>

                                                ))
                                            }

                                        </tbody>

                                    </table>

                                )

                        }

                    </div>

                </div>

            </div>

        </div>

    );

}

export default MyComplaints;