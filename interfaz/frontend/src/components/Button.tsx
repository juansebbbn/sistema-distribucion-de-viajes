export function Button({ texto }: { texto: string }) {
  return (
    <button style={misEstilos.boton}>
        <p style={misEstilos.parrafo}>{texto}</p>
    </button>
  );
}

const misEstilos = {
  boton: {
    backgroundColor: '#28a745',
    padding: '12px',
    cursor: 'pointer'
  },
  parrafo: {
    color: 'white',
    fontSize: '16px'
  }
};