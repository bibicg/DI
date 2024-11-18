import tkinter as tk
from tkinter import messagebox


class MainMenu:
    # define el menú inicial del juego, donde el jugador puede seleccionar opciones como jugar, ver estadísticas y salir.
    # Este menú está conectado al controlador (GameController) para que gestione estas opciones.
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        # Usamos la ventana principal root
        self.root = root
        self.root.title("Menú Principal")
        

   
        # y establece su título.
        self.label_titulo = tk.Label(self.root, text="JUEGO DE MEMORIA", font=("Arial Black", 24), fg="black")
        self.label_titulo.pack(pady=20)

        # Crea tres botones: Jugar, Estadísticas y Salir, cada uno enlazado a un callback específico para:
        # iniciar el juego, mostrar las estadísticas y cerrar la aplicación.

        self.btn_jugar = tk.Button(self.root, text="Jugar", font=("Calibri", 18), fg="white", bg="black", command=start_game_callback)
        self.btn_jugar.pack(pady=20)

        self.btn_stats = tk.Button(self.root, text="Ver estadísticas", font=("Calibri", 18), fg="white", bg="black", command=show_stats_callback)
        self.btn_stats.pack(pady=20)

        self.btn_exit = tk.Button(self.root, text="Salir", font=("Calibri", 18), fg="white", bg="black", command=quit_callback)
        self.btn_exit.pack(pady=20)
        

    def ask_player_name(self):
        # Abre un diálogo para solicitar el nombre del jugador. Esta función se utiliza al iniciar una partida para
        # personalizar la experiencia del usuario.
        pass

    def show_stats(self, stats):
        # Abre una ventana Toplevel para mostrar las estadísticas de los jugadores. Las puntuaciones se organizan por
        # nivel de dificultad, y cada entrada muestra el nombre y el número de movimientos realizados.
        # Cada nivel de dificultad tiene su propia sección con una lista de las mejores puntuaciones.
        pass
