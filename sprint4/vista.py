import tkinter as tk
from tkinter import Toplevel, messagebox, simpledialog

class GameView(tk.Toplevel):
    # representa la ventana principal del juego, incluyendo el tablero, el contador de movimientos y el temporizador.

    def __init__(self, on_card_click_callback, update_move_count_callback, update_time_callback):
        # Inicializa los elementos necesarios para la vista del juego.
        # self.labels: almacena las etiquetas de las cartas, representadas por Label, asociando cada posición del
        # tablero a una etiqueta específica.
        # on_card_click_callback, update_move_count_callback, update_time_callback: funciones de callback que permiten
        # que la vista se comunique con el controlador cuando se realizan acciones como hacer clic en una carta,
        # actualizar el contador de movimientos o el temporizador.
        super().__init__()
        self.title("Juego de Memoria")
        self.geometry("600x600")
        self.on_card_click_callback = on_card_click_callback
        self.update_move_count_callback = update_move_count_callback
        self.update_time_callback = update_time_callback

        self.board_size = 0
        self.buttons = []  # Matriz de botones que representan las cartas
        self.move_label = None  # Etiqueta para mostrar los movimientos
        self.time_label = None  # Etiqueta para mostrar el tiempo

    def create_board(self, model):
        # Crea la ventana de juego como una instancia de Toplevel, estableciendo su título y usando el modelo (model)
        # para definir el tamaño y el contenido del tablero. 
        # La ventana topLevel ya se crea al pasarle como parametro a la clase
        self.board_size = model.size
        back_image = model.get_back_image()
        self.buttons = [[None for _ in range(self.board_size)] for _ in range(self.board_size)]

        # Genera un tablero de Button en mi caso (en lugar de label) en función del tamaño, cada una mostrando una imagen oculta.
        # Las etiquetas se organizan en una cuadrícula y están vinculadas a eventos de clic que llaman al
        # callback on_card_click_callback.
        for r in range(self.board_size):
            for c in range(self.board_size):
                btn = tk.Button(
                    self, image=back_image, width=100, height=100,
                    command=lambda r=r, c=c: self.on_card_click_callback(r, c)
                )
                btn.grid(row=r, column=c, padx=5, pady=5)
                self.buttons[r][c] = btn

        # Añade etiquetas para el contador de movimientos y el temporizador, posicionándolas debajo del tablero.
        self.move_label = tk.Label(self, text="Movimientos: 0", font=("Calibri", 14))
        self.move_label.grid(row=self.board_size, column=0, columnspan=self.board_size // 2)

        self.time_label = tk.Label(self, text="Tiempo: 0s", font=("Calibri", 14))
        self.time_label.grid(row=self.board_size, column=self.board_size // 2, columnspan=self.board_size // 2)

    def update_board(self, pos, image):
        # Actualiza la imagen de una carta en una posición específica (pos), configurando la imagen correspondiente
        # según el image_id proporcionado.
        r, c = pos
        self.buttons[r][c].config(image=image)

    def reset_cards(self, pos1, pos2, back_image):
        # Restaura las imágenes de dos cartas a su estado oculto, útil cuando el jugador no encuentra una coincidencia
        # entre dos cartas seleccionadas.
        r1, c1 = pos1
        r2, c2 = pos2
        self.buttons[r1][c1].config(image=back_image)
        self.buttons[r2][c2].config(image=back_image)

    def update_move_count(self, moves):
        # Actualiza el contador de movimientos en la interfaz, modificando el texto de la etiqueta que muestra los movimientos actuales.
        self.move_label.config(text=f"Movimientos: {moves}")

    def update_time(self, time):
        # Actualiza el temporizador en la interfaz para reflejar el tiempo transcurrido.
        self.time_label.config(text=f"Tiempo: {time}s")


class MainMenu:
    # define el menú inicial del juego, donde el jugador puede seleccionar opciones como jugar, ver estadísticas y salir.
    # Este menú está conectado al controlador (GameController) para que gestione estas opciones.
    def __init__(self, root, start_game_callback, show_stats_callback, quit_callback):
        # Inicializa la ventana principal del menú
        self.root = root
        self.start_game_callback = start_game_callback
        self.show_stats_callback = show_stats_callback
        self.quit_callback = quit_callback
        self.setup_main_menu()

    def setup_main_menu(self): # meto en esta función la creación de widgets y lo llamo desde el constructor (no es necesario realmente)
        # para qie la ventana comience limpia se llama al metodo clear_screen() que se define al final de la clase
        self.clear_screen()

        # y establece su título.
        label_titulo = tk.Label(self.root, text="JUEGO DE MEMORIA", font=("Arial Black", 24), fg="black")
        label_titulo.pack(pady=20)

        # Crea tres botones: Jugar, Estadísticas y Salir, cada uno enlazado a un callback específico para:
        # iniciar el juego, mostrar las estadísticas y cerrar la aplicación.

        btn_jugar = tk.Button(self.root, text="Jugar", font=("Calibri", 18), fg="white", bg="black", command=self.ask_player_name)
        btn_jugar.pack(pady=20)

        btn_stats = tk.Button(self.root, text="Ver estadísticas", font=("Calibri", 18), fg="white", bg="black", command=self.show_stats_callback)
        btn_stats.pack(pady=20)

        btn_exit = tk.Button(self.root, text="Salir", font=("Calibri", 18), fg="white", bg="black", command=self.quit_callback)
        btn_exit.pack(pady=20)

    def ask_player_name(self):
        # Abre un diálogo para solicitar el nombre del jugador. Esta función se utiliza al iniciar una partida para
        # personalizar la experiencia del usuario.
        player_name = tk.simpledialog.askstring("Nombre del Jugador", "Escribe tu nombre:")
        if player_name:
            self.show_difficulty_selection(player_name)

    def show_difficulty_selection(self, player_name):
        # Muestra la selección de dificultad y llama al callback para iniciar el juego.
        self.clear_screen()

        label_difficulty = tk.Label(self.root, text=f"¡Hola {player_name}! Selecciona un tablero, del más sencillo al más complejo:", font=("Calibri", 14))
        label_difficulty.pack(pady=10)

        difficulties = [("Fácil (4x4)", "easy"), ("Intermedio (6x6)", "medium"), ("Difícil (8x8)", "hard")]
        for text, difficulty in difficulties:
            tk.Button(self.root, text=text, font=("Calibri", 14), fg="white", bg="black", command=lambda d=difficulty: self.start_game_callback(player_name, d)).pack(pady=5)


    def show_stats(self, stats): # MOSTRAR ESTADISTICAS
        # Abre una ventana Toplevel para mostrar las estadísticas de los jugadores. Las puntuaciones se organizan por
        # nivel de dificultad, y cada entrada muestra el nombre y el número de movimientos realizados.
        # Cada nivel de dificultad tiene su propia sección con una lista de las mejores puntuaciones.
        stats_window = tk.Toplevel(self.root)
        stats_window.title("Estadísticas")
        stats_window.geometry("400x400")

        for difficulty, scores in stats.items():
            if scores:
                tk.Label(stats_window, text=f"Dificultad {difficulty}x{difficulty}:", font=("Calibri", 14, "bold")).pack(pady=5)
                for score in scores:
                    tk.Label(stats_window, text=f"{score['name']} - {score['moves']} movimientos - {score['date']}", font=("Calibri", 12)).pack(pady=2)

    def clear_screen(self):
        # es el metodo al que llamamos desde otras partes de la clase y que limpia los widgets de la ventana
        for widget in self.root.winfo_children():
            widget.destroy()

