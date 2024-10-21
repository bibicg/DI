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

# METODOS:

def clickar_anadir_usuario(): # botón que añade el usuario al listbox
    nombre = entrada_nombre.get() # El nombre (Entry) nos da el nombre del usuario
    edad = scale.get() # La edad (Scale) nos da la edad del usuario
    genero = var_radio.get() # El genero (radioButton) nos da el genero del usuario

    usuario = f"Nombre: {nombre}, Edad: {edad}, Género: {genero}" # Crea un usuario con los valores recibidos de los widget
    lista_usuarios.append(usuario)  # Agrega el usuario a la lista
    actualizar_listbox()
    entrada_nombre.delete(0, tk.END)  # Limpia el campo de nombre
    scale.set(0)  # Resetea la escala de edad
    var_radio.set("masculino")  # Resetea el género a masculino

def actualizar_listbox():   # Actualiza el listbox con todos los usuarios que vamos añadiendo (y que quitamos)
    listbox.delete(0, tk.END)  # Limpia el Listbox
    for usuario in lista_usuarios:
        listbox.insert(tk.END, usuario)  # Inserta cada usuario en el Listbox

def eliminar_usuario():
    usuario_seleccionado = listbox.curselection() # obtenemos el elemento (usuario) seleccionado en el listbox
    if usuario_seleccionado:
        indice = usuario_seleccionado[0]  # Solo eliminamos el primer seleccionado
        del lista_usuarios[indice]
        actualizar_listbox()
    else:
        messagebox.showwarning("AVISO", "Debes seleccionar un usuario.")

def salir(): # salir de la aplicación
    root.quit()

def guardar_lista():
    messagebox.showinfo("Guardar Lista", "La lista de usuarios ha sido guardada.")

def cargar_lista():
    messagebox.showinfo("Cargar Lista", "La lista de usuarios ha sido cargada.")


# VARIABLES:
# Creo la lista de usuarios que se mostrarán en el listbox:
lista_usuarios =[] # cada usuario está compuesto por nombre, edad y genero. Por ahora, está vacía

# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 12: Scale")
root.geometry("600x800")

# Aunque el ejercicio no lo pide, creo 3 frames para dividir en pantalla la información y para practicar con el widget
# Creamos un frame
frame_top = tk.Frame (root, highlightbackground="black", highlightcolor="black", highlightthickness=1)
frame_top.pack(padx= 5, pady = 5, fill="both", expand = True) #

# Creamos el segundo frame
frame_central = tk.Frame (root)
frame_central.pack(padx= 5, pady = 5, fill="both", expand = True)

# Creamos el tercer frame
frame_bottom = tk.Frame (root)
frame_bottom.pack(padx= 5, pady = 5, fill="both", expand = True)

# DENTRO DEL FRAME SUPERIOR:
# Campo para el nombre de usuario (precisa una label y una entry):
etiqueta_nombre = tk.Label(frame_top, text="Nombre de usuario:")
etiqueta_nombre.pack()

entrada_nombre = tk.Entry(frame_top, width=30)
entrada_nombre.pack()

# Scale para seleccionar la edad del usuario (necesita también una etiqueta para la indicación al usuario)
etiqueta_edad = tk.Label(frame_top, text="Edad de usuario:")
etiqueta_edad.pack()

scale = tk.Scale(frame_top, from_=0, to=100, orient="horizontal")
scale.pack(pady=20)

# 3 Radiobuttons para seleccionar el genero del usuario
# Primero, la etiqueta con la info para el usuario:
etiqueta_genero = tk.Label(frame_top, text="Género de usuario:")
etiqueta_genero.pack()

# Segundo, crear una variable para los radioButtons
var_radio = tk.StringVar()
var_radio.set("masculino") # valor por defecto

# Tercero, crear los rarioButton
radio_masculino = tk.Radiobutton(frame_top, text="masculino", variable=var_radio, value="masculino")
radio_femenino = tk.Radiobutton(frame_top, text="femenino", variable=var_radio, value="femenino")
radio_otro = tk.Radiobutton(frame_top, text="otro", variable=var_radio, value="otro")

radio_masculino.pack()
radio_femenino.pack()
radio_otro.pack()

# Al final del frame superior, estará el botón para añadir el nuevo usuario
boton_anadir_usuario = tk.Button (frame_top, text ="Añadir usuario", command = clickar_anadir_usuario)
boton_anadir_usuario.pack()

# DENTRO DEL FRAME CENTRAL:
# Etiqueta con la info para el usuario:
etiqueta_lista = tk.Label(frame_central, text="Lista de usuarios:")
etiqueta_lista.pack()


listbox = tk.Listbox(frame_central, selectmode=tk.SINGLE)
listbox.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)

scrollbar = tk.Scrollbar(frame_central)
scrollbar.pack(side=tk.RIGHT, fill=tk.Y)

listbox.config(yscrollcommand=scrollbar.set)
scrollbar.config(command=listbox.yview)

# Botón para eliminar un usuario de la lista:
boton_eliminar_usuario = tk.Button(frame_central, text="Eliminar usuario", command=eliminar_usuario)
boton_eliminar_usuario.pack(side=tk.BOTTOM, pady=10, anchor="center")

# DENTRO DEL FRAME INFERIOR:
boton_salir = tk.Button(frame_bottom, text="Salir", command=salir)
boton_salir.pack(side=tk.BOTTOM, padx=5)

# MENÚ CON DOS OPCIONES(SUPERIOR, EN LA BARRA)
# Creamos el menú en el root
menu_bar = tk.Menu(root)
root.config(menu=menu_bar)

# Creamos los items del menú
archivo_menu = tk.Menu(menu_bar, tearoff=0)
archivo_menu.add_command(label="Guardar Lista", command=guardar_lista) # ejecuta el metodo guardar_lista
archivo_menu.add_command(label="Cargar Lista", command=cargar_lista) # ejecuta el metodo cargar_lista

menu_bar.add_cascade(label="Archivo", menu=archivo_menu)


# Ejecutamos el bucle principal
root.mainloop()