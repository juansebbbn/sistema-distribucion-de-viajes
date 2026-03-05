# Sistema de Distribución de Viajes

## Descripción del Proyecto

Este es un sistema de gestión de distribución de viajes diseñado para optimizar la asignación de vehículos y conductores a diferentes rutas de transporte. El sistema implementa un algoritmo inteligente que distribuye eficientemente la carga y los recursos humanos para maximizar la eficiencia logística.

## Arquitectura

El proyecto sigue una arquitectura hexagonal (Ports and Adapters) con las siguientes capas:

### Core del Dominio
- **Modelos**: Entidades principales del negocio como Sucursal, Vehiculo, Conductor, ViajeDiario, Ruta, Mercancia, HojaDeRuta y Cliente
- **Servicios**: Lógica de negocio principal incluyendo el algoritmo de asignación y orquestación
- **Puertos**: Interfaces que definen los contratos de entrada y salida del dominio

### Componentes Principales

#### Implementacion_algoritmo_asignacion
Servicio principal que implementa la lógica de asignación de viajes:
- Mapea sucursales con sus viajes correspondientes
- Asigna vehículos según capacidad de carga
- Asigna conductores según experiencia y requisitos especiales
- Genera hojas de ruta para cada asignación

#### OrquestacionAlgortimo
Coordina la ejecución del algoritmo de asignación con la obtención de datos del sistema.

#### ObtencionDatosDelSistema
Servicio encargado de recuperar los datos necesarios para el funcionamiento del algoritmo.

## Funcionalidades

### Algoritmo de Asignación
El sistema implementa las siguientes reglas de asignación:

1. **Agrupación por Origen**: Los viajes se agrupan por sucursal de origen
2. **Optimización de Carga**: Los viajes se asignan a vehículos maximizando la utilización de capacidad
3. **Asignación de Conductores**: Los conductores se asignan según:
   - Años de experiencia requeridos por la ruta
   - Requisitos de carnet especial cuando la ruta lo exige
4. **Actualización de Estados**: El sistema actualiza la ubicación de vehículos y conductores entre sucursales

### Características Técnicas
- **Java 17**: Lenguaje principal de desarrollo
- **Spring Boot**: Framework para la aplicación
- **Lombok**: Reducción de código boilerplate
- **JUnit 5**: Framework de testing
- **Maven**: Gestión de dependencias y construcción

## Estructura del Proyecto

```
src/
├── main/java/com/juan/appgestioninventario_viajes/
│   ├── core/
│   │   ├── modelos/          # Entidades del dominio
│   │   ├── servicios/         # Lógica de negocio
│   │   └── puertos/          # Interfaces del dominio
│   └── AppGestionInventarioViajesApplication.java
└── test/java/                 # Tests unitarios
```

## Modelos de Datos

### Sucursal
Representa las sucursales del sistema con sus vehículos y conductores disponibles.

### Vehiculo
Define los vehículos con capacidad de carga y características específicas.

### Conductor
Contiene información de los conductores incluyendo experiencia y certificaciones.

### ViajeDiario
Representa un viaje específico con mercancía, ruta y cliente asociado.

### Ruta
Define las rutas de transporte con requisitos específicos de experiencia y certificaciones.

### HojaDeRuta
Documento generado para cada asignación de vehículo y conductor a un conjunto de viajes.

## Ejecución del Sistema

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

### Compilación y Ejecución
```bash
mvn clean compile
mvn test
mvn spring-boot:run
```

### Ejecución de Tests
```bash
mvn test
```

## Tests Unitarios

El proyecto incluye una suite completa de tests unitarios para el algoritmo de asignación que cubren:

- Mapeo correcto de viajes a sucursales
- Asignación de vehículos según capacidad
- Asignación de conductores según requisitos
- Manejo de casos límite y errores
- Verificación de estados persistentes

## Flujo de Trabajo

1. **Obtención de Datos**: El sistema recupera información de sucursales, vehículos, conductores y viajes
2. **Mapeo de Viajes**: Se agrupan los viajes por sucursal de origen
3. **Asignación de Recursos**: Para cada sucursal se asignan vehículos y conductores óptimos
4. **Generación de Rutas**: Se crean las hojas de ruta con los viajes asignados
5. **Actualización de Estados**: Se actualiza la ubicación de recursos entre sucursales

## Consideraciones de Diseño

- **Separación de Responsabilidades**: Cada componente tiene una responsabilidad única y bien definida
- **Inmutabilidad**: Los modelos son inmutables cuando es posible para garantizar consistencia
- **Testabilidad**: El diseño facilita la creación de tests unitarios aislados
- **Extensibilidad**: La arquitectura permite fácil adición de nuevas funcionalidades

## Estado Actual del Proyecto

El sistema se encuentra en fase de desarrollo con el algoritmo principal de asignación implementado y probado. Los tests unitarios cubren los escenarios principales de funcionamiento y validan la correcta implementación de la lógica de negocio.

## Próximos Pasos

- Implementación de persistencia de datos
- Desarrollo de API REST para exposición de servicios
- Desarrollo de interfaz de usuario