# Productos Service - Post-Contenido 2 U10

Proyecto base para el laboratorio de Metricas de Calidad y SonarQube.

## Objetivo

Configurar un Quality Gate personalizado en SonarQube, corregir un bug y al menos tres Code Smells detectados, ejecutar un segundo analisis con mejora de metricas e integrar la inspeccion al pipeline de GitHub Actions.

## Tecnologias

- Java 21
- Maven 3.9+
- Spring Boot 3
- SonarQube / SonarCloud
- JaCoCo
- GitHub Actions

## Quality Gate requerido

Nombre del Quality Gate: **Estandar Universidad**

| Condicion | Valor requerido |
| --- | --- |
| Bugs | Bloquear si es mayor que 0 |
| Coverage | Bloquear si es menor que 60% |
| Code Smells | Bloquear si es mayor que 5 |
| Duplicated Lines (%) | Bloquear si es mayor que 5% |

## Analisis inicial

Este primer estado conserva los hallazgos solicitados para documentar el punto de partida del laboratorio:

| Hallazgo inicial | Archivo | Estado |
| --- | --- | --- |
| Bug: `orElse(null)` en `buscar()` | `ProductoService` | Corregido con `NoSuchElementException` |
| Code Smell: inyeccion por campo con `@Autowired` | `ProductoService` | Corregido con inyeccion por constructor |
| Code Smell: `n.equals("")` | `ProductoService` | Corregido con `nombre.isBlank()` |
| Code Smell: alta complejidad en `procesarProducto()` | `ProductoService` | Corregido extrayendo `validarDatos()` |

## Correcciones aplicadas

| Requisito de la rubrica | Evidencia en el codigo |
| --- | --- |
| `buscar()` no retorna `null` | Lanza `NoSuchElementException` con mensaje descriptivo |
| Prueba para id inexistente | `buscarLanzaExcepcionCuandoProductoNoExiste()` |
| Inyeccion por constructor | `ProductoService(ProductoRepository productoRepository)` |
| Uso de `isBlank()` | Validacion de nombre en `validarDatos()` |
| Menor complejidad ciclomática | `procesarProducto()` delega validaciones a `validarDatos()` |

## Ejecucion local

```bash
mvn clean verify
```

## Analisis con SonarQube local

1. Iniciar SonarQube en Docker y abrir `http://localhost:9000`.
2. Crear el Quality Gate **Estandar Universidad** con las cuatro condiciones de la rubrica.
3. Asignar el Quality Gate al proyecto **Productos Service**.
4. Ejecutar:

```bash
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN
```

## Evidencias

Agregar aqui las capturas del dashboard:

| Momento | Captura | Resultado |
| --- | --- | --- |
| Antes de correcciones | `capturas/sonarqube-antes.png` | Pendiente |
| Despues de correcciones | `capturas/sonarqube-despues.png` | Pendiente |

## Progreso de commits sugerido

| Commit | Mensaje sugerido | Contenido |
| --- | --- | --- |
| 1 | `docs: documentar analisis inicial de sonarqube` | Proyecto base, JaCoCo, SonarQube y hallazgos iniciales |
| 2 | `fix: corregir bug y code smells de producto service` | Bug `orElse(null)`, constructor injection, `isBlank()` y extraccion de validaciones |
| 3 | `ci: automatizar analisis de calidad con github actions` | Workflow, README final y evidencias comparativas |
