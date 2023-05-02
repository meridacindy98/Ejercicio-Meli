# Servicio mutant

Recibe como parametro una lista de satelites donde se especifica el nombre y la distancia con la nave emisora del mensaje de cada satelite. Ademas de un arreglo de Strings que le llega a cada satelite con el mensaje.

**URL** : `https://ejercicio-meli.herokuapp.com/topsecret`

**Método** : `POST`

**Ejemplo body:**
```json
{
  "satellites":[
    {
      "name": "kenobi",
      "distance": 100.0,
      "message": ["este", "", "", "mensaje", ""]
    },
    {
      "name": "skywalker",
      "distance": 115.5,
      "message": ["", "es", "", "", "secreto"]
    },
    {
      "name": "sato",
      "distance": 142.7,
      "message": ["este", "", "un", "", ""]
    }
  ]
}
```

**Posibles respuestas** :

- 200 OK
```json
{
  "position": { "x": -100.0, "y": 75.5 },
  "message": "este es un mensaje secreto"
}
```
- 404 NOT_FOUND
```json
{
  "error": "",
  "message": "Error al intentar determinar el mensaje"
}
```

```json
{
  "error": "",
  "message": "Error al intentar determinar la posicion de la nave"
}
```

