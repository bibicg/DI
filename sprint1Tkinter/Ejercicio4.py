# Ejercicio 4: Checkbutton
# Crea una ventana con tres casillas de verificación (Checkbutton) que representen tres aficiones
# (por ejemplo, “Leer”, “Deporte”, “Música”). Cuando el usuario seleccione o deseleccione
# una casilla, el estado actual de las aficiones seleccionadas debe mostrarse en una etiqueta.


import tkinter as tk

root = tk.Tk()
root.title("Ejercicio 4: Checkbutton")
root.geometry("300x200")


def actualizar_aficiones(): #metodo que recibe las validaciones (checkbutton)
    aficiones_seleccionadas = [] # lista de aficciones seleccionadas
    if var_check_1.get(): # si clicaste la variable correspondiente al check 1, te gusta leer
        aficiones_seleccionadas.append("Leer")
    if var_check_2.get(): # si clicaste la variable correspondiente al check 2, te gusta el deporte
        aficiones_seleccionadas.append("Deporte")
    if var_check_3.get(): # si clicaste la variable correspondiente al check 3, te gusta la musica
        aficiones_seleccionadas.append("Música")

    # Actualizar la etiqueta con las aficiones seleccionadas, con el join concatenamos repuestas
    etiqueta_aficiones.config(text="Aficiones seleccionadas: " + ", ".join(aficiones_seleccionadas))

# Creamos las variables para cada uno de los checkbutton:
var_check_1 = tk.IntVar()
var_check_2 = tk.IntVar()
var_check_3 = tk.IntVar()

# Creamos los checkbutton
check_1 = tk.Checkbutton(root, text="Leer", variable=var_check_1, command=actualizar_aficiones)
check_2 = tk.Checkbutton(root, text="Deporte", variable=var_check_2, command=actualizar_aficiones)
check_3 = tk.Checkbutton(root, text="Música", variable=var_check_3, command=actualizar_aficiones)
check_1.pack(pady=10)
check_2.pack(pady=10)
check_3.pack(pady=10)

# Creamos la etiqueta donde mostrar las aficciones seleccionadas
etiqueta_aficiones = tk.Label(root, text="Aficiones seleccionadas: ")
etiqueta_aficiones.pack()

root.mainloop()