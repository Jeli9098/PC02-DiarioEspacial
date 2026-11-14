# Diario espacial – PC02 DAM 2026-2

App Android (Kotlin + Jetpack Compose) que consume la API abierta de la NASA (APOD y NeoWs) y guarda los datos en SQLite con Room.

## Integrantes
| Rol | Nombre | Opción |
|---|---|---|
| Alumno 1 | _(nombre)_ | Imágenes (APOD) |
| Alumno 2 | _(nombre)_ | Asteroides (NeoWs) |

## Cómo ejecutar
1. Clonar el repositorio y abrirlo en Android Studio.
2. Generar una API key en https://api.nasa.gov (sección *Generate API Key*).
3. En el archivo **`local.properties`** (raíz del proyecto, NO se sube a GitHub) agregar:
   ```
   NASA_API_KEY=tu_clave_aqui
   ```
4. *Sync Gradle* y ejecutar en un emulador o dispositivo con internet.

La clave se lee en `app/build.gradle.kts` y se expone como `BuildConfig.NASA_API_KEY`; `RetrofitClient` la agrega a cada petición con un interceptor.

## Arquitectura
```
data/
  remote/      RetrofitClient (común), apod/ y neows/ (DTO + interfaz Retrofit)
  local/       AppDatabase (común), ApodEntity/ApodDao, AsteroidEntity/AsteroidDao
  mapper/      DTO -> Entity
  repository/  ApodRepository, AsteroidRepository
ui/
  navigation/  Routes, AppNavigation (barra inferior, común)
  components/  FavoriteButton, LoadingView, EmptyView (común)
  apod/        pantallas + ViewModel (Alumno 1)
  asteroids/   pantallas + ViewModel (Alumno 2)
```
Flujo: API → DTO → Entity → Room → `Flow` → ViewModel (`StateFlow`) → pantalla. Las pantallas siempre leen de Room.

## Acuerdo de coordinación (base común)
- **_(nombre del Alumno 1)_** preparó la base común en la rama `setup/base-comun` (PR #1, revisado y aprobado por _(nombre del Alumno 2)_):
  - Configuración de Gradle, permiso de internet, `RetrofitClient` con interceptor de la API key.
  - `AppDatabase` con las **dos** tablas ya registradas (`ApodEntity`, `AsteroidEntity`) y sus DAO como *stubs*.
  - `AppNavigation` con la barra inferior y los grafos `apodGraph` / `asteroidGraph` como *stubs*.
  - Componentes compartidos (`FavoriteButton`, `LoadingView`, `EmptyView`) y `DateUtils`.
- Después del merge de la base, cada integrante creó su rama desde `main` actualizado:
  - Alumno 1: `feature/imagenes-apod`
  - Alumno 2: `feature/asteroides-neows`
- Regla: cada uno **solo modifica los archivos de su opción** (su Entity, DAO, DTO, Repository, ViewModel, pantallas y archivo de navegación). Si se necesita cambiar un archivo común, se avisa al compañero y se hace en un PR aparte.
- Cada PR es revisado por el otro integrante con al menos un comentario antes de fusionarse a `main` (rama protegida).
