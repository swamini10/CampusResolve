import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Navbar from "../components/Navbar";
import Sidebar from "../components/Sidebar";
import "./../css/RaiseComplaint.css";

function RaiseComplaint() {

    const navigate = useNavigate();

    const [complaint, setComplaint] = useState({
        title: "",
        description: ""
    });

    const [message, setMessage] = useState("");
    const [error, setError] = useState("");

    const handleChange = (e) => {
        setComplaint({
            ...complaint,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {

        e.preventDefault();

        setMessage("");
        setError("");

        try {

            await axios.post(
                "http://localhost:8080/api/complaints",
                complaint,
                {
                    headers: {
                        Authorization:
                            "Bearer " + localStorage.getItem("token")
                    }
                }
            );

            setMessage("✅ Complaint Raised Successfully!");

            setComplaint({
                title: "",
                description: ""
            });

            setTimeout(() => {
                navigate("/student");
            }, 1500);

        } catch (err) {

            console.log(err);

            setError("❌ Unable to Raise Complaint. Please try again.");

        }

    };

    return (

        <div className="dashboard">

            <Sidebar />

            <div className="dashboard-content">

                <Navbar />

                <div className="raise-container">

                    <div className="raise-card">

                        <h2>Raise Complaint</h2>

                        <p>
                            Fill the details below to submit a complaint.
                        </p>

                        <form onSubmit={handleSubmit}>

                            <div className="form-group">

                                <label>Complaint Title</label>

                                <input
                                    type="text"
                                    name="title"
                                    placeholder="Enter complaint title"
                                    value={complaint.title}
                                    onChange={handleChange}
                                    required
                                />

                            </div>

                            <div className="form-group">

                                <label>Description</label>

                                <textarea
                                    name="description"
                                    placeholder="Describe your complaint..."
                                    rows="6"
                                    value={complaint.description}
                                    onChange={handleChange}
                                    required
                                />

                            </div>

                            {message && (
                                <div className="success-msg">
                                    {message}
                                </div>
                            )}

                            {error && (
                                <div className="error-msg">
                                    {error}
                                </div>
                            )}

                            <button type="submit">
                                Submit Complaint
                            </button>

                        </form>

                    </div>

                </div>

            </div>

        </div>

    );

}

export default RaiseComplaint;