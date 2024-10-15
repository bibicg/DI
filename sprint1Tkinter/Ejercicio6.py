# Ejercicio 6: Listbox
# Crea una lista de elementos (Listbox) que contenga una lista de frutas (por ejemplo,
# “Manzana”, “Banana”, “Naranja”). Al seleccionar una fruta y hacer clic en un botón,
# muestra el nombre de la fruta seleccionada en una etiqueta.

import tkinter as tk

def mostrar_fruta_seleccionada():
    seleccion = listbox.curselection()
    elementos = [listbox.get(i) for i in seleccion]
    etiqueta.config(text=f"Seleccionaste: {','.join(elementos)}")

root = tk.Tk()
root.title("Ejercicio 6: Listbox")
root.geometry("300x400")

# Crear una lista de frutas:
lista_frutas =["Manzana", "Banana", "Naranja"]

# Crear una listbox:
listbox = tk.Listbox(root, selectmode=tk.MULTIPLE)
for fruta in lista_frutas:
    listbox.insert(tk.END, fruta)
listbox.pack()


# Botón para mostrar selecciones
boton = tk.Button(root, text="Muestra la fruta seleccionada", command=mostrar_fruta_seleccionada)
boton.pack(pady = 5)

# Etiqueta para mostrar las selecciones
etiqueta = tk.Label(root, text="Seleccionaste: Ninguno")
etiqueta.pack(pady = 10)


root.mainloop()