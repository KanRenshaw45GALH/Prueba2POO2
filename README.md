# Gestor de Tareas y Recordatorios

Sistema de gestión de elementos (tareas y recordatorios) con soporte para dos tipos de usuarios: **General** y **Premium**.

---

## Descripción

Esta aplicación permite a los usuarios crear, gestionar y visualizar tareas y recordatorios. El sistema diferencia entre usuarios con suscripción gratuita (General) y de pago (Premium), aplicando límites y permisos según el tipo de cuenta.

---

## Estructura del Proyecto

proyecto/
│
├── catalogo/
│   ├── Estado.java              # Enum: estados de una tarea
│   └── Prioridad.java           # Enum: niveles de prioridad
| 
├── estrategia/
|   ├── EstrategiaElemento.java # Interfaz: Ejecucion de la lista de elementos
|   ├── EstrategiaGuardar.java  # Clase concreta para el guardado de elementos
|   ├── EstrategiaEditar.java   # Clase concreta para la edicion de elementos
|   ├── EstrategiaEliminar.java # Clase concreta para la eliminacion de elementos
|   └── CompartirHilo.java      # Clase concreta para el flujo de compartidos por medio de hilos
|
│
├── modeloElemento/
│   ├── AccionesElemento.java    # Interfaz: acciones sobre elementos
│   ├── Elemento.java            # Clase abstracta base para elementos
│   ├── ElementoTarea.java       # Clase concreta: Tarea
│   └── ElementoRecordatorio.java# Clase concreta: Recordatorio
│
├── modeloUsuario/
│   ├── AccionesUsuario.java     # Interfaz: acciones sobre usuarios
│   ├── Usuario.java             # Clase abstracta base para usuarios
│   ├── UsuarioGeneral.java      # Clase concreta: Usuario con plan gratuito
│   ├── UsuarioPremium.java      # Clase concreta: Usuario con plan de pago
|   └── GestorUsuario.java       # Clase concreta: Maneja la transformacion de un Usuario General a uno Premiun y viceversa
│
├── EntradaDatos/
|   └──  EntradaDatos.java       # Clase concreta: Siver de Menu para todo el Gestor de Tareas interactuando con todos los metodos y clases
|
└── Main/
    └── Main.java                # Clase principal - punto de entrada


---


## Módulos y Clases

### Catálogo (`catalogo/`)

**`Estado`** — Enum con los posibles estados de una tarea:
- `EN_PROGRESO`, `COMPLETADO`, `VENCIDA`, `CANCELADA`, `PENDIENTE`

**`Prioridad`** — Enum con los niveles de prioridad:
- `ALTA`, `MEDIA`, `BAJA`

---


### Modelo de Elementos (`modeloElemento/`)
**`AccionesElemento`** *(Interfaz)*
Define el contrato para todo elemento del sistema:
- `crearElemento()` — crea el elemento
- `compartirElemento()` — comparte el elemento con otros usuarios
- `imprimirElementos()` — imprime los detalles del elemento

**`Elemento`** *(Clase abstracta)*
Base para todos los elementos. Contiene atributos comunes:
- `id`, `titulo`, `descripcion`, `prioridad`
- `fechaCreacion`, `fechaLimite`
- `cantidadColaboradores`, `usuario` (propietario)

**`ElementoTarea`** *(Extiende `Elemento`)*
Representa una tarea con atributo adicional:
- `estado` (`Estado`) — estado actual de la tarea

**`ElementoRecordatorio`** *(Extiende `Elemento`)*
Representa un recordatorio con atributos adicionales:
- `fechaRecordatorio` — fecha en que se activa la alerta
- `fechaActual` — fecha de referencia
- `activarAlerta()` — activa la alerta si `fechaRecordatorio == fechaActual`

---


### Modelo de Usuarios (`modeloUsuario/`)
**`AccionesUsuario`** *(Interfaz)*
Define el contrato para todo usuario:
- `modoSuscripcion()` — muestra el modo de suscripción actual
- `verificarUsuario()` — verifica las credenciales del usuario
- `imprimirUsuario()` — imprime los datos del usuario

**`Usuario`** *(Clase abstracta)*
Base para todos los usuarios. Atributos comunes:
- `nombreCompleto`, `edad`, `email`, `password`

**`UsuarioGeneral`** *(Extiende `Usuario`)*
Usuario con plan gratuito. Incluye:
- Límites: 8 tareas, 4 recordatorios, 1 elemento compartido
- Métodos de conteo: `conteoTarea()`, `conteoRecordatorio()`, `conteoCompartido()`
- `activarSuscripcion()` — activa el plan premuim

**`UsuarioPremium`** *(Extiende Usuario)*
Usuario con plan de pago. Incluye:
- Sin límites de elementos
- `pagarSuscripcion()` — procesa el pago de la suscripción
- `cancelarSuscripcion()` — cancela el plan activo
- Acceso completo y permiso de compartir de manera ilimitada habilita por defecto

**`GestorUsuario`**
- `convertirAPremuim` — procesa el pago de la suscripción
- `convertirAGeneral` — cancela el plan activo

---


## Diagrama de Herencia

```
AccionesUsuario (interfaz)
        |
     Usuario (abstracta)
        |              |     
     UsuarioGeneral  /  UsuarioPremium

AccionesElemento (interfaz)
        |
      Elemento (abstracta)
        |                |
      ElementoTarea  /  ElementoRecordatorio
```


## Diagrama de Herencia

```
AccionesUsuario (interfaz)
        |
     Usuario (abstracta)
        |              |     
     UsuarioGeneral  /  UsuarioPremium

AccionesElemento (interfaz)
        |
      Elemento (abstracta)
        |                |
      ElementoTarea  /  ElementoRecordatorio
```

---

## Ejemplo de uso del programa
\\\

\\\\
---

## Comparativa: Usuario General vs Premium

| Característica              | Usuario General| Usuario Premium |
|-----------------------------|----------------|-----------------|
| Límite de tareas            | 8              | Ilimitado       |
| Límite de recordatorios     | 4              | Ilimitado       |
| Elementos compartidos       | 1              | Ilimitado       |
| Costo                       | Gratis         | De pago         |

---

## Autor

GERARDO ANDRES LANDAVERRY HUEZO #00137224;
KRISTEN NICOLE CRUZ RODRIGUEZ #00051524 ;
FERNANDO JOSUE ESCAMILLA RIVERA #00053324;
DIEGO OTONIEL MENDEZ CABRERA #00010023;
RENE EDUARDO GONZALEZ IRAHETA #00128624;



----


