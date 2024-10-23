# Vista (Interfaz gráfica)**:
#    - Utilizarás diferentes widgets de Tkinter para crear la interfaz gráfica:
#      - **Label**: Para mostrar el título de la aplicación y las coordenadas donde se haga clic.
#      - **Listbox**: Para mostrar las notas que el usuario añade.
#      - **Entry**: Para que el usuario escriba una nueva nota.
#      - **Button**: Para agregar notas, eliminar notas, guardar notas en un archivo, cargar notas desde un archivo y descargar una imagen desde GitHub.
#      - **Label**: Para mostrar la imagen descargada desde GitHub.
#    - Se debe capturar el clic del ratón en cualquier parte de la ventana, excepto en los botones, utilizando `bind`.

import tkinter as tk
from tkinter import messagebox

class VistaNotas:
    def __init__(self, controlador):
        self.controlador = controlador
        self.root = tk.Tk()
        self.root.title("Practicando con el patrón de diseño MVC: app de notas")
        self.root.geometry("600x400")
        self.widgets()
        self.insert()
        self.delete()
        self.clic()

        # Metodo que lee las coordenadas donde clica el raton:
        # NO SE EN QUE CLASE DEBE IR

    def clic(event):
        print("Clic en:", event.x, event.y)

    def widgets(self):
        self.etiqueta_notas = tk.label(self.root, text="Añade aquí tus notas")
        self.etiqueta_notas.pack(pady=10)

        self.etiqueta_coordenadas = tk.label(self.root) # Muestra las coordenadas del raton
        self.etiqueta_coordenadas.pack(pady=10)



        self.entrada_nota = tk.Entry()
        self.entrada_nota.pack(pady=10)

        self.listbox = tk.Listbox()  # aquí se mostrarán las notas que ha ido añadiendo el usuario
                                    # Se usará el metodo `insert()` para añadir elementos y `delete()` para eliminarlos.


        # BOTONES: Cada botón estará asociado a una función del controlador mediante la opción `command`.
        self.boton_agregar_nota = tk.Button(self.root, text="Agrega tu nota al listado")
        self.boton_agregar_nota.pack(pady=5)

        self.boton_eliminar_nota = tk.Button(self.root, text="Elimina tu nota del listado")
        self.boton_eliminar_nota.pack(pady=5)

        self.boton_guardar_nota = tk.Button(self.root, text="Guardar notas")
        self.boton_guardar_nota.pack(pady=5)

        self.boton_cargar_nota = tk.Button(self.root, text="Cargar notas")
        self.boton_cargar_nota.pack(pady=5)

        self.boton_descargar_imagen = tk.Button(self.root, text="Descargar imagen")
        self.boton_descargar_imagen.pack(pady=5)

    # No tengo claro que estos dos metodos tengan que estar aqui:
    def insert(self):
        pass

    def delete(self):
        pass


    def limpiar_ventana(self):
        for widget in self.root.winfo_children():
            widget.destroy()

    def iniciar(self):
        self.root.mainloop()
