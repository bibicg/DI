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

# tamaño de los widgets
req_ancho_1 = etiqueta_1.winfo_reqwidth()
req_alto_1 = etiqueta_1.winfo_reqheight()
req_ancho_2 = etiqueta_2.winfo_reqwidth()
req_alto_2 = etiqueta_2.winfo_reqheight()
req_ancho_3 = etiqueta_3.winfo_reqwidth()
req_alto_3 = etiqueta_3.winfo_reqheight()

# centrar la ventana en nuestra pantalla:
#  Actualizamos todo el contenido de la ventana (la ventana pude crecer si se le agrega
#  mas widgets).Esto actualiza el ancho y alto de la ventana en caso de crecer.

#  Obtenemos el largo y  ancho de la pantalla
wtotal = root.winfo_screenwidth()
htotal = root.winfo_screenheight()
#  Guardamos el largo y alto de la ventana
wventana = req_ancho_1 + req_ancho_2 + req_ancho_3
hventana = req_alto_1 + req_alto_2 + + req_alto_3

#  Aplicamos la siguiente formula para calcular donde debería posicionarse
pwidth = round(wtotal/2-wventana/2)
pheight = round(htotal/2-hventana/2)

#  Se lo aplicamos a la geometría de la ventana
root.geometry(str(wventana)+"x"+str(hventana)+"+"+str(pwidth)+"+"+str(pheight))



# ahora hay que sumar todo eso para obtener el tamaño total de los widgets

root.mainloop() #Corremos la aplica