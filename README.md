# API Rest: Libreria Videojuegos

## Idea del Proyecto

Este proyecto consiste en una aplicación web que lo que nos permite a partir de una base de datos de videojuegos general, organizarlos en librerías o colecciones creadas por nosotros mismos, para poder organizar cualquier videojuego como queramos. Por ejemplo, creamos la colección de "Deseados" y ya a partir de eso metemos en esa lista videojuegos que estén disponibles en la base de datos

***

## Justificación del Proyecto

La justificación de este proyecto radica en la creciente popularidad de los videojuegos y la necesidad de los jugadores de organizar y gestionar sus colecciones de manera eficiente. Actualmente, no existe una herramienta específica que permita a los usuarios llevar un registro detallado de sus videojuegos, incluyendo información sobre el estado de juego (por jugar, completados, etc.). Esta aplicación facilitará la gestión de las colecciones de videojuegos y mejorará la experiencia de los jugadores. Tambien nace la idea del proyecto a nivel personal por la necesidad de tener un sitio donde organizar todos mis videojuegos ya sean digitales o físicos.

***

## Tablas

Este proyecto usa 3 tablas principales

### Descripción

1. #### Users

La tabla Users almacena información sobre los usuarios de la aplicación. Cada usuario tiene un identificador único, un nombre de usuario y una contraseña.



**userId**: Un identificador único para cada usuario (Clave Primaria).&#x20;

**username**: El nombre de usuario del usuario, que es obligatorio y debe ser único.&#x20;

**password**: La contraseña del usuario, que es obligatoria.

