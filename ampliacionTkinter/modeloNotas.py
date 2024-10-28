# Modelo (Manejo de datos)**:
#    - Crearás una clase `NotasModel` que gestionará la lógica de las notas:
#      - **Mtodo `agregar_nota(nueva_nota)`**: Añade una nueva nota a la lista de notas.
#      - **Metodo `eliminar_nota(indice)`**: Elimina la nota en el índice especificado.
#      - **Metodo `obtener_notas()`**: Devuelve la lista de notas.
#      - **Metodo `guardar_notas()`**: Guarda las notas en un archivo de texto (`notas.txt`).
#      - **Metodo `cargar_notas()`**: Carga las notas desde el archivo de texto (`notas.txt`) al iniciar la aplicación.

class ModeloNotas: # Esta clase gestionará la lista de notas y proporcionará métodos para agregar, eliminar y recuperar las notas.

    def __init__(self):
        self.notas = [] # Lista que almacena las notas
        # self.cargar_notas()

    def agregar_nota(self, nueva_nota): # Añade una nueva nota a la lista de notas. Utiliza el metodo `append()` para agregar la nueva nota.
        self.notas.append(nueva_nota)

    def eliminar_nota(self, indice): # Elimina una nota en un índice específico. Usa `del self.notas[indice]` para eliminarla.
        del self.notas[indice]

    def obtener_notas(self): # Devuelve la lista completa de notas. Este metodo retorna el valor de `self.notas`.
        return self.notas

    def guardar_notas(self): # Abre el archivo `notas.txt` en modo escritura (`'w'`), y escribe cada nota en una nueva línea utilizando `write()`.
        with open('notas.txt', 'w') as archivo:
            for nota in self.notas:
                archivo.write(nota + '\n')

    def cargar_notas(self): # Abre el archivo `notas.txt` en modo lectura (`'r'`) y lee cada línea, eliminando los saltos de línea usando `strip()`.
        # Abrir un archivo en modo lectura: archivo = open('datos.txt', 'r')
        with open('notas.txt', 'r') as archivo:
            self.notas = [line.strip() for line in archivo.readlines()]
            # readlines() lee todas las líneas y las devuelve como una lista
