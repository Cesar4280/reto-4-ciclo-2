DROP TABLE IF EXISTS paciente;

CREATE TABLE paciente(
    cedula TEXT(10) PRIMARY KEY NOT NULL,
    nombre TEXT(90) NOT NULL,
    edad INTEGER UNSIGNED NOT NULL,
    ciudad TEXT(30) NOT NULL,
    eps TEXT(50) NOT NULL,
    enfermedad TEXT(40) NOT NULL
);