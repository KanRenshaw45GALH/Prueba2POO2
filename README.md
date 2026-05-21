# Gestor de Tareas y Recordatorios 📋⏰

Sistema de gestión de elementos (tareas y recordatorios) desarrollado en **Java** con **Gradle**, con soporte para dos tipos de usuarios: **General** y **Premium**.

---

## Descripción

Esta aplicación permite a los usuarios crear, gestionar y visualizar tareas y recordatorios mediante una interfaz interactiva por consola.

El sistema diferencia entre usuarios con suscripción gratuita (**General**) y usuarios con suscripción de pago (**Premium**), aplicando límites y permisos según el tipo de cuenta.

El proyecto implementa conceptos fundamentales de Programación Orientada a Objetos:

- Herencia
- Polimorfismo
- Interfaces
- Clases abstractas
- Enumeraciones (`enum`)
- Manejo de hilos (`Thread`)
- Bloqueos concurrentes (`ReentrantLock`)
- Patrón de diseño Strategy

Además, el sistema permite compartir elementos entre usuarios utilizando múltiples hilos de ejecución de manera segura.

---

## Tecnologías Utilizadas 💻

- Java 17+
- Gradle
- Programación Orientada a Objetos (POO)
- Threads y concurrencia
- Colecciones (`ArrayList`, `List`)
- Manejo de fechas (`LocalDate`)
- Patrón Strategy

---

## Estructura del Proyecto

```text
proyecto/
│
├── catalogo/
│   ├── Estado.java
│   └── Prioridad.java
│
├── estrategia/
│   ├── EstrategiaElemento.java
│   ├── EstrategiaGuardar.java
│   ├── EstrategiaEditar.java
│   ├── EstrategiaEliminar.java
│   └── CompartirHilo.java
│
├── modeloElemento/
│   ├── AccionesElemento.java
│   ├── Elemento.java
│   ├── ElementoTarea.java
│   └── ElementoRecordatorio.java
│
├── modeloUsuario/
│   ├── AccionesUsuario.java
│   ├── Usuario.java
│   ├── UsuarioGeneral.java
│   ├── UsuarioPremium.java
│   └── GestorUsuario.java
│
├── ui/
│   └── EntradaDatos.java
│
└── Main/
    └── Main.java
```

---

# Módulos y Clases

---

## Catálogo (`catalogo/`)

Contiene las enumeraciones utilizadas por el sistema.

### `Estado`

Enum que representa el estado de una tarea:

- `EN_PROGRESO`
- `COMPLETADO`
- `VENCIDA`
- `CANCELADA`
- `PENDIENTE`

### `Prioridad`

Enum que representa el nivel de prioridad:

- `ALTA`
- `MEDIA`
- `BAJA`

---

## Modelo de Elementos (`modeloElemento/`)

Gestiona las tareas y recordatorios del sistema.

### `AccionesElemento` *(Interfaz)*

Define las acciones principales de cualquier elemento:

- `crearElemento()`
- `imprimirElementos()`

---

### `Elemento` *(Clase Abstracta)*

Clase base para todos los elementos.

#### Atributos principales

- `id`
- `titulo`
- `descripcion`
- `prioridad`
- `fechaCreacion`
- `fechaLimite`
- `usuario`
- `colaboradores`
- `cantidadColaboradores`

#### Funcionalidades

- Creación de elementos
- Validación de datos
- Gestión de colaboradores
- Impresión de información

---

### `ElementoTarea` *(Extiende Elemento)*

Representa una tarea dentro del sistema.

#### Características

- Posee un estado (`Estado`)
- Puede cambiar entre:
  - `PENDIENTE`
  - `EN_PROGRESO`
  - `CANCELADA`
  - `COMPLETADO`

---

### `ElementoRecordatorio` *(Extiende Elemento)*

Representa un recordatorio con activación automática.

#### Atributos adicionales

- `fechaRecordatorio`
- `fechaActual`
- `alerta`

#### Método destacado

```java
activarAlerta()
```

Activa la alerta cuando la fecha actual alcanza o supera la fecha del recordatorio.

---

## Modelo de Usuarios (`modeloUsuario/`)

Gestiona toda la lógica relacionada con usuarios y suscripciones.

### `AccionesUsuario` *(Interfaz)*

Define las acciones principales de cualquier usuario:

- `crearElemento()`
- `modoSuscripcion()`
- `verificarUsuario()`
- `imprimirUsuario()`

---

### `Usuario` *(Clase Abstracta)*

Clase base para todos los usuarios.

#### Atributos principales

- `nombreCompleto`
- `edad`
- `email`
- `password`
- `accesoCompleto`
- `fechaActual`
- `elemento`

#### Funcionalidades

- Verificación de usuario
- Compartir elementos
- Eliminar elementos
- Listar elementos

---

### `UsuarioGeneral` *(Extiende Usuario)*

Usuario gratuito del sistema.

#### Restricciones

