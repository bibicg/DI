import tkinter as tk
from tkinter import messagebox, simpledialog, Toplevel, Label
from vista import MainMenu, GameView
from modelo import GameModel
import time

class GameController:
    # es el controlador principal del juego y coordina el flujo de la aplicación, gestionando la comunicación entre el
    # modelo (GameModel) y la vista (MainMenu y GameView). Maneja eventos de usuario como clics en el tablero, y
    # actualiza el estado del juego y la interfaz en consecuencia.
    def __init__(self, root):
        # Inicialización de elementos:
        self.root = root
        self.modelo = None  # instancia del modelo del juego, creada al iniciar una partida.
        self.vista = None  # referencia a la vista actual del juego (GameView).

        # Crea el menú principal (MainMenu) y establece sus callbacks para iniciar el juego, mostrar estadísticas y salir de la aplicación.
        self.main_menu = MainMenu(root, self.start_game, self.show_stats, self.quit_game)
        self.selected_cards = []  # lista que almacena las posiciones de las cartas seleccionadas por el jugador.
        self.evaluating = False  # Indicador para evitar clics mientras se evalúan cartas.

    def start_game(self, nombre_jugador, difficulty):
        # Muestra una ventana de carga y crea una instancia de GameModel con la dificultad y el nombre del jugador
        # proporcionados. Llama a check_images_loaded para verificar que las imágenes del juego se han descargado antes de mostrar el tablero.

        if not nombre_jugador.strip():
            messagebox.showwarning("Advertencia", "Debes escribir tu nombre para poder jugar.")
            return

        board_size = self.get_board_size(difficulty)
        self.modelo = GameModel(board_size, nombre_jugador)  # Crea una instancia del modelo.
        self.modelo.difficulty = board_size  # Guarda la dificultad como atributo.

        # Crea la vista del juego.
        self.vista = GameView(self.on_card_click, self.update_move_count, self.update_time)
        self.vista.create_board(self.modelo)
        self.modelo.start_timer()  # Inicia el temporizador en el modelo.
        self.update_time()  # Inicia la actualización del temporizador.

    # METÍ METODOS EXTRA PORQUE NO ME FUNCIONABA EL VOLTEO DE LAS CARTAS
    def get_board_size(self, difficulty):
        # devuelve el tamaño del tablero dependiendo de la dificultad.
        return {"facil": 4, "intermedio": 6, "dificil": 8}.get(difficulty, 4)

    def on_card_click(self, r, c):
        # Maneja el evento de clic en una carta del tablero. Si el temporizador no ha comenzado, lo inicia y actualiza
        # el temporizador en la interfaz.
        # Almacena la posición de la carta seleccionada y, si hay dos cartas en self.selected, llama a handle_card_selection
        # para verificar si coinciden.
        if self.evaluating or (r, c) in self.selected_cards:
            return  # Ignorar clics mientras se evalúa un par o en cartas ya seleccionadas.

        card_value = self.modelo.reveal_card(r, c)
        self.vista.update_board((r, c), self.modelo.get_card_images()[card_value])
        self.selected_cards.append((r, c))

        if len(self.selected_cards) == 2:
            self.evaluating = True  # Marcar como en evaluación.
            self.root.after(1000, self.handle_card_selection) #metemos un tiempo de espera despues de voltear las cartas

    def handle_card_selection(self):
        # Verifica si las dos cartas seleccionadas forman una pareja utilizando el metodo check_match del modelo. Si coinciden, mantiene la visualización de las cartas; si no, las oculta después de un breve retraso.
        # Actualiza el contador de movimientos en la interfaz y llama a check_game_complete para ver si el jugador ha completado el juego.
        pos1, pos2 = self.selected_cards
        back_image = self.modelo.get_back_image()

        if self.modelo.check_match(pos1, pos2):
            # Deshabilitar botones si hay coincidencia.
            for r, c in self.selected_cards:
                self.vista.buttons[r][c].config(state="disabled")
            if self.modelo.is_game_complete():
                self.end_game()
        else:
            # Restablecer cartas si no coinciden.
            self.vista.reset_cards(pos1, pos2, back_image)

        self.selected_cards.clear()  # Vaciar la lista después de evaluar.
        self.evaluating = False  # Liberar para permitir nuevos clics.
        self.update_move_count()

    def update_move_count(self):
        # Actualiza el contador de movimientos en GameView para reflejar el número actual de movimientos realizados.
        if self.vista is not None:
            self.vista.update_move_count(self.modelo.moves)

    def update_time(self):
        # Actualiza el temporizador en la vista del juego. Llama a sí misma cada segundo mientras el juego esté activo,
        # manteniendo el tiempo en la interfaz sincronizado con el tiempo transcurrido.
        if self.vista is not None:
            elapsed_time = self.modelo.get_time()
            self.vista.update_time(elapsed_time)
            self.root.after(1000, self.update_time)

    def end_game(self):
        # Maneja la finalización del juego, mostrando un mensaje de victoria y regresando al menú principal.
        self.modelo.save_score()  # Guardar la puntuación del jugador.
        messagebox.showinfo("¡Enhorabuena!",
            f"¡Ganaste en {self.modelo.moves} movimientos!\nTiempo: {self.modelo.get_time()} segundos.", )
        self.return_to_main_menu()

    def show_stats(self):
        # Obtiene las estadísticas de puntuaciones desde el modelo y las muestra en el menú principal.
        stats = self.modelo.load_scores() if self.modelo else {}
        formatted_stats = {str(key): value for key, value in stats.items()}  # Convertir claves a cadenas.
        self.main_menu.show_stats(formatted_stats)

    def return_to_main_menu(self):
        # Cierra la vista de juego actual y vuelve al menú principal, permitiendo que el jugador inicie una nueva partida o salga del juego.
        if self.vista:
            self.vista.destroy()
            self.vista = None
        self.main_menu.setup_main_menu()

    def quit_game(self):
        # Cierra la aplicación.
        self.root.quit()


