# Guía de uso – PC02 Diario espacial

Cada carpeta = un PR. Copia los archivos respetando las rutas (`app/src/main/java/...`).
Si tu paquete no es `com.esan.diarioespacial`, cambia la línea `package` e `import` de cada archivo.

## Orden
1. **PR 1 – `setup/base-comun`** (lo hace uno, el otro revisa): carpeta `0_base_comun`
2. **PR 2 – `feature/imagenes-apod`** (Alumno 1): carpeta `1_alumno1_imagenes` (reemplaza los stubs ApodEntity, ApodDao, ApodNavigation)
3. **PR 3 – `feature/asteroides-neows`** (Alumno 2): carpeta `2_alumno2_asteroides` (reemplaza los stubs AsteroidEntity, AsteroidDao, AsteroidNavigation)

PR 2 y PR 3 no tocan los mismos archivos, así que no hay conflictos.

## Commits sugeridos

**Base común**
1. `Configura dependencias de Retrofit, Room, Navigation y Coil`
2. `Lee la API key desde local.properties con BuildConfig`
3. `Agrega RetrofitClient con interceptor de api_key`
4. `Agrega AppDatabase con entidades y DAO base`
5. `Agrega navegación principal con barra inferior`
6. `Agrega componentes compartidos y DateUtils`
7. `Agrega README con acuerdo de coordinación`

**Alumno 1**
1. `Agrega ApodDto y ApodApi`
2. `Completa ApodEntity y ApodDao con favoritos`
3. `Agrega mapper de ApodDto a ApodEntity`
4. `Agrega ApodRepository`
5. `Agrega ApodViewModel con StateFlow`
6. `Agrega lista de imágenes APOD`
7. `Agrega pantalla de detalle APOD`
8. `Agrega pantalla Mis imágenes favoritas`
9. `Conecta navegación interna de Imágenes`

**Alumno 2**
1. `Agrega DTOs del feed NeoWs y NeoWsApi`
2. `Completa AsteroidEntity y AsteroidDao con favoritos`
3. `Agrega mapper del feed agrupado por fecha a AsteroidEntity`
4. `Agrega AsteroidRepository`
5. `Agrega AsteroidViewModel con filtro de peligrosos`
6. `Agrega lista de asteroides con formato de unidades`
7. `Agrega pantalla de detalle de asteroide`
8. `Agrega pantalla Mis asteroides`
9. `Conecta navegación interna de Asteroides`

## Checklist antes de entregar
- [ ] `local.properties` NO aparece en ningún commit (`git log --all -- local.properties` vacío)
- [ ] Rama `main` protegida (Settings → Branches → Require pull request + 1 approval)
- [ ] Cada PR tiene ≥1 comentario del compañero + aprobación
- [ ] README con nombres, Alumno 1/2, cómo poner la key y acuerdo de coordinación
- [ ] Probar en modo avión: la app no se cae y muestra mensaje / datos guardados
