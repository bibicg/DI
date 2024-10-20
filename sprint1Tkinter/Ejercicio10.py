# Ejercicio 10: Scrollbar
# Crea un Text que contenga un texto largo (varios párrafos) y añade una barra de
# desplazamiento vertical (Scrollbar) para que el usuario pueda desplazarse a través del
# contenido.

import tkinter as tk

def insertar_texto():
    cuadro_texto.insert(tk.INSERT,
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n"
                    "Cras elementum nibh vel accumsan aliquet. Donec non libero quis ex sagittis venenatis. \n"
                    "Proin eros leo, porta at ultricies et, ultrices id justo. \n\n"
                    "Proin eleifend, velit in ultrices posuere, lectus tortor pulvinar libero, tincidunt lobortis ipsum odio sed nulla. \n"
                    "Ut quis leo risus. Phasellus eu mi nec elit pretium porta sit amet ac diam. Donec elementum id dui sit amet mollis. \n\n"
                    "Pellentesque bibendum sagittis elementum. Phasellus vulputate nunc sit amet erat ultricies, in auctor nulla ornare. \n"
                    "Duis vitae luctus magna. Curabitur facilisis mi orci, ac molestie neque congue nec. \n"
                    "Nam suscipit ipsum at ante accumsan, ac suscipit lacus condimentum. Fusce rhoncus commodo gravida. Duis a accumsan diam. \n"
                    "Nam placerat ante quis nunc interdum, vel blandit magna rutrum.\n\n"
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. \n"
                    "Cras elementum nibh vel accumsan aliquet. Donec non libero quis ex sagittis venenatis. \n"
                    "Proin eros leo, porta at ultricies et, ultrices id justo. \n\n"
                    "Proin eleifend, velit in ultrices posuere, lectus tortor pulvinar libero, tincidunt lobortis ipsum odio sed nulla. \n"
                    "Ut quis leo risus. Phasellus eu mi nec elit pretium porta sit amet ac diam. Donec elementum id dui sit amet mollis. \n\n"
                    "Pellentesque bibendum sagittis elementum. Phasellus vulputate nunc sit amet erat ultricies, in auctor nulla ornare. \n"
                    "Duis vitae luctus magna. Curabitur facilisis mi orci, ac molestie neque congue nec. \n"
                    "Nam suscipit ipsum at ante accumsan, ac suscipit lacus condimentum. Fusce rhoncus commodo gravida. Duis a accumsan diam. \n"
                    "Nam placerat ante quis nunc interdum, vel blandit magna rutrum."
    )


# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 10: Scrollbar")
root.geometry("200x100")

# Crear un frame para contener el text y el scrollbar
frame = tk.Frame(root)
frame.pack(fill="both", expand=True)

# Creamos un text largo para permitir que se muestre el scrollbar
cuadro_texto = tk.Text(frame, wrap="none") # para evitar salto de línea automático
cuadro_texto.grid(row=0, column=0, sticky="nsew") # se usa el grid para controlar las posiciones de cada elemento


# Creamos la barra de scroll vertical
scroll_vertical = tk.Scrollbar(frame, orient="vertical", command = cuadro_texto.yview)
scroll_vertical.grid(row=0, column=1, sticky="ns") # alineado a la derecha del cuadro_texto
cuadro_texto.config(yscrollcommand= scroll_vertical.set)

# Ajustamos el tamaño del frame y del cuadro de texto al tamaño de la ventana
frame.grid_rowconfigure(0, weight=1)
frame.grid_columnconfigure(0, weight=1)

insertar_texto() # llamamos al metodo que inserta el texto en el cuadro de texto


# Ejecutamos el bucle principal
root.mainloop()