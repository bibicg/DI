import tkinter as tk

# Ejercicio 1: Label
# Crea una ventana que muestre tres etiquetas (Label). La primera debe mostrar un
# mensaje de bienvenida, la segunda debe mostrar tu nombre, y la tercera debe cambiar su
# texto al hacer clic en un botón.

root = tk.Tk()
root.title("Ejercicio 1: Label")
root.geometry("300x200")

# Crear tres etiquetas con mensajes diferentes:
etiqueta_1 = tk.Label (root, text = "Bienvenida!!!")
etiqueta_1.pack()

etiqueta_2 = tk.Label (root, text = "Bibiana")
etiqueta_2.pack()

etiqueta_3 = tk.Label (root, text = "Texto inicial de la etiqueta 3")
etiqueta_3.pack()

def clickar_boton():
    etiqueta_3.config(text="Nuevo texto de la etiqueta 3")


boton = tk.Button (root, text ="Haz click, soy un botón!", command = clickar_boton)
boton.pack()



root.mainloop()