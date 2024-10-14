# Ejercicio 2: Button
# Diseña una ventana con dos botones. Uno debe mostrar un mensaje en una etiqueta al
# presionarlo, y el otro debe cerrar la ventana cuando se haga clic en él.

import tkinter as tk

root = tk.Tk()
root.title("Ejercicio 2: Button")
root.geometry("300x200")

def clickar_boton_1():
    etiqueta_1 = tk.Label(root, text="Soy un mensaje derivado de presionar un botón")
    etiqueta_1.pack()

boton_1 = tk.Button (root, text ="Presióname, mostraré un mensaje en una etiqueta", command = clickar_boton_1)
boton_1.pack()

def clickar_boton_2():
    root.destroy()

boton_2 = tk.Button (root, text ="Presióname, cerraré la ventana", command = clickar_boton_2)
boton_2.pack()



root.mainloop()