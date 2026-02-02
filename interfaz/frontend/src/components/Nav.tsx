import { Link } from "react-router-dom";

export function NavBar() {

  return (
    <nav style={styles.nav}>
      <Link to="/" style={styles.logo}>
        Smart Triping & Logistics
      </Link>

      <div style={styles.linkContainer}>
        <Link to="/" style={styles.link}>
          Asignar Viajes
        </Link>
        <Link to="/Sucursales" style={styles.link}>
          Sucursales
        </Link>
        <Link to="/Vehiculos" style={styles.link}>
          Vehículos
        </Link>
        <Link to="/Conductores" style={styles.link}>
          Conductores
        </Link>
        <Link to="/facturas" style={styles.link}>
          Facturas
        </Link>
      </div>
    </nav>
  );
}

const styles = {
  nav: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    backgroundColor: "#1a202c", 
    padding: "0.8rem 2rem",
    boxShadow: "0 4px 6px -1px rgba(0, 0, 0, 0.1)",
    fontFamily: "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
  },
  logo: {
    color: "#63b3ed", 
    fontSize: "1.25rem",
    fontWeight: "bold",
    textDecoration: "none",
  },
  linkContainer: {
    display: "flex",
    gap: "25px",
  },
  link: {
    color: "#e2e8f0",
    textDecoration: "none",
    fontSize: "0.95rem",
    fontWeight: "500",
    transition: "all 0.2s ease",
  },
};
