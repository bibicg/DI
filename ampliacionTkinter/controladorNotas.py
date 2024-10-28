# Controlador (Manejo de lógica de interacción)**:
# Crearás una clase `ControladorNotas` que conectará la vista con el modelo y manejará la interacción del usuario:

# EN EL CONTROLADOR ESTARAN LOS COMMAND
# self.vista.boton_agregar.config(y ahi es donde le agregas el command)
import threading
from io import BytesIO
import requests
from tkinter import messagebox
from PIL import Image, ImageTk
import tkinter as tk
from vistaNotas import VistaNotas
from modeloNotas import ModeloNotas

class ControladorNotas: # Esta clase gestionará la lógica de la interacción entre el usuario, la vista y el modelo.

    def __init__(self, vistaNotas, modelo):
        self.vistaNotas = vistaNotas
        self.modelo = modelo

        # Cada botón de la vista estará asociado a una función del controlador mediante la opción `command`
        self.vistaNotas.boton_agregar.config(command=self.agregar_nota)
        self.vistaNotas.boton_eliminar.config(command=self.eliminar_nota)
        self.vistaNotas.boton_guardar.config(command=self.guardar_notas)
        self.vistaNotas.boton_cargar.config(command=self.cargar_notas)
        self.vistaNotas.boton_descargar.config(command=self.descargar_imagen)

        self.actualizar_listbox()



    def agregar_nota(self):
        nueva_nota = self.vistaNotas.entry_nota.get() # - Obtiene el texto del widget `Entry` de la vista utilizando `self.vista.entry_nota.get()`.
        if nueva_nota:
            self.modelo.agregar_nota(nueva_nota) # Llama al metodo `agregar_nota()` del modelo para agregar la nueva nota
            self.vistaNotas.entry_nota.delete(0, tk.END)
            self.actualizar_listbox() # Actualiza el `Listbox` llamando a `actualizar_listbox()


    def eliminar_nota(self): # Elimina la nota seleccionada en el `Listbox` y actualiza la vista.
        try:
            indice = self.vistaNotas.listbox.curselection()[0] # Obtiene el índice seleccionado del Listbox
            self.modelo.eliminar_nota(indice) # Llama al metodo eliminar_nota() del modelo para eliminar la nota en el índice seleccionado
            self.actualizar_listbox() # Actualiza el `Listbox` llamando a `actualizar_listbox()
        except IndexError:
            messagebox.showwarning("Advertencia", "Debes seleccionar una nota antes de pulsar el botón eliminar.")


    def guardar_notas(self):
        self.modelo.guardar_notas() # Llama al metodo `guardar_notas()` del modelo para guardar la lista de notas en un archivo.
        messagebox.showinfo("Éxito", "Notas guardadas con éxito.") # Muestra un mensaje de confirmación con `messagebox.showinfo()`.

    def cargar_notas(self):
        self.modelo.cargar_notas() # Llama al metodo `cargar_notas()` del modelo para cargar las notas desde un archivo
        self.actualizar_listbox() # Actualiza el `Listbox` llamando a `actualizar_listbox()`.

    def descargar_imagen(self):
        # Cambia esta URL a la imagen que desees
        url = 'https://www.wikihow.com/images_en/thumb/d/dd/Get-the-URL-for-Pictures-Step-7-Version-3.jpg/v4-460px-Get-the-URL-for-Pictures-Step-7-Version-3.jpg.webp'
        hilo = threading.Thread(target=self._descargar_imagen, args=(url,))
        hilo.start()

    def _descargar_imagen(self, url):

        respuesta = requests.get(url)

        if respuesta.status_code == 200:  # Verificar si la descarga fue exitosa
            # Abrir la imagen desde los bytes descargados
            imagen = Image.open(BytesIO(respuesta.content))
            imagen_tk = ImageTk.PhotoImage(imagen)

            # Usar after para actualizar la interfaz en el hilo principal
            self.vistaNotas.master.after(0, lambda: self.vistaNotas.mostrar_imagen(imagen_tk))
        else:
            print(f"Error al descargar la imagen: {e}")
            self.vistaNotas.master.after(0, lambda: self.vistaNotas.mostrar_error("Error al descargar la imagen."))



    def actualizar_listbox(self):
        self.vistaNotas.listbox.delete(0, tk.END) # Eliminar los elementos actuales del `Listbox` usando `delete(0, tk.END)`.
        for nota in self.modelo.obtener_notas():  # Obtener las notas actuales desde el modelo llamando a `obtener_notas()`.
            self.vistaNotas.listbox.insert(tk.END, nota) # Insertar cada nota en el ´Listbox´ usando `insert(tk.END, nota)` en un bucle para asegurarse de que todas las notas se reflejan en la interfaz gráfica.
