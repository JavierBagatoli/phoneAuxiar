# Consultas

## Consultas genericas, todos los controladores cuentan con

Get /
Post /
Put /
Delete /

ninguna de estas consultas cuenta con una logica especializada, y por ello el borrado es de tipo fisico

## Consultas a persona

### Login de persona

Post .url/api/v1/personas/login

{
"email": "javiers@gmail.com",
"password": "123"
}

### Cambiar credenciales de persona

Post .url/api/v1/personas/actualizarCredenciales

{
"email" : "javier@gmail.com",
"codigo" : "123456",
"contrasenia": "123456",
"contraseniaRepetida": "123456",
"nuevoEmail": "nuevoEmail"
}

### Registrar de persona

Post .url/api/v1/personas/registrar

{
"nombre" : "JavierRegistro",
"apellido" : "Bagatoli",
"dni" : 123,
"email" : "javier@gmail.com",
"contrasenia" : "123",
"contraseniaRepetida" : "123"
}

### Crear persona -> Esta es la creacion estandard del controlador (Realmente no se deberia usar)

Post .url/api/v1/personas

{
"apellido":"test",
"nombre":"test",
"dni" : 4524,
"email": "javier@gmail.com",
"rol": "",
"fechaNacimiento": null,
"codigoSeguridad": 0,
"persona_carrito": "",
"contrasenia" : "123"
}

### Agregar elemento al carrito

Post .url/api/v1/personas/nuevoEmail/agregarAlCarrito

{
"id" : "1"
}

### Pedir que se genere un token

Put .url/api/v1/personas/1/generarToken

### Comprar Carrito

POST .url/api/v1/personas/realizarCompra

{
"email" : "nuevoEmail"
}

### Limpiar Carrito

Post .url/api/v1/personas/limpiarCarrito

{
"email" : "nuevoEmail"
}
