import tkinter as tk
from PIL import Image, ImageTk

# Vista (Interfaz gráfica)**:
#    - Utilizarás diferentes widgets de Tkinter para crear la interfaz gráfica:
#      - **Label**: Para mostrar el título de la aplicación y las coordenadas donde se haga clic.
#      - **Listbox**: Para mostrar las notas que el usuario añade.
#      - **Entry**: Para que el usuario escriba una nueva nota.
#      - **Button**: Para agregar notas, eliminar notas, guardar notas en un archivo, cargar notas desde un archivo y descargar una imagen desde GitHub.
#      - **Label**: Para mostrar la imagen descargada desde GitHub.
#    - Se debe capturar el clic del ratón en cualquier parte de la ventana, excepto en los botones, utilizando `bind`.

class VistaNotas: # Esta clase gestionará la interfaz gráfica de la aplicación, definiendo los widgets y su disposición.

    def __init__(self, master): # Constructor
        self.master = master
        self.master.title("Gestión de Notas")

        self.label_titulo = tk.Label(master, text="Aplicación de Notas") # Se utiliza para mostrar el título de la aplicación.
        self.label_titulo.pack()

        self.listbox = tk.Listbox(master) # Muestra la lista de notas agregadas. Se usará el metodo insert() para añadir elementos y delete() para eliminarlos
        self.listbox.pack()

        self.entry_nota = tk.Entry(master)  # Entrada de texto donde el usuario puede escribir una nueva nota
        self.entry_nota.pack()

        # Botones para agregar, eliminar, guardar, cargar notas, y descargar una imagen.
        # Cada botón estará asociado a una función del controlador mediante la opción `command`.
        self.boton_agregar = tk.Button(master, text="Agregar Nota")
        self.boton_agregar.pack()

        self.boton_eliminar = tk.Button(master, text="Eliminar Nota")
        self.boton_eliminar.pack()

        self.boton_guardar = tk.Button(master, text="Guardar Notas")
        self.boton_guardar.pack()

        self.boton_cargar = tk.Button(master, text="Cargar Notas")
        self.boton_cargar.pack()

        self.boton_descargar = tk.Button(master, text="Descargar Imagen")
        self.boton_descargar.pack()

        self.label_imagen = tk.Label(master) # También se usa para mostrar la imagen descargada.
        self.label_imagen.pack()

        self.label_coordenadas = tk.Label(master, text="Coordenadas del clic") # También se usa para mostrar las coordenadas del clic del ratón
        self.label_coordenadas.pack()

        self.master.bind("<Button-1>", self.actualizar_coordenadas)

    def actualizar_coordenadas(self, event):
        self.label_coordenadas.config(text=f"Coordenadas: ({event.x}, {event.y})")

    # CAMBIO EL METODO DE MOSTRAR IMAGEN, A VER SI ASI FUNCIONA:
    #def mostrar_imagen(self, ruta):
        # img = Image.open(ruta)
        # img = img.resize((200, 200), Image.ANTIALIAS)
        # self.img_tk = ImageTk.PhotoImage(img)
        # self.label_imagen.config(image=self.img_tk)

    def mostrar_imagen(self, imagen_tk):
        self.label_imagen.config(image=imagen_tk)
        self.label_imagen.image = imagen_tk  # Mantener referencia de la imagen

