import tkinter as tk
from controlador import GameController
from modelo import GameModel
from vista import MainMenu


if __name__ == "__main__":
    # Creamos la ventana principal con un titulo y un tamaño
    root = tk.Tk()
    root.title("- Menú principal -")
    root.geometry("600x600")

    # Inicializamos el controlador
    # La variable controller maneja la lógica de la aplicación, actuando como intermediario entre la interfaz de usuario
    # y el modelo de datos.
    controller = GameController(root)  

    # Ejecutamos el bucle principal de Tkinter
    root.mainloop()


