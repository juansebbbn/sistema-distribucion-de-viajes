import { type CSSProperties, useState } from 'react';
import { NavBar } from '../components/Nav';

interface Conductor {
    id_conductor: number;
    anios_experiencia: number;
    carnet_super_especial: boolean;
}

export function Conductores() {
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [conductores, setConductores] = useState<Conductor[]>([
        { id_conductor: 1, anios_experiencia: 5, carnet_super_especial: true },
        { id_conductor: 2, anios_experiencia: 2, carnet_super_especial: false },
    ]);
    const [form, setForm] = useState({ anios_experiencia: 0, carnet_super_especial: false });

    const guardarConductor = () => {
        const nuevo: Conductor = {
            id_conductor: conductores.length + 1,
            anios_experiencia: Number(form.anios_experiencia),
            carnet_super_especial: form.carnet_super_especial
        };
        setConductores([...conductores, nuevo]);
        setForm({ anios_experiencia: 0, carnet_super_especial: false });
        setIsModalOpen(false);
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
        th: { backgroundColor: "#f8fafc", padding: "1rem", textAlign: "left", fontSize: "0.8rem", color: "#64748b", borderBottom: "1px solid #e2e8f0", textTransform: "uppercase" },
        td: { padding: "1rem", borderBottom: "1px solid #f1f5f9", color: "#2d3748" },
        overlay: {
            position: "fixed", top: 0, left: 0, right: 0, bottom: 0,
            backgroundColor: "rgba(0,0,0,0.6)", display: "flex", justifyContent: "center", alignItems: "center", zIndex: 1000
        },
        modal: { backgroundColor: "white", padding: "2rem", borderRadius: "15px", width: "400px" },
        input: { width: "100%", padding: "0.8rem", marginBottom: "1rem", borderRadius: "8px", border: "1px solid #cbd5e0" },
        checkboxContainer: { display: "flex", alignItems: "center", gap: "10px", marginBottom: "1.5rem" },
        btnPrimary: { backgroundColor: "#3182ce", color: "white", padding: "0.8rem 1.5rem", borderRadius: "8px", border: "none", fontWeight: "bold", cursor: "pointer" }
    };

    return (
        <div style={styles.container}>
            <NavBar />
            <main style={styles.main}>
                <div style={styles.headerCard}>
                    <h1 style={{ margin: 0, color: "#2d3748" }}>Conductores</h1>
                    <button onClick={() => setIsModalOpen(true)} style={styles.btnPrimary}>
                        + Registrar Conductor
                    </button>
                </div>

                <div style={styles.tableCard}>
                    <table style={styles.table}>
                        <thead>
                            <tr>
                                <th style={styles.th}>ID</th>
                                <th style={styles.th}>Años de Experiencia</th>
                                <th style={styles.th}>Carnet Especial</th>
                                <th style={styles.th}>Estado</th>
                            </tr>
                        </thead>
                        <tbody>
                            {conductores.map(c => (
                                <tr key={c.id_conductor}>
                                    <td style={styles.td}>#{c.id_conductor}</td>
                                    <td style={styles.td}>{c.anios_experiencia} años</td>
                                    <td style={styles.td}>
                                        {c.carnet_super_especial ? "✅ Sí" : "❌ No"}
                                    </td>
                                    <td style={styles.td}>
                                        <span style={{ color: "#38a169", fontWeight: "bold" }}>Disponible</span>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>

                {/* MODAL DE SUBIDA */}
                {isModalOpen && (
                    <div style={styles.overlay}>
                        <div style={styles.modal}>
                            <h2 style={{ marginTop: 0 }}>Nuevo Conductor</h2>
                            
                            <label style={{ display: "block", marginBottom: "5px" }}>Años de Experiencia</label>
                            <input 
                                type="number"
                                style={styles.input} 
                                value={form.anios_experiencia}
                                onChange={e => setForm({...form, anios_experiencia: parseInt(e.target.value)})}
                            />

                            <div style={styles.checkboxContainer}>
                                <input 
                                    type="checkbox" 
                                    id="carnet"
                                    checked={form.carnet_super_especial}
                                    onChange={e => setForm({...form, carnet_super_especial: e.target.checked})}
                                />
                                <label htmlFor="carnet">¿Posee carnet super especial?</label>
                            </div>

                            <div style={{ display: "flex", gap: "10px", justifyContent: "flex-end" }}>
                                <button onClick={() => setIsModalOpen(false)} style={{ background: "none", border: "none", cursor: "pointer" }}>Cancelar</button>
                                <button onClick={guardarConductor} style={styles.btnPrimary}>Guardar</button>
                            </div>
                        </div>
                    </div>
                )}
            </main>
        </div>
    );
}