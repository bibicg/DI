# Ejercicio 6: Listbox
# Crea una lista de elementos (Listbox) que contenga una lista de frutas (por ejemplo,
# “Manzana”, “Banana”, “Naranja”). Al seleccionar una fruta y hacer clic en un botón,
# muestra el nombre de la fruta seleccionada en una etiqueta.

import tkinter as tk

# Creamos un metodo que recorra la listbox para saber qué elememtos de la misma se han seleccionado
def mostrar_fruta_seleccionada():
    seleccion = listbox.curselection()
    elementos = [listbox.get(i) for i in seleccion]
    etiqueta.config(text=f"Seleccionaste: {','.join(elementos)}")

# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 6: Listbox")
root.geometry("300x400")

# Creamos una lista con las frutas que se van a mostrar en el listbox:
lista_frutas =["Manzana", "Banana", "Naranja"]

# Creamos el listbox, al que le pasaremos el listado de frutas que acabamos de crear:
listbox = tk.Listbox(root, selectmode=tk.MULTIPLE) # se permite seleccion múltiple
for fruta in lista_frutas: # recorremos la lista de frutas con un bucle
    listbox.insert(tk.END, fruta) # y vamos metiendo cada elemento en el listbox
listbox.pack()


# Creamos un botón que, al clickar, nos muestre en una etiqueta las frutas que hemos seleccionado
# Llama al metodo "mostrar_fruta_seleccionada"
boton = tk.Button(root, text="Muestra la fruta seleccionada", command=mostrar_fruta_seleccionada)
boton.pack(pady = 5)

# Creamos la etiqueta para poder mostrar las frutas seleccionadas del listbox
etiqueta = tk.Label(root, text="Seleccionaste: Ninguno")
etiqueta.pack(pady = 10)

# Ejecutamos el bucle principal
root.mainloop()