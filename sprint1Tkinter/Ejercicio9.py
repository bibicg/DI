# Ejercicio 9: Menu
# Crea una barra de menú en la ventana que tenga dos menús desplegables: “Archivo” con
# las opciones “Abrir” y “Salir”, y “Ayuda” con la opción “Acerca de”. La opción “Salir” debe
# cerrar la ventana, y “Acerca de” debe mostrar un mensaje informativo en una ventana
# emergente.

import tkinter as tk
from tkinter import messagebox

def acerca_de():
    messagebox.showinfo("Acerca de", "Esta es una información adicional.")

def cerrar_ventana():
    root.destroy() # cierra la ventana, saliendo así de la aplicación
    # root.quit() BUSCAR LA DIFERENCIA QUE HAY ENTRE AMBAS

root = tk.Tk()
root.title("Ejercicio 9: Menu")
root.geometry("300x200")

# Creamos la barra de nuestro menú en el root:
barra_menu = tk.Menu(root)
root.config(menu=barra_menu)

# Creamos el submenú Archivo
menu_archivo = tk.Menu(barra_menu, tearoff = 0)
barra_menu.add_cascade(label="Archivo", menu=menu_archivo)
    # y le añadimos sus submenús correspondientes:
menu_archivo.add_cascade(label="Abrir", )
menu_archivo.add_separator()
menu_archivo.add_cascade(label="Salir", command=cerrar_ventana)

# Creamos el submenú Ayuda
menu_ayuda = tk.Menu(barra_menu, tearoff = 0)
barra_menu.add_cascade(label="Ayuda", menu=menu_ayuda)
    # y le añadimos sus submenús correspondientes:
menu_ayuda.add_cascade(label="Acerca de", command=acerca_de)

root.mainloop()