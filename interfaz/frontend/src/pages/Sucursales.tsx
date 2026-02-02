import { useState, type CSSProperties } from 'react';
import { NavBar } from '../components/Nav';

interface Sucursal {
    id_sucursal: number;
    nombre_sucursal: string;
    descripcion_sucursal: string;
}

export function Sucursales() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [sucursales, setSucursales] = useState<Sucursal[]>([
        { id_sucursal: 1, nombre_sucursal: "Central Tandil", descripcion_sucursal: "Sede principal de distribución" },
    ]);
    const [form, setForm] = useState({ nombre_sucursal: "", descripcion_sucursal: "" });

    const guardarSucursal = () => {
        const nueva: Sucursal = {
            id_sucursal: sucursales.length + 1,
            ...form
        };
        setSucursales([...sucursales, nueva]);
        setForm({ nombre_sucursal: "", descripcion_sucursal: "" });
        setIsModalOpen(false);
        alert("Sucursal enviada al sistema de logística.");
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
        th: { backgroundColor: "#f8fafc", padding: "1rem", textAlign: "left", fontSize: "0.8rem", color: "#64748b", borderBottom: "1px solid #e2e8f0" },
        td: { padding: "1rem", borderBottom: "1px solid #f1f5f9" },
        overlay: {
            position: "fixed", top: 0, left: 0, right: 0, bottom: 0,
            backgroundColor: "rgba(0,0,0,0.6)", display: "flex", justifyContent: "center", alignItems: "center", zIndex: 1000
        },
        modal: { backgroundColor: "white", padding: "2rem", borderRadius: "15px", width: "400px", boxShadow: "0 20px 25px rgba(0,0,0,0.15)" },
        input: { width: "100%", padding: "0.8rem", marginBottom: "1rem", borderRadius: "8px", border: "1px solid #cbd5e0" },
        btnPrimary: { backgroundColor: "#3182ce", color: "white", padding: "0.8rem 1.5rem", borderRadius: "8px", border: "none", fontWeight: "bold", cursor: "pointer" }
    };

    return (
        <div style={styles.container}>
            <NavBar />
            <main style={styles.main}>
                <div style={styles.headerCard}>
                    <h1 style={{ margin: 0, color: "#2d3748" }}>Sucursales</h1>
                    <button onClick={() => setIsModalOpen(true)} style={styles.btnPrimary}>
                        + Nueva Sucursal
                    </button>
                </div>

                <div style={styles.tableCard}>
                    <table style={styles.table}>
                        <thead>
                            <tr>
                                <th style={styles.th}>ID</th>
                                <th style={styles.th}>Nombre</th>
                                <th style={styles.th}>Descripción</th>
                            </tr>
                        </thead>
                        <tbody>
                            {sucursales.map(s => (
                                <tr key={s.id_sucursal}>
                                    <td style={styles.td}>#{s.id_sucursal}</td>
                                    <td style={{ ...styles.td, fontWeight: "bold" }}>{s.nombre_sucursal}</td>
                                    <td style={styles.td}>{s.descripcion_sucursal}</td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                {isModalOpen && (
                    <div style={styles.overlay}>
                        <div style={styles.modal}>
                            <h2 style={{ marginTop: 0 }}>Nueva Sucursal</h2>
                            <label style={{ display: "block", marginBottom: "5px" }}>Nombre</label>
                            <input 
                                style={styles.input} 
                                value={form.nombre_sucursal}
                                onChange={e => setForm({...form, nombre_sucursal: e.target.value})}
                            />
                            <label style={{ display: "block", marginBottom: "5px" }}>Descripción</label>
                            <textarea 
                                style={{ ...styles.input, height: "100px" }} 
                                value={form.descripcion_sucursal}
                                onChange={e => setForm({...form, descripcion_sucursal: e.target.value})}
                            />
                            <div style={{ display: "flex", gap: "10px", justifyContent: "flex-end" }}>
                                <button onClick={() => setIsModalOpen(false)} style={{ background: "none", border: "none", cursor: "pointer" }}>Cancelar</button>
                                <button onClick={guardarSucursal} style={styles.btnPrimary}>Guardar</button>
                            </div>
                        </div>
                    </div>
                )}
            </main>
        </div>
    );
}