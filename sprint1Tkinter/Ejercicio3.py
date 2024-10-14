# Ejercicio 3: Entry
# Crea una interfaz que tenga un campo de entrada (Entry) donde el usuario pueda escribir
# su nombre. Al hacer clic en un botón, debe mostrarse un saludo personalizado en una
# etiqueta.

import tkinter as tk

root = tk.Tk()
root.title("Ejercicio 3: Entry")
root.geometry("300x200")

etiqueta_1 = tk.Label(root, text="Escribe tu nombre:")
etiqueta_1.pack()

entrada_1 = tk.Entry(root, width=30)
entrada_1.pack()

etiqueta_2 = tk.Label(root, text="")
etiqueta_2.pack(pady=20)

def clickar_boton_1():
    texto = entrada_1.get()
    etiqueta_2.config(text = f"Hola: {texto}")

boton_1 = tk.Button (root, text ="Presióname!!", command = clickar_boton_1)
boton_1.pack()

root.mainloop()