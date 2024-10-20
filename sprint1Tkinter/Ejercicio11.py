# Ejercicio 11: Scale
# Crea una barra deslizante (Scale) que permita al usuario seleccionar un número entre 0 y
# 100. El valor seleccionado debe mostrarse en tiempo real en una etiqueta.

import tkinter as tk

def seleccionar_numero(numero): # método que recibe el número del scale
    etiqueta.config(text=f"Numero: {numero}") # Indicamos a la etiqueta lo que mostrará

# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 11: Scale")
root.geometry("200x200")

# Creamos el widget Scale
scale = tk.Scale(root, from_=0, to=100, orient="horizontal", command=seleccionar_numero)
scale.pack(pady=20)

# Creamos una etiqueta, donde se mostrará en tiempo real el número seleccionado
etiqueta = tk.Label(root, text="Numero: 0")
etiqueta.pack(pady=5)

# Ejecutamos el bucle principal
root.mainloop()