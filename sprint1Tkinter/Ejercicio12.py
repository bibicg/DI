# Ejercicio 12: Aplicación de Registro de Usuarios
# Desarrolla una aplicación que permita registrar usuarios con los siguientes datos: nombre,
# edad y género. La aplicación debe contar con las siguientes funcionalidades:
# 1. Un campo de entrada (Entry) para el nombre del usuario.
# 2. Una barra deslizante (Scale) para seleccionar la edad del usuario (entre 0 y 100
# años).
# 3. Tres botones de opción (Radiobutton) para seleccionar el género del usuario
# (masculino, femenino, otro).
# 4. Un botón (Button) para añadir el usuario a una lista.
# 5. Una lista de usuarios (Listbox) que muestre el nombre, la edad y el género de los
# usuarios registrados.
# 6. Una barra de desplazamiento vertical (Scrollbar) para la lista de usuarios.
# 7. Un botón para eliminar el usuario seleccionado de la lista.
# 8. Un botón de salir que cierre la aplicación.
# 9. Un menú desplegable con las opciones “Guardar Lista” y “Cargar Lista” esto nos
# mostrara un messagebox indicando que la lista ha sido guardada o cargada.

import tkinter as tk
from tkinter import messagebox

class UsuariosApp:
    # al hacerlo todo dentro de una clase, necesito renombrar con self.
    def __init__(self, root): # constructor de la clase, recibe root como parámetro
        self.root = root # inicializamos root
        self.root.title("Ejercicio 12: Añadir y eliminar usuarios") # indicamos un title
        self.root.geometry("600x800") # y tamaño para la ventana

        # Creo la lista de usuarios que se mostrarán en el listbox:
        self.lista_usuarios = [] # cada usuario está compuesto por nombre, edad y genero. Por ahora, está vacía

        # Aunque el ejercicio no lo pide, creo 3 frames para dividir en pantalla la información y para practicar con el widget
        # Creamos un frame para la parte de arriba
        self.frame_top = tk.Frame(root, highlightbackground="black", highlightcolor="black", highlightthickness=1)
        self.frame_top.pack(padx= 5, pady = 5, fill="both", expand = True)

        # Campo para el nombre de usuario (precisa una label y una entry):
        etiqueta_nombre = tk.Label(self.frame_top, text="Nombre de usuario:")
        etiqueta_nombre.pack()

        self.entrada_nombre = tk.Entry(self.frame_top, width=30)
        self.entrada_nombre.pack()

        # Scale para seleccionar la edad del usuario (necesita también una etiqueta para la indicación al usuario)
        etiqueta_edad = tk.Label(self.frame_top, text="Edad de usuario:")
        etiqueta_edad.pack()

        self.scale = tk.Scale(self.frame_top, from_=0, to=100, orient="horizontal")
        self.scale.pack(pady=20)

        # 3 Radiobuttons para seleccionar el genero del usuario
        # Primero, la etiqueta con la info para el usuario:
        etiqueta_genero = tk.Label(self.frame_top, text="Género de usuario:")
        etiqueta_genero.pack()

        # Segundo, crear una variable para los radioButtons
        self.var_radio = tk.StringVar()
        self.var_radio.set("masculino")  # valor por defecto

        # Tercero, crear los rarioButton
        radio_masculino = tk.Radiobutton(self.frame_top, text="masculino", variable=self.var_radio, value="masculino")
        radio_femenino = tk.Radiobutton(self.frame_top, text="femenino", variable=self.var_radio, value="femenino")
        radio_otro = tk.Radiobutton(self.frame_top, text="otro", variable=self.var_radio, value="otro")

        radio_masculino.pack()
        radio_femenino.pack()
        radio_otro.pack()

        # Al final del frame superior, estará el botón para añadir el nuevo usuario
        boton_anadir_usuario = tk.Button(self.frame_top, text="Añadir usuario", command=self.clickar_anadir_usuario)
        boton_anadir_usuario.pack()

        # Creamos el segundo frame:
        self.frame_central = tk.Frame(root)
        self.frame_central.pack(padx= 5, pady = 5, fill="both", expand = True)

        # DENTRO DEL FRAME CENTRAL:
        # Etiqueta con la info para el usuario:
        etiqueta_lista = tk.Label(self.frame_central, text="Lista de usuarios:")
        etiqueta_lista.pack()

        self.listbox = tk.Listbox(self.frame_central, selectmode=tk.SINGLE)
        self.listbox.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)

        self.scrollbar = tk.Scrollbar(self.frame_central)
        self.scrollbar.pack(side=tk.RIGHT, fill=tk.Y)

        self.listbox.config(yscrollcommand=self.scrollbar.set)
        self.scrollbar.config(command=self.listbox.yview)

        # Botón para eliminar un usuario de la lista:
        boton_eliminar_usuario = tk.Button(self.frame_central, text="Eliminar usuario", command=self.eliminar_usuario)
        boton_eliminar_usuario.pack(side=tk.BOTTOM, pady=10, anchor="center")

        # Creamos el tercer frame
        self.frame_bottom = tk.Frame(root)
        self.frame_bottom.pack(padx= 5, pady = 5, fill="both", expand = True)

        # DENTRO DEL FRAME INFERIOR:
        boton_salir = tk.Button(self.frame_bottom, text="Salir", command=self.salir)
        boton_salir.pack(side=tk.BOTTOM, padx=5)


        # MENÚ CON DOS OPCIONES(SUPERIOR, EN LA BARRA)
        # Creamos el menú en el root
        self.menu_bar = tk.Menu(root)
        root.config(menu=self.menu_bar)

        # Creamos los items del menú
        archivo_menu = tk.Menu(self.menu_bar, tearoff=0)
        archivo_menu.add_command(label="Guardar Lista", command=self.guardar_lista)  # ejecuta el metodo guardar_lista
        archivo_menu.add_command(label="Cargar Lista", command=self.cargar_lista)  # ejecuta el metodo cargar_lista

        self.menu_bar.add_cascade(label="Archivo", menu=archivo_menu)

    # Métodos
    def clickar_anadir_usuario(self): # botón que añade el usuario al listbox
        nombre = self.entrada_nombre.get() # El nombre (Entry) nos da el nombre del usuario
        edad = self.scale.get() # La edad (Scale) nos da la edad del usuario
        genero = self.var_radio.get() # El genero (radioButton) nos da el genero del usuario

        usuario = f"Nombre: {nombre}, Edad: {edad}, Género: {genero}" # Crea un usuario con los valores recibidos de los widget
        self.lista_usuarios.append(usuario) # Agrega el usuario a la lista
        self.actualizar_listbox()
        self.entrada_nombre.delete(0, tk.END) # Limpia el campo de nombre
        self.scale.set(0) # Resetea la escala de edad
        self.var_radio.set("masculino") # Resetea el género a masculino

    def actualizar_listbox(self): # Actualiza el listbox con todos los usuarios que vamos añadiendo (y que quitamos)
        self.listbox.delete(0, tk.END) # Limpia el Listbox
        for usuario in self.lista_usuarios:
            self.listbox.insert(tk.END, usuario) # Inserta cada usuario en el Listbox

    def eliminar_usuario(self):
        usuario_seleccionado = self.listbox.curselection() # obtenemos el elemento (usuario) seleccionado en el listbox
        if usuario_seleccionado:
            indice = usuario_seleccionado[0] # Solo eliminamos el primer seleccionado
            del self.lista_usuarios[indice]
            self.actualizar_listbox()
        else:
            messagebox.showwarning("AVISO", "Debes seleccionar un usuario.")

    def salir(self): # salir de la aplicación
        self.root.quit()

    def guardar_lista(self):
        messagebox.showinfo("Guardar Lista", "La lista de usuarios ha sido guardada.")

    def cargar_lista(self):
        messagebox.showinfo("Cargar Lista", "La lista de usuarios ha sido cargada.")

if __name__ == "__main__":
    root = tk.Tk()
    app = UsuariosApp(root)
    root.mainloop() #Corremos la aplicacion con un bucle
