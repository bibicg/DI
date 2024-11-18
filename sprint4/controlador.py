import tkinter.simpledialog as simpledialog
from vista import MainMenu
import tkinter.messagebox


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
        self.nombre_jugador = ""  # Almacenamos el nombre del jugador aquí

        # Llamamos al menú principal y pasamos los callbacks correspondientes
        # Crea el menú principal (MainMenu) y establece sus callbacks para iniciar el juego, mostrar estadísticas y salir de la aplicación.

        self.menu = MainMenu(root, start_game_callback=self.start_game, show_stats_callback=self.show_stats,
                             quit_callback=self.quit_game)


    def start_game(self):
        # Primero pedimos la dificultad
        self.show_difficulty_selection()

    def show_difficulty_selection(self):
        # Pedir dificultad al jugador
        difficulty = simpledialog.askstring("Selecciona la dificultad",
                                            "Escribe la dificultad (facil, intermedio, dificil):",
                                            parent=self.root)
        if difficulty not in ['facil', 'intermedio', 'dificil']:
            tkinter.messagebox.showerror("Error", "Esa dificultad no está en la lista.")
            return

        # Si la dificultad es válida, pedimos el nombre del jugador
        self.player_name = simpledialog.askstring("Nombre del jugador", "Introduce tu nombre:", parent=self.root)
        if not self.player_name:
            tkinter.messagebox.showwarning("Advertencia", "Debes escribir tu nombre para poder jugar.")
            return

        # Aquí podrías continuar con la lógica de iniciar la partida
        tkinter.messagebox.showinfo("Listo para jugar",
                                    f"¡Hola, {self.player_name}! Dificultad seleccionada: {difficulty}")
        # Ahora puedes seguir con el proceso de crear la instancia de GameModel y mostrar la vista del juego.

    def show_stats(self):  # FUNCIONES TODAVIA NO IMPLEMENTADAS: mensaje de aviso al respecto
        tkinter.messagebox.showinfo("Advertencia", "Estadísticas: aún no disponible.")

    def quit_game(self):  # cierra la ventana y la aplicación, por tanto
        self.root.quit()
