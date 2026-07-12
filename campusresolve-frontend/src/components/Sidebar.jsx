import { Link, useLocation, useNavigate } from "react-router-dom";
import {
  FaUniversity,
  FaHome,
  FaPlusCircle,
  FaList,
  FaSignOutAlt,
  FaUserCircle,
} from "react-icons/fa";

import "./../css/Sidebar.css";

function Sidebar() {
  const navigate = useNavigate();
  const location = useLocation();

  const fullName = localStorage.getItem("fullName");
  const email = localStorage.getItem("email");
  const role = localStorage.getItem("role");

  const logout = () => {
    localStorage.clear();
    navigate("/login");
  };

  return (
    <aside className="sidebar">

      {/* Logo */}

      <div className="sidebar-logo">
        <FaUniversity className="logo-icon" />

        <div>
          <h2>CampusResolve</h2>
        </div>
      </div>

      {/* Menu */}

      <nav className="sidebar-menu">

        <Link
          to="/student"
          className={location.pathname === "/student" ? "active" : ""}
        >
          <FaHome />
          <span>Dashboard</span>
        </Link>

      

      </nav>

      {/* Bottom Profile */}

      <div className="sidebar-footer">

        <div className="user-profile">

          <FaUserCircle className="user-icon" />

          <div className="user-details">
            <h4>{fullName}</h4>
            <p>{email}</p>

            <span className="role-badge">
              {role}
            </span>
          </div>

        </div>

        <button className="logout-btn" onClick={logout}>
          <FaSignOutAlt />
          <span>Logout</span>
        </button>

      </div>

    </aside>
  );
}

export default Sidebar;