# Asociación Comunal - Grupo 10

Repositorio para el desarrollo del proyecto universitario de la Asociación Comunal, estructurado como un monorepo que integra un frontend en Next.js y un backend en Spring Boot.

### Estructura del Repositorio
```bash
web-asociacion-comunal/ - Aplicación frontend desarrollada con Next.js y shadcn/ui.
```

```bash
backend-asociacion-comunal/ - API REST desarrollada con Spring Boot, Spring Data JPA y Spring Security.
```

Guía de Instalación y Ejecución

1. Clonar el Repositorio

- Clona el proyecto en tu máquina local asegurándote de mantener la estructura del monorepo:

```bash
git clone https://github.com/Marcos6192005/AsociacionComunal-Grupo10-priv.git
cd AsociacionComunal-Grupo10-priv
```

2. Configurar y Ejecutar el Frontend (Next.js)
- Entra a la carpeta del frontend:

```bash
cd web-asociacion-comunal
```
- Instala las dependencias(bun, pnpm, npm):

```bash
npm install
```
- Inicia el servidor de desarrollo:

```bash
npm run dev
```
- La aplicación web estará disponible por defecto en http://localhost:3000.

3. Configurar y Ejecutar el Backend (Spring Boot)
- Abre una nueva terminal y navega a la carpeta del backend desde la raíz:

```bash
cd backend-asociacion-comunal
```
- Abre IntellJ IDEA y en el panel de Gradle haz click en sincronizar, esto es para que el proyecto tenga las dependencias necesarias para correr.
- Cuando tengas el servidor corriendo este estará disponible en http://localhost:8081
- Para ingresar, por defecto el username es "user" y la password la podras ver en el servidor cuando tengas corriendo el servidor.

Para terminal u otros IDEs, ejecuta el proyecto utilizando el Gradle Wrapper incluido:

- En Windows (CMD / PowerShell):

```Bash
.\gradlew bootRun
```

- En Linux / macOS:

```Bash
./gradlew bootRun
```
El servidor backend se iniciará localmente (configurado típicamente en el puerto 8081 para evitar conflictos locales).


## Componentes y Documentación de Referencia
- **Frontend**: Utiliza componentes de shadcn/ui.
  
- **Backend**: Construido sobre Spring Boot, incluye soporte para Spring Data JPA, Spring Security y documentación interactiva mediante SpringDoc OpenAPI. Para consultas avanzadas sobre dependencias de Gradle o integración con bases de datos, consulta la documentación oficial de Spring Boot.

<img width="860" height="307" alt="image" src="https://github.com/user-attachments/assets/81b91114-52b8-41e6-879e-445d3ca4817b" />
