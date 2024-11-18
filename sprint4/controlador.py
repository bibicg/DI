import tkinter.messagebox
from vista import MainMenu

class GameController:
    # es el controlador principal del juego y coordina el flujo de la aplicación, gestionando la comunicación entre el
    # modelo (GameModel) y la vista (MainMenu y GameView). Maneja eventos de usuario como clics en el tablero, y
    # actualiza el estado del juego y la interfaz en consecuencia.
    def __init__(self, root):
        # Inicialización de elementos:
        self.root = root
        self.menu = None
        self.modelo = None
        self.selected = []
        self.timer_started = False

        # Crea el menú principal (MainMenu) y establece sus callbacks para iniciar el juego, mostrar estadísticas y salir de la aplicación.

        self.menu = MainMenu(root,start_game_callback=self.start_game,show_stats_callback=self.show_stats,quit_callback=self.quit_game)

    def start_game(self): # FUNCIONES TODAVIA NO IMPLEMENTADAS: mensaje de aviso al respecto
        tkinter.messagebox.showinfo("Adevertencia", "Jugar: aún no disponible.")

    def show_stats(self): # FUNCIONES TODAVIA NO IMPLEMENTADAS: mensaje de aviso al respecto
        tkinter.messagebox.showinfo("Advertencia", "Estadísticas: aún no disponible.")

    def quit_game(self): # cierra la ventana y la aplicación, por tanto
        self.root.quit()
