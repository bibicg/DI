import tkinter as tk

# Practicando: crear una ventana que esté centrada en nuestra pantalla,
# y que se adapte al tamaño  de los widgets que hay en nuestra ventana

def abrir_ventana():
    ventana_emergente = tk.Toplevel()
    ventana_emergente.title("Ventana Emergente")
    ventana_emergente.geometry("200x100")
    etiqueta = tk.Label(ventana_emergente, text="Hola desde la ventana emergente!")
    etiqueta.pack()

def saber_tamano():
    # obtener dimensiones de la pantalla
    ancho_pantalla = root.winfo_screenwidth()
    alto_pantalla = root.winfo_screenheight()
    print(f"Resolución de pantalla: {ancho_pantalla}x{alto_pantalla}")

def mostrar_dimensiones():
    ancho = root.winfo_width()
    alto = root.winfo_height()
    print(f"Dimensiones de la ventana: {ancho}x{alto}")

# Parece que hace lo mismo que el metodo mostrar_dimensiones
def obtener_dimensiones_reales():
    root.update_idletasks()
    ancho = root.winfo_width()
    alto = root.winfo_height()
    print(f"Dimensiones reales: {ancho}x{alto}")

root = tk.Tk()
root.title("Practicando con posicionamiento y tamaños")
root.geometry("400x600")
root.resizable(0,0)

# Crear tres etiquetas con mensajes diferentes:
etiqueta_1 = tk.Label(root, text="Practicando con tkinter.\n")
etiqueta_1.pack()

etiqueta_2 = tk.Label(root, text="Debemos centrar la ventana dentro de nuestra pantalla.\n")
etiqueta_2.pack()

etiqueta_3 = tk.Label(root, text="Y que sea del tamaño de los widgets que tenemos colocados en dicha ventara.\n")
etiqueta_3.pack()

# Desde este botón, averiguamos el tamaño de la pantalla de nuestro equipo:
boton_pantalla = tk.Button(root, text="¿Quieres saber el tamaño de la pantalla de tu PC?", command=saber_tamano)
boton_pantalla.pack(pady=10)

# Desde este botón, averiguamos el tamaño de la ventana:
boton_dimensiones = tk.Button(root, text="¿Quieres saber el tamaño de la ventana de tu app?", command=mostrar_dimensiones)
boton_dimensiones.pack()

# Desde este botón, abrimos una ventana nueva:
boton_abrir_ventana = tk.Button(root, text="Abrir otra ventana", command=abrir_ventana)
boton_abrir_ventana.pack(pady=10)

boton_real = tk.Button(root, text="Obtener Dimensiones Reales", command=obtener_dimensiones_reales)
boton_real.pack()

boton = tk.Button(root, text="Botón")
boton.pack()

def mostrar_tamano_requerido():
    req_ancho = boton.winfo_reqwidth()
    req_alto = boton.winfo_reqheight()
    print(f"Tamaño requerido del botón: {req_ancho}x{req_alto}")

boton_size = tk.Button(root, text="Mostrar Tamaño Requerido", command=mostrar_tamano_requerido)
boton_size.pack()

# centrar la ventana en nuestra pantalla:
#  Actualizamos todo el contenido de la ventana (la ventana pude crecer si se le agrega
#  mas widgets).Esto actualiza el ancho y alto de la ventana en caso de crecer.

#  Obtenemos el largo y  ancho de la pantalla
wtotal = root.winfo_screenwidth()
htotal = root.winfo_screenheight()
#  Guardamos el largo y alto de la ventana
wventana = 400
hventana = 600

#  Aplicamos la siguiente formula para calcular donde debería posicionarse
pwidth = round(wtotal/2-wventana/2)
pheight = round(htotal/2-hventana/2)

#  Se lo aplicamos a la geometría de la ventana
root.geometry(str(wventana)+"x"+str(hventana)+"+"+str(pwidth)+"+"+str(pheight))

# tamaño de los widgets
req_ancho = boton.winfo_reqwidth()
req_alto = boton.winfo_reqheight()
print(f"Tamaño requerido del botón: {req_ancho}x{req_alto}")

root.mainloop() #Corremos la aplicacion con un bucle