# Ejercicio 3: Entry
# Crea una interfaz que tenga un campo de entrada (Entry) donde el usuario pueda escribir
# su nombre. Al hacer clic en un botón, debe mostrarse un saludo personalizado en una
# etiqueta.

import tkinter as tk

root = tk.Tk()
root.title("Ejercicio 3: Entry")
root.geometry("300x200")

etiqueta_indicacion = tk.Label(root, text="Escribe tu nombre:")
etiqueta_indicacion.pack()

entrada_nombre = tk.Entry(root, width=30)
entrada_nombre.pack()

etiqueta_mostrar_saludo = tk.Label(root, text="")
etiqueta_mostrar_saludo.pack(pady=20)

def saludar_usuario():
    texto = entrada_nombre.get()
    etiqueta_mostrar_saludo.config(text = f"Hola: {texto}")

boton_1 = tk.Button (root, text ="Presióname!!", command = saludar_usuario)
boton_1.pack()

root.mainloop()