| Característica | Límite |
|----------------|---------|
| Tareas | 8 |
| Recordatorios | 4 |
| Compartidos | 1 |

#### Métodos destacados

- `conteoTarea()`
- `conteoRecordatorio()`
- `modoSuscripcion()`

---

### `UsuarioPremium` *(Extiende Usuario)*

Usuario con acceso completo.

#### Beneficios

- Tareas ilimitadas
- Recordatorios ilimitados
- Compartidos ilimitados
- Acceso completo habilitado

#### Funcionalidades

- Pago de suscripción
- Manejo de fechas de suscripción
- Compartir elementos sin restricciones

---

### `GestorUsuario`

Clase encargada de transformar usuarios entre:

- `UsuarioGeneral → UsuarioPremium`
- `UsuarioPremium → UsuarioGeneral`

Conservando:

- Datos personales
- Lista de elementos
- Estado del usuario

---

## Estrategia (`estrategia/`)

Implementación del patrón de diseño **Strategy**.

---

### `EstrategiaElemento` *(Interfaz)*

Define el contrato de las estrategias:

```java
ejecutar(List<Elemento> elementos, Elemento elemento)
```

---

### `EstrategiaGuardar`

Permite:

- Guardar elementos
- Validar elementos duplicados

---

### `EstrategiaEditar`

Permite editar:

- Título
- Descripción
- Prioridad

Incluye manejo de errores y validaciones.

---

### `EstrategiaEliminar`

Permite eliminar elementos de manera segura.

---

### `CompartirHilo`

Clase concurrente que implementa `Runnable`.

#### Funcionalidades

- Compartir elementos usando múltiples hilos
- Evitar condiciones de carrera
- Utilizar `ReentrantLock`

#### Validaciones

- Evita compartir elementos duplicados
- Evita compartir elementos consigo mismo

---

## Interfaz de Usuario (`ui/`)

### `EntradaDatos`

Clase principal de interacción con consola.

#### Funcionalidades del menú

- Registro de usuarios
- Inicio de sesión
- Crear tareas
- Crear recordatorios
- Ver pendientes
- Completar tareas
- Editar elementos
- Eliminar elementos
- Compartir elementos
- Cambiar suscripción
- Visualizar información del usuario

---

## Diagrama de Herencia

```text
AccionesUsuario (Interfaz)
        │
     Usuario (Abstracta)
       / \
      /   \
UsuarioGeneral   UsuarioPremium


AccionesElemento (Interfaz)
        │
     Elemento (Abstracta)
       / \
      /   \
ElementoTarea   ElementoRecordatorio
```

---

## Diagrama de Estrategias

```text
             EstrategiaElemento
                    │
      ┌─────────────┼─────────────┐
      │             │             │
EstrategiaGuardar  EstrategiaEditar  EstrategiaEliminar
```

---

## Diagrama de Hilos

```text
Usuario Activo
      │
      ▼
CompartirHilo (Runnable)
      │
      ▼
Thread
      │
      ▼
ReentrantLock
      │
      ▼
Compartir Elemento de forma segura
```

---

## Ejemplo de Uso del Programa

```text
BIENVENIDO AL SISTEMA:
RECORDATORIO DE TAREAS

[1] Iniciar sesión
[2] Registrar nuevo usuario
[3] Salir
```

---

## Ejecución del Proyecto ▶️

### Compilar el proyecto

```bash
gradle build
```

### Ejecutar el proyecto

```bash
gradle run
```

O ejecutar directamente:

```bash
Main.java
```

---

## Comparativa: Usuario General vs Premium

| Característica | Usuario General | Usuario Premium |
|----------------|-----------------|-----------------|
| Límite de tareas | 8 | Ilimitado |
| Límite de recordatorios | 4 | Ilimitado |
| Compartidos | 1 | Ilimitado |
| Acceso completo | ❌ | ✅ |
| Costo | Gratis | $4.99 |

---

## Características Destacadas ⭐

- Arquitectura orientada a objetos
- Sistema modular y escalable
- Uso de concurrencia con hilos
- Manejo de errores y validaciones
- Patrón Strategy implementado
- Gestión de usuarios y suscripciones
- Compartidos concurrentes seguros

---

## Posibles Mejoras Futuras 🚀

- Persistencia en base de datos
- Interfaz gráfica
- Notificaciones automáticas
- API REST
- Integración con calendario
- Exportación de tareas
- Sistema de autenticación avanzada

---

## Autores 👨‍💻

- GERARDO ANDRES LANDAVERRY HUEZO — `#00137224`
- KRISTEN NICOLE CRUZ RODRIGUEZ — `#00051524`
- FERNANDO JOSUE ESCAMILLA RIVERA — `#00053324`
- DIEGO OTONIEL MENDEZ CABRERA — `#00010023`
- RENE EDUARDO GONZALEZ IRAHETA — `#00128624`

---

## Licencia 📄

Proyecto desarrollado con fines académicos y educativos.
