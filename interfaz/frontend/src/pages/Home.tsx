import { NavBar } from '../components/Nav';
import { type CSSProperties } from 'react';

export function Home() {
    const styles: { [key: string]: CSSProperties } = {
        container: {
            display: "flex",
            flexDirection: "column",
            width: "100%", 
            minHeight: "100vh",
            backgroundColor: "#90cdf4", 
        },
        main: {
            flex: 1,
            padding: "2rem",
            width: "100%", 
            boxSizing: "border-box",
            display: "flex",
            flexDirection: "column",
            gap: "2rem"
        },
        statsGrid: {
            display: "grid",
            gridTemplateColumns: "repeat(auto-fit, minmax(250px, 1fr))",
            gap: "1.5rem",
            width: "100%"
        }
    };

    return (
        <div style={styles.container}>
            <NavBar />
            <main style={styles.main}>
                <header style={{ display: "flex", justifyContent: "space-between", alignItems: "flex-end" }}>
                    <div>
                        <h1 style={{ fontSize: "2.2rem", color: "#1a202c", margin: 0 }}>
                            Panel de Asignación Logística
                        </h1>
                        <p style={{ color: "#718096", fontSize: "1.1rem" }}>
                            Gestión de red de sucursales en tiempo real.
                        </p>
                    </div>
                    <div style={{ textAlign: "right", paddingBottom: "5px" }}>
                        <span style={{ color: "#38a169", fontWeight: "bold" }}>● Sistema Online</span>
                    </div>
                </header>

                <div style={styles.statsGrid}>
                    {["Vehículos", "Sucursales", "Viajes Pendientes"].map((titulo, index) => (
                        <div key={index} style={{
                            backgroundColor: "white",
                            padding: "2rem",
                            borderRadius: "12px",
                            boxShadow: "0 4px 6px rgba(0,0,0,0.05)",
                            border: "1px solid #e2e8f0",
                            textAlign: "center"
                        }}>
                            <h3 style={{ color: "#718096", fontSize: "0.9rem", textTransform: "uppercase", letterSpacing: "1px" }}>{titulo}</h3>
                            <p style={{ fontSize: "3rem", fontWeight: "bold", color: "#2d3748", margin: "0.5rem 0" }}>
                                {index === 0 ? "12" : index === 1 ? "4" : "28"}
                            </p>
                        </div>
                    ))}
                </div>

                <section style={{
                    backgroundColor: "white",
                    borderRadius: "12px",
                    border: "1px solid #e2e8f0",
                    padding: "2rem",
                    flex: 1
                }}>
                    <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center", marginBottom: "2rem" }}>
                        <h2 style={{ fontSize: "1.5rem", color: "#2d3748" }}>Mapa de Asignación Eficiente</h2>
                        <button style={{ 
                            backgroundColor: "#3182ce", 
                            color: "white", 
                            padding: "0.8rem 1.6rem", 
                            borderRadius: "8px", 
                            border: "none", 
                            fontWeight: "bold",
                            cursor: "pointer"
                        }}>
                            Ejecutar Algoritmo
                        </button>
                    </div>
                    <div style={{ border: "2px dashed #e2e8f0", borderRadius: "12px", padding: "4rem", textAlign: "center", color: "#a0aec0" }}>
                        Aquí se visualizará el HashMap de asignación generado por el Core Java.
                    </div>
                </section>
            </main>
        </div>
    );
}