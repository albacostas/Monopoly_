## Guía rápida de git

### Subir cambios de tu rama

```
Desde Github, ir a Pull requests,
añadir una pull request con tu rama.
```

### Cargar cambios de la rama main en tu rama

```
$ git merge main
```


### Trabajar en el proyecto

```
Añadir archivos al área de espera:
$ git add [file]
$ git add .
```

```
Crear commit del area de espera:
$ git commit -m "[nombre del commit]"
```

### Github

```
Descargar cambios de GitHub en local:
$ git pull
```

```
Consultar el estado de git:
$ git status
```

```
Cargar cambios de local a GitHub:
$ git push
```

### Trabajar con ramas

```
Listar las ramas:
$ git branch
```

```
Cambiar entre ramas:
$ git checkout [nombre de la rama]
```

```
Combinar una rama con la rama actual:
$ git merge [nombre de la rama a combinar]
utilizar desde la rama main para añadir funcionalidades completadas
```