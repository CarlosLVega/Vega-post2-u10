# Productos Service - Post-Contenido 2 U10


Proyecto finalizado para el laboratorio de la Unidad 10: Metricas de Calidad y SonarQube. La entrega incluye configuracion de JaCoCo, SonarQube/SonarCloud, correccion de un bug critico, correccion de tres Code Smells, pruebas unitarias, evidencias comparativas y automatizacion con GitHub Actions.

## Estado Final

| Elemento solicitado | Estado |
| --- | --- |
| Proyecto Java con estructura `src/` | Completado |
| JaCoCo configurado | Completado |
| SonarQube configurado | Completado |
| Quality Gate **Estandar Universidad** documentado | Completado |
| Bug `orElse(null)` corregido | Completado |
| Tres Code Smells corregidos | Completado |
| Prueba para id inexistente | Completado |
| Segundo analisis documentado | Completado |
| GitHub Actions integrado | Completado |
| Capturas comparativas incluidas | Completado |

## Tecnologias

- Java 21
- Maven 3.9+
- Spring Boot 3
- H2 Database
- JUnit 5 y Mockito
- JaCoCo
- SonarQube / SonarCloud
- GitHub Actions

## Quality Gate

Nombre configurado: **Estandar Universidad**

| Condicion | Regla |
| --- | --- |
| Bugs | Bloquear si es mayor que 0 |
| Coverage | Bloquear si es menor que 60% |
| Code Smells | Bloquear si es mayor que 5 |
| Duplicated Lines (%) | Bloquear si es mayor que 5% |

## Correcciones Aplicadas

| Hallazgo | Antes | Despues |
| --- | --- | --- |
| Bug en `buscar()` | `repo.findById(id).orElse(null)` | `orElseThrow()` con `NoSuchElementException` |
| Inyeccion de dependencias | `@Autowired` en campo | Constructor injection |
| Validacion de nombre | `n.equals("")` | `nombre == null || nombre.isBlank()` |
| Complejidad ciclomática | Validaciones dentro de `procesarProducto()` | Metodo privado `validarDatos()` |

## Pruebas

La verificacion final se ejecuto con:

```bash
mvn clean verify
```

Resultado final:

| Indicador | Resultado |
| --- | --- |
| Tests ejecutados | 12 |
| Fallas | 0 |
| Errores | 0 |
| JaCoCo XML | `target/site/jacoco/jacoco.xml` |
| Cobertura aproximada de lineas | 93% |

## Comparacion del Analisis

| Metrica | Analisis inicial | Analisis final |
| --- | --- | --- |
| Bugs | 1 bug por retorno `null` en `buscar()` | 0 bugs |
| Code Smells | `@Autowired`, `equals("")`, complejidad alta | 3 Code Smells corregidos |
| Coverage | Reporte inicial de JaCoCo | Cobertura superior al 60% |
| Duplicated Lines (%) | Validado contra Quality Gate | Dentro del limite esperado |
| Quality Gate | Fallo inicial esperado por bug y smells | Estado final preparado para aprobar |

## Evidencias

### Dashboard inicial

![Dashboard antes de correcciones](capturas/sonarqube-antes.png)

### Dashboard final

![Dashboard despues de correcciones](capturas/sonarqube-despues.png)

## Ejecucion Local

Compilar, probar y generar cobertura:

```bash
mvn clean verify
```

Ejecutar la aplicacion:

```bash
mvn spring-boot:run
```

Endpoints:

| Metodo | Ruta | Descripcion |
| --- | --- | --- |
| GET | `/api/productos` | Lista productos |
| GET | `/api/productos/{id}` | Busca producto por id |
| POST | `/api/productos` | Crea producto validando nombre, precio y stock |

## SonarQube Local

Levantar SonarQube:

```bash
docker compose up -d
```

Ejecutar analisis:

```bash
mvn clean verify sonar:sonar -Dsonar.token=TU_TOKEN
```

El archivo `sonar-project.properties` contiene las rutas de fuentes, pruebas, binarios y reporte JaCoCo.

## GitHub Actions

El workflow `.github/workflows/ci.yml` ejecuta el pipeline en cada `push` y `pull_request` hacia `main`.

Para SonarCloud se configuraron estas variables esperadas:

| Nombre | Tipo | Uso |
| --- | --- | --- |
| `SONAR_TOKEN` | Secret | Token de autenticacion de SonarCloud |
| `SONAR_ORGANIZATION` | Variable | Organizacion del proyecto en SonarCloud |

## Commits de la Entrega

| Commit | Mensaje | Contenido |
| --- | --- | --- |
| 1 | `docs: documentar analisis inicial de sonarqube` | Proyecto base, JaCoCo, SonarQube y hallazgos iniciales |
| 2 | `fix: corregir bug y code smells de producto service` | Correccion del bug y de tres Code Smells |
| 3 | `ci: automatizar analisis de calidad con github actions` | Workflow, README final y evidencias comparativas |
