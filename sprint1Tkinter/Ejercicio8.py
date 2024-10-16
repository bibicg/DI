# Ejercicio 8: Frame
# Diseña una interfaz que esté dividida en dos Frame. El Frame superior debe contener dos
# etiquetas y un campo de entrada, mientras que el Frame inferior debe contener dos
# botones: uno para mostrar el contenido del Entry en una etiqueta, y otro para borrar el
# contenido del Entry.

import tkinter as tk

# Creamos el metodo que muestre el contenido del entry superior en la label inferior
# al clickar el botón correspondiente (button_1_bottom)
def mostrar_contenido_entry():
    contenido_label = entry_top.get() # introducimos el contenido del entry en una variable
    label_3_bottom.config(text=f"{contenido_label}") # le pasamos la variable al config de la etiqueta para que se muestre

# Creamos el metodo que borre el contenido del entry superior al clickar el botón correspondiente (button_2_bottom)
def borrar_contenido_entry():
    entry_top.delete(0, tk.END) # borro el contenido del entry en el propio entry
    label_3_bottom.config(text="") # borro el contenido de la etiqueta (el que habíamos copiado)

# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 8: Frame")
root.geometry("600x600")

# Creamos un frame
frame_top = tk.Frame (root, bg = "lightblue")
frame_top.pack(padx= 5, pady = 5, fill="both", expand = True)

# Creamos el segundo frame
frame_bottom = tk.Frame (root, bg = "lightgreen")
frame_bottom.pack(padx= 5, pady = 5, fill="both", expand = True)

# Añadimos los widgets indicados en el ejercicio al frame_top:
label_1_top = tk.Label(frame_top, text="Etiqueta 1 dentro del frame superior")
label_1_top.pack(padx= 5, pady = 5)

label_2_top = tk.Label(frame_top, text="Etiqueta 2 dentro del frame superior")
label_2_top.pack(padx= 5, pady = 5)

entry_top = tk.Entry(frame_top)
entry_top.pack(padx= 5, pady = 5)

# Añadimos los widgets indicados en el ejercicio al frame_bottom:
button_1_bottom = tk.Button(frame_bottom, text="Mostrar el contenido del entry en la label inferior", command=mostrar_contenido_entry)
button_1_bottom.pack(padx= 5, pady = 5)

button_2_bottom = tk.Button(frame_bottom, text="Borrar el contenido del entry", command=borrar_contenido_entry)
button_2_bottom.pack(padx= 5, pady = 5)

label_3_bottom = tk.Label(frame_bottom)
label_3_bottom.pack(padx= 5, pady = 5)

# Ejecutamos el bucle principal
root.mainloop()