# Ejercicio 5: Radiobutton
# Crea un grupo de tres botones de opción (Radiobutton) para que el usuario elija su color
# favorito (rojo, verde o azul). Al seleccionar una opción, cambia el color de fondo de la
# ventana al color seleccionado.

import tkinter as tk

root = tk.Tk()
root.title("Ejercicio 5: Radiobutton")
root.geometry("300x200")

def cambiar_color(): # Metod para cambiar el color del fondo
    color = var_color.get() # leyendo el color de la variable recogida en el radioButton
    root.config(bg=color) # y configurando que el root tenga un color de fondo

# Creamos una variable donde meter el color del fondo, inicialmente será gris:
var_color = tk.StringVar(value="grey")

# Crear 3 Radiobuttons, cada uno de ellos establece un color para el fondo del root
radio_rojo = tk.Radiobutton(root, text="Rojo", variable=var_color, value="red", command=cambiar_color)
radio_verde = tk.Radiobutton(root, text="Verde", variable=var_color, value="green", command=cambiar_color)
radio_azul = tk.Radiobutton(root, text="Azul", variable=var_color, value="blue", command=cambiar_color)

radio_rojo.pack(pady=10)
radio_verde.pack(pady=5)
radio_azul.pack(pady=10)

# Cambiamos el color de fondo al iniciar
cambiar_color()

root.mainloop()
