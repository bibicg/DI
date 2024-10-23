# Debéis implementar un juego de piedra, papel, tijera en TKINTER.
# Habrá un menú que me permita seleccionar la partida de un jugador (contra la máquina), dos jugadores o salir del programa.
# En cada partida se jugará hasta que uno de los dos participantes logre 3 puntos.
# En cada jugada se recibirá información de lo que ha sacado el jugador 1, lo que ha sacado el jugador dos y el resultado. Por ejemplo:
# "El jugador uno saca piedra , el jugador dos saca papel. Gana el jugador dos, papel gana a piedra"
#
# Los empates no cuentan.
#
# Tú decides la interfaz, qué widgets usar, uso de messagebox o no...
# Encapsula en funciones todo el codigo susceptible de agruparse, para facilitar la legibilidad
# Puedes investigar el uso de TopLevel() por si quieres usar ventanas distintas a la principal.
# Sólo puedes usar código que hemos visto en clase.


import tkinter as tk
from tkinter import messagebox
import random


class PiedraPapelTijera:

    def __init__(self, root): # Constructor de la clase, en el que:
        self.root = root # definimos la ventana
        self.root.title("Piedra, Papel o Tijera") # su nombre
        self.root.geometry("400x300") # el tamaño de la ventana
        self.jugador1_puntaje = 0 # los jugadores parten de 0 puntos (podrán llegar a los 3)
        self.jugador2_puntaje = 0
        self.menu_inicial() # se inicia el metodo al abrir la app
        self.centrar_ventana() # se inicia el metodo que centra la ventana en la pantalla

    def menu_inicial(self):
        self.resetear_ventana() # es necesario para que, si volvemos a jugar otra partida, no se siga viendo el menú inicial
                                # se repetiría tantas veces como se inicializase la partida

        self.etiqueta_seleccion_modo = tk.Label(self.root, text="Vamos a jugar!!!:\n¿qué quieres hacer?")
        self.etiqueta_seleccion_modo.pack(pady=30)
        # 3 opciones en el menú inicial, cada una con un botón vinculado a un método:
        self.boton_1jugador = tk.Button(self.root, text="JUGAR CON 1 JUGADOR", command=self.jugar_un_jugador)
        self.boton_1jugador.pack(pady=10)
        self.boton_2jugadores = tk.Button(self.root, text="JUGAR CON 2 JUGADORES", command=self.jugar_dos_jugadores)
        self.boton_2jugadores.pack(pady=5)
        self.boton_salir = tk.Button(self.root, text="SALIR", command=self.root.quit)
        self.boton_salir.pack(pady=10)


    def jugar_dos_jugadores(self):
        pass

    def jugar_un_jugador(self): # si el usuario ha clickado el botón de 1 jugador
        self.resetear_ventana() # resetea si empezamos de nuevo, igual que en el menu_inicial

        self.etiqueta_pregunta_jugada = tk.Label(self.root, text="¿Cuál es tu jugada?:\n piedra, papel o tijera \n (escíbelo tal cual)")
        self.etiqueta_pregunta_jugada.pack(pady=30)

        self.jugador1_entry = tk.Entry(self.root) # El jugador escribe su jugada para la mano en curso
        self.jugador1_entry.pack(pady=5)

        self.boton_jugada = tk.Button(self.root, text="OK", command=self.eleccion) # se registra la jugada al pulsar
                                                                                    # llamando al metodo eleccion
        self.boton_jugada.pack()

        self.resultado_label = tk.Label(self.root, text="")  # etiqueta en la que se mostrará info de la mano
        self.resultado_label.pack(pady=10)

    def eleccion(self):
        jugador1_elige = self.jugador1_entry.get().lower() # lee el entry donde el jugador escribió su elección

        jugador2_elige = random.choice(["piedra", "papel", "tijera"]) # la maquina elige de forma aleatoria

        self.mostrar_resultados(jugador1_elige, jugador2_elige) # se llama al metodo que muestra los resultados

    # recibe las elecciones de ambos jugadores, llama al metodo comparar_jugadas, que evalua, haciendo una comparativa
    # de las jugadas, quien ha ganado
    def mostrar_resultados(self, jugador1_elige, jugador2_elige):
        resultado = self.comparar_jugadas(jugador1_elige, jugador2_elige)

        if resultado == 1: # en el metodo obtener_resultado se establece que si gana la mano el jugador 1, devuelve un 1
            self.jugador1_puntaje += 1 # por tanto,jugador 1 suma 1 punto a su puntaje total (parte de 0)
            mensaje_etiqueta = (f"Jugador 1 saca: {jugador1_elige}, Jugador 2 saca: {jugador2_elige}.\n "
                       f"{jugador1_elige} gana a {jugador2_elige}.\n JUGADOR 1 GANA ESTA MANO.")
        elif resultado == -1: # en el metodo obtener_resultado se establece que si pierde la mano el jugador 1, devuelve un -1
            self.jugador2_puntaje += 1 # por tanto,jugador 2 suma 1 punto a su puntaje total (parte de 0)
            mensaje_etiqueta = (f"Jugador 1 saca: {jugador1_elige}, Jugador 2 saca: {jugador2_elige}.\n "
                       f"{jugador2_elige} gana a {jugador1_elige}.\n JUGADOR 2 GANA ESTA MANO.")
        else:
            # si no se dan ninguna de las situaciones anteriores, quiere decir que ha habido un empate
            mensaje_etiqueta = f"Jugador 1 saca: {jugador1_elige}, Jugador 2 saca: {jugador2_elige}. \n HAY EMPATE."

        self.resultado_label.config(text=mensaje_etiqueta) # la label que se creó en metodo jugar_1jugador para dar info

        if self.jugador1_puntaje == 3: # si el jugador 1 obtiene 3 puntos, saltará un messageBox (para ello se importó) con su victoria
            messagebox.showinfo("Fin del juego", "El jugador 1 gana con 3 puntos!")
            self.menu_inicial() # y volvemos a ejecutar el menu_incial para seguir jugando
        elif self.jugador2_puntaje == 3: # si el jugador 2 obtiene 3 puntos, saltará un messageBox on su victoria
            messagebox.showinfo("Fin del juego", "El jugador 2 gana con 3 puntos!")
            self.menu_inicial() # y volvemos a ejecutar el menu_incial para seguir jugando

    def comparar_jugadas(self, jugador1, jugador2): # compara las posibles jugadas de ambos jugadores para dar un ganador
        if jugador1 == jugador2:
            return 0  # Empate
        elif (jugador1 == "piedra" and jugador2 == "tijera") or (jugador1 == "papel" and jugador2 == "piedra") or (jugador1 == "tijera" and jugador2 == "papel"):
            return 1  # El jugador 1 ha ganado
        else:
            return -1  # EL jugador 2 ha ganado

    def resetear_ventana(self): # metodo que permite que las ventanas se limpien para que no se vayan acumulando widgets
        for widget in self.root.winfo_children():
            widget.destroy()


    # CENTRAR VENTANA. lO INVOCAMOS DESDE EL CONSTRUCTOR PARA QUE YA SE ABRA EN EL CENTRO
    def centrar_ventana(self):
        self.root.update_idletasks()  # Asegurarse de que la ventana está actualizada
        ancho_ventana = self.root.winfo_width()
        alto_ventana = self.root.winfo_height()
        ancho_pantalla = self.root.winfo_screenwidth()
        alto_pantalla = self.root.winfo_screenheight()

        # Calcular las posiciones X e Y para centrar la ventana
        posicion_x = (ancho_pantalla // 2) - (ancho_ventana // 2)
        posicion_y = (alto_pantalla // 2) - (alto_ventana // 2)

        self.root.geometry(f"{ancho_ventana}x{alto_ventana}+{posicion_x}+{posicion_y}")


if __name__ == "__main__":
    root = tk.Tk()
    app = PiedraPapelTijera(root)
    root.mainloop()
