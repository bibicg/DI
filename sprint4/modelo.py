import threading
    # permite la creación y gestión de hilos, que se utiliza en este archivo para cargar las imágenes en
    # segundo plano, optimizando la carga inicial del juego.

import time
    # proporciona acceso a funciones relacionadas con el tiempo, como time() para capturar el tiempo en segundos.
    # Aquí, se usa para calcular la duración de la partida.

import random
    # se utiliza para mezclar las posiciones de las imágenes en el tablero y así variar la disposición de las
    # cartas en cada partida.

from datetime import datetime
    # importa funciones de fecha y hora. En este contexto, se usa para guardar la fecha en que
    # el jugador ha completado una partida en el archivo de puntuaciones.

#from recursos import descargar_imagen
    # función externa que permite descargar y redimensionar las imágenes desde una URL.
    # Las imágenes representan las cartas del juego y una versión oculta que se utiliza en el tablero hasta que se descubren.
    # NO LA HE UTILIZADO PORQUE NO HE SIDO CAPAZ DE QUE ME RESPONDIERA LA DESCARGA DESDE UNA URL

import os
from tkinter import PhotoImage, messagebox
import tkinter as tk


class GameModel:
    # administra la lógica central del juego, como la generación del tablero, la carga de imágenes, y el cálculo
    # del tiempo y de los movimientos realizados.

    def __init__(self, size, player_name, cell_size=100):
        # Incialización:
        self.size = size
        self.player_name = player_name
        self.cell_size = cell_size
        self.board = []  # El tablero de juego (tendrá una lista de cartas) se basa en la dificultad elegida
        self.back_image = None  # Imagen para el reverso de las cartas.
        self.card_images = []  # Lista para almacenar las imágenes cargadas.
        self.start_time = None  # Tiempo inicial del temporizador.
        self.moves = 0 # Movimientos realizados
        self.pairs_found = 0 # Parejas encontradas
        self.scores_file = "ranking.txt"  # Archivo para guardar puntuaciones

        self._generate_board()  # Llama a _generate_board() para crear la estructura del tablero
        self._load_images()  # Llama a _load_images() para cargar las imágenes en segundo plano

    def _generate_board(self):
        # Genera el tablero con pares de identificadores mezclados aleatoriamente
        num_pairs = (self.size * self.size) // 2
        card_ids = list(range(1, num_pairs + 1)) * 2
        random.shuffle(card_ids) # metodo para mezclar aleatoriamente
        self.board = card_ids

    def _load_images(self):
        # NO HE SIDO CAPAZ DE DESCARGAR LAS IMAGENES DESDE UNA URL, SIEMPRE ME DABA PROBLEMAS PIL Y REQUEST.
        # AUNQUE ESTÁN EN LOCAL, HE USADO ESTE METODO PARA SIMULAR LA DESCARGA
        # inicia un hilo separado para descargar y cargar las imágenes necesarias desde una URL base. La imagen oculta
        # se asigna a hidden_image y cada identificador de carta se asigna a una imagen descargada específica.
        # Se utiliza un hilo para evitar demoras en la interfaz del usuario durante la carga.
        try:
            # Cargar la imagen del reverso
            self.back_image = tk.PhotoImage(file="img/reverso.png")

            # Cargar las imágenes de los anversos, llamadas igual, variando solo el numero despues del guion
            self.card_images = []
            for i in range(0, (self.size * self.size) // 2):
                img = tk.PhotoImage(file=f"img/carta_{i}.png") # unica manera en que no me dan error las imagenes
                self.card_images.append(img)
        except Exception as e:
            raise FileNotFoundError(f"Error al cargar imágenes: {e}")

    def start_timer(self):
        # reinicia el tiempo de inicio del juego para el temporizador, permitiendo el registro del tiempo transcurrido.
        self.start_time = time.time()

    def get_time(self):
        # calcula y devuelve el tiempo en segundos desde que se inició el temporizador.
        return int(time.time() - self.start_time) if self.start_time else 0

    def check_match(self, pos1, pos2):
        # incrementa el contador de movimientos y verifica si dos posiciones del tablero contienen la misma imagen,
        # indicando una coincidencia. Si se encuentran las imágenes coincidentes, se incrementa el contador pairs_found.
        self.moves += 1
        idx1 = pos1[0] * self.size + pos1[1]
        idx2 = pos2[0] * self.size + pos2[1]
        if self.board[idx1] == self.board[idx2]:
            self.pairs_found += 1
            return True
        return False

    def is_game_complete(self):
        # verifica si se han encontrado todas las parejas del tablero. Si el número de parejas encontradas equivale a
        # la mitad del tamaño total del tablero, el juego se considera completado.
        return self.pairs_found == (self.size * self.size) // 2

    def reveal_card(self, r, c):
        # Devuelve el valor de la carta en una posición específica de nuestro tablero
        index = r * self.size + c
        return self.board[index] - 1  # Ajustar a índice basado en 0 para las imágenes.

    def get_back_image(self):
        # devuelve la imagen usada para el reverso de todas las cartas, como si fuera la de la marca de la baraja
        return self.back_image

    def get_card_images(self):
        # devuelve la lista de las imagenes usadas en los anversos de las cartas
        return self.card_images
    
    def save_score(self):
        # guarda la puntuación del jugador en un archivo ranking.txt. Los datos incluyen el nombre del jugador,
        # la dificultad, el número de movimientos y la fecha. Solo se guardan las tres mejores puntuaciones para cada
        # nivel de dificultad, basándose en el menor número de movimientos.
        try:
            date = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
            score_data = f"{self.player_name}, {self.difficulty}, {self.moves}, {self.get_time()}, {date}\n"
            with open("ranking.txt", "a") as f:
                f.write(score_data)
        except Exception as e:
            print(f"Error al guardar el puntaje: {e}")


    def load_scores(self):
        # carga y devuelve las puntuaciones desde el archivo ranking.txt. Si el archivo no existe, el metodo devuelve un
        # diccionario vacío con listas para cada nivel de dificultad. Esta información es útil para mostrar el ranking de
        # los mejores jugadores en la interfaz.
        scores = {"4": [], "6": [], "8": []}  # Diccionario para 4x4, 6x6, 8x8
        try:
            with open("ranking.txt", "r") as f:
                for line in f:
                    name, difficulty, moves, time, date = line.strip().split(", ")
                    scores[difficulty].append({
                        "name": name,
                        "moves": int(moves),
                        "time": int(time),
                        "date": date
                    })
        except FileNotFoundError:
            pass  # devuelve un diccionario vacio si aun no hay contenido

        # Ordena cada lista por el número de movimientos realizados por cada jugador
        for difficulty in scores:
            scores[difficulty].sort(key=lambda x: x["moves"])

        return scores


