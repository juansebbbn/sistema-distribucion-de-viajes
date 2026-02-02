import { type CSSProperties, useState } from 'react';
import { NavBar } from '../components/Nav';

interface Vehiculo {
    id_vehiculo: number;
    capacidadKG: number;
}

export function Vehiculos() {

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [vehiculos, setVehiculos] = useState<Vehiculo[]>([
        { id_vehiculo: 1, capacidadKG: 500 },
        { id_vehiculo: 2, capacidadKG: 1200 },
    ]);
    const [form, setForm] = useState({ capacidadKG: 0 });

    const guardarVehiculo = () => {
        const nuevo: Vehiculo = {
            id_vehiculo: vehiculos.length + 1,
            capacidadKG: Number(form.capacidadKG)
        };
        setVehiculos([...vehiculos, nuevo]);
        setForm({ capacidadKG: 0 });
        setIsModalOpen(false);
        console.log("Vehículo listo para persistir en el Core");
    };

    const styles: { [key: string]: CSSProperties } = {
        container: {
            display: "flex",
            flexDirection: "column",
            width: "100%",
            minHeight: "100vh",
            backgroundColor: "#90cdf4", 
        },
        main: {
            padding: "2rem",
            flex: 1,
            width: "100%",
            boxSizing: "border-box",
        },
        headerCard: {
            display: "flex",
            justifyContent: "space-between",
            alignItems: "center",
            backgroundColor: "white",
            padding: "1.5rem 2rem",
            borderRadius: "12px",
            marginBottom: "2rem",
            boxShadow: "0 2px 4px rgba(0,0,0,0.05)"
        },
        tableCard: {
            backgroundColor: "white",
            borderRadius: "12px",
            overflow: "hidden",
            boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
        },
        table: { width: "100%", borderCollapse: "collapse" },
        th: { 
            backgroundColor: "#f8fafc", 
            padding: "1rem", 
            textAlign: "left", 
            fontSize: "0.8rem", 
            color: "#64748b", 
            borderBottom: "1px solid #e2e8f0",
            textTransform: "uppercase" 
        },
        td: { padding: "1rem", borderBottom: "1px solid #f1f5f9", color: "#2d3748" },
        overlay: {
            position: "fixed", top: 0, left: 0, right: 0, bottom: 0,
            backgroundColor: "rgba(0,0,0,0.6)", display: "flex", justifyContent: "center", alignItems: "center", zIndex: 1000
        },
        modal: { backgroundColor: "white", padding: "2rem", borderRadius: "15px", width: "400px" },
        input: { width: "100%", padding: "0.8rem", marginBottom: "1rem", borderRadius: "8px", border: "1px solid #cbd5e0" },
        btnPrimary: { backgroundColor: "#3182ce", color: "white", padding: "0.8rem 1.5rem", borderRadius: "8px", border: "none", fontWeight: "bold", cursor: "pointer" }
    };

    return (
        <div style={styles.container}>
            <NavBar />
            <main style={styles.main}>
                <div style={styles.headerCard}>
                    <h1 style={{ margin: 0, color: "#2d3748" }}>Vehículos</h1>
                    <button onClick={() => setIsModalOpen(true)} style={styles.btnPrimary}>
                        + Alta de Vehículo
                    </button>
                </div>

                <div style={styles.tableCard}>
                    <table style={styles.table}>
                        <thead>
                            <tr>
                                <th style={styles.th}>ID Vehículo</th>
                                <th style={styles.th}>Capacidad de Carga</th>
                                <th style={styles.th}>Estado Logístico</th>
                            </tr>
                        </thead>
                        <tbody>
                            {vehiculos.map(v => (
                                <tr key={v.id_vehiculo}>
                                    <td style={styles.td}>#{v.id_vehiculo}</td>
                                    <td style={{ ...styles.td, fontWeight: "bold" }}>{v.capacidadKG} KG</td>
                                    <td style={styles.td}>
                                        <span style={{ 
                                            backgroundColor: "#f0fff4", 
                                            color: "#38a169", 
                                            padding: "0.2rem 0.6rem", 
                                            borderRadius: "12px",
                                            fontSize: "0.85rem",
                                            fontWeight: "bold"
                                        }}>
                                            En Depósito
                                        </span>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                {isModalOpen && (
                    <div style={styles.overlay}>
                        <div style={styles.modal}>
                            <h2 style={{ marginTop: 0 }}>Nuevo Vehículo</h2>
                            
                            <label style={{ display: "block", marginBottom: "5px", color: "#4a5568" }}>
                                Capacidad Máxima (KG)
                            </label>
                            <input 
                                type="number"
                                style={styles.input} 
                                value={form.capacidadKG}
                                onChange={e => setForm({...form, capacidadKG: parseInt(e.target.value)})}
                                placeholder="Ej: 1500"
                            />

                            <div style={{ display: "flex", gap: "10px", justifyContent: "flex-end", marginTop: "1rem" }}>
                                <button onClick={() => setIsModalOpen(false)} style={{ background: "none", border: "none", cursor: "pointer", color: "#718096" }}>
                                    Cancelar
                                </button>
                                <button onClick={guardarVehiculo} style={styles.btnPrimary}>
                                    Confirmar Alta
                                </button>
                            </div>
                        </div>
                    </div>
                )}
            </main>
        </div>
    );
}