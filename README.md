- Casilla:
  HECHO Construsctor casilla.
  DUDA evaluarCasilla (Alba rehaciendo)
  HECHO comprarCasilla 
  HECHO sumarValor

  ManejarTransporte (revisar)

- Grupo: HECHO y revisado
  HECHO anhadirCasilla
  HECHO DuenhoGrupo

- Menu:
  iniciarPartida
  analizarCamonado
  descJugador
  descCasilla
  comprar
  salirCarcel
  listarVenta
  listarJugadores
  listarAvatares
  acabarTurno

- Tablero: HECHO
  toString HECHO {
    Colores HECHO
    Tabulacion HECHO
    Cajas HECHO
  }
  encontrar_casilla HECHO

- Avatar:
  moverAvatar

- Jugador:
  anhadirPropiedad (hecho, Alba)
  sumarFortuna (hecho, Alba)
  sumarGastos (hecho, Alba)
  DUDA encarcelar

-IMPRIMIR TABLERO


1- Desde tu rama: add commit push
2- Cambiar a rama main: checkout main
3- Juntar en main el contenido de tu rama: git merge (nombre_rama)
4- Desde main: add commit push