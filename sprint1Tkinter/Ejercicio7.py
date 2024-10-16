# Ejercicio 7: Canvas
# Crea un Canvas y dibuja en él un círculo y un rectángulo. El tamaño y las posiciones
# deben ser definidas por el usuario a través de dos campos de entrada (Entry) para las
# coordenadas.

import tkinter as tk

# Creamos el metodo que dibuje en el canvas (lienzo) el circulo
def dibujar_circulo():

    # Dibujamos el círculo con las coordenadas que ha introducido el usuario
    # invocando los metodos get de las entry. El tamaño es por defecto
    canvas.create_oval(100, 60, int(entry_circulo_x.get()), int(entry_circulo_y.get()), fill="yellow")

# Creamos el metodo que dibuje en el canvas (lienzo) el rectangulo
def dibujar_rectangulo():

    # Dibujamos el rectángulo con las coordenadas que ha introducido el usuario
    # invocando los metodos get de las entry. El tamaño es por defecto
    canvas.create_rectangle(200, 100, int(entry_rectangulo_x.get()), int(entry_rectangulo_y.get()), outline="pink")

# Creamos la ventana principal, igual que en todos los archivos, para que esta se muestre
root = tk.Tk()
root.title("Ejercicio 7: Canvas")
root.geometry("400x700")

# Creamos el canvas, dándole tamaño y color de fondo
canvas = tk.Canvas(root, width=300, height=200, bg="white")
canvas.pack()

# Entradas hechas por el usuario para las coordenadas del círculo:
label_circulo_x= tk.Label(root, text="Círculo: coordenada x:") # Etiqueta para indicar lo que tiene que introducir
label_circulo_x.pack(pady = 5)
entry_circulo_x = tk.Entry(root) # Entry para recoger lo que escribe el usuario
entry_circulo_x.pack(pady = 5)

label_circulo_y = tk.Label(root, text="Círculo: coordenada y:") # Etiqueta para indicar lo que tiene que introducir
label_circulo_y.pack(pady = 5)
entry_circulo_y = tk.Entry(root) # Entry para recoger lo que escribe el usuario
entry_circulo_y.pack(pady = 5)

# Entradas hechas por el usuario para las coordenadas del rectángulo:
label_rectangulo_x= tk.Label(root, text="Rectángulo: coordenada x:") # Etiqueta para indicar lo que tiene que introducir
label_rectangulo_x.pack(pady = 5)
entry_rectangulo_x = tk.Entry(root) # Entry para recoger lo que escribe el usuario
entry_rectangulo_x.pack(pady = 5)

label_rectangulo_y = tk.Label(root, text="Rectángulo: coordenada y:") # Etiqueta para indicar lo que tiene que introducir
label_rectangulo_y.pack(pady = 5)
entry_rectangulo_y = tk.Entry(root) # Entry para recoger lo que escribe el usuario
entry_rectangulo_y.pack(pady = 5)

# Botón para dibujar el circulo, llamando al metodo encargado de ello:
button_circulo= tk.Button(root, text="Vamos a dibujar un círculo!", command=dibujar_circulo)
button_circulo.pack(padx = 10, pady = 10)

# Botón para dibujar el rectangulo, llamando al metodo encargado de ello:
button_rectangulo= tk.Button(root, text="Vamos a dibujar un rectángulo!", command=dibujar_rectangulo)
button_rectangulo.pack(padx = 10, pady = 10)

# Ejecutamos el bucle principal
root.mainloop()