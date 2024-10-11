from Monstruo import Monstruo # Importamos las clases que se instancian en la mazmorra, que son Monstruo y Tesoro
                                # El heroe se instancia en el main, igual que la mazmorra
from Tesoro import Tesoro
import random # importamos la clase random para poder usar el metodo choice para elegir mosntruos aleatorios

# La clase Mazmorra gestiona el flujo del juego. Controla el progreso del héroe a través de la mazmorra,
# Maneja los encuentros con monstruos y el descubrimiento de tesoros.
# La mazmorra termina cuando el héroe derrota a todos los monstruos o muere.

class Mazmorra:
    # Constructor de la clase Mazmorra:
    def __init__(self, heroe):
        # Atributos de la clase
        self.heroe = heroe
        # Creamos a los monstruos inicializándolos con todos sus atributos
        self.monstruos = [Monstruo("Rata", 3, 12, 40), Monstruo("Jabalí", 15, 5, 60), Monstruo("Serpiente", 6, 6, 20)]
        self.tesoro = Tesoro() # Instanciamos la clase tesoro


    # MÉTODOS DE LA CLASE MAZMORRA:

    # jugar controla el flujo principal del juego. En cada turno, el héroe se enfrenta a un monstruo y debe elegir
    # entre atacar, defenderse o curarse. Después de derrotar a un monstruo, el héroe encuentra un tesoro. El juego termina
    def jugar(self):
        print("Héroe entra en la mazmorra.") #Aviso al jugador al entrar en la mazmorra

        while len(self.monstruos) > 0 and self.heroe.esta_vivo(): #Bucle que se repite mientras hay un monstruo y héroe está vivo
            monstruo = random.choice(self.monstruos)  # random.choice elige de forma aleatoria uno de los monstruos
            print(f"Te has encontrado con un {monstruo.nombre}.") #Aviso al jugador de con qué monstruo se enfrenta
            self.enfrentar_enemigo(monstruo) #Llamamos al metodo enfrentar_enemigo de la mazmorra, que gestiona el combate

            #if self.heroe.esta_vivo and len(self.mostruos)<0:
                # self.monstruos.remove(monstruo)

        if self.heroe.esta_vivo(): # Si el heroe está vivo despues de haber derrotado a todos los monstruos (esto
                                    # se hara en el metodo enfrentar_enemigo, saldrá un mensaje por pantalla
                                    # indicando que el heroe ha ganafo la partida
            print(f"¡{self.heroe.nombre_heroe} ha derrotado a todos los monstruos y ha conquistado la mazmorra!")
        else:
            print("Héroe ha sido derrotado en la mazmorra.") # Si el héroe no gana la partida (no derrota a todos los
                                                            # monstruos, también saldrá mensaje por pantalla


    def enfrentar_enemigo(self, monstruo): # Gestiona el combate entre el héroe y un monstruo.

        while monstruo.esta_vivo() and self.heroe.esta_vivo(): #mientras monstruo y héroe están vivos, se repite el bucle
            print("¿Qué deseas hacer?") # El jugador elige entre atacar, defenderse o curarse, mientras que el
                                        # monstruo ataca automáticamente en cada turno.
            print("1. Atacar")          # La elección es numerica. Se hará por teclado.
            print("2. Defender")
            print("3. Curarse")
            opcion = input("Elige una opción: ")

            if opcion == "1": # el jugador decide atacar
                self.heroe.atacar(monstruo) # invocamos el metodo atacar del heroe
                if monstruo.esta_vivo():
                    monstruo.atacar(self.heroe) # el monstruo siempre ataca mientras está vivo
            elif opcion == "2": # el jugador decide defenderse
                self.heroe.defenderse()  # invocamos el metodo defenderse del heroe
                monstruo.atacar(self.heroe) # el monstruo siempre ataca mientras está vivo
                self.heroe.reset_defensa()
            elif opcion == "3": # el jugador decide curarse
                self.heroe.curarse()  # invocamos el metodo curarse del heroe
                if monstruo.esta_vivo(): # el monstruo siempre ataca mientras está vivo
                    monstruo.atacar(self.heroe) # llamamos al metodo atacar del monstruo
            else:
                print("Opción no válida.") # Si el usuario teclea algo diferente a lo contemplado en el if-elif

        if not monstruo.esta_vivo(): # Cuando el mosntruo muere (es decir, que su metodo está vivo es false), es que
                                    # ha sido derrotado por el héroe
            print(f"¡{self.heroe.nombre_heroe} ha derrotado a {monstruo.nombre}!") # Aviso por pantalla al jugador
            self.monstruos.remove(monstruo) # se elimina el monstruo que ya ha participado para que no salga de nuevo
            self.buscar_tesoro() # se llama al metodo buscar_tesoro

    def buscar_tesoro(self): # metodo buscar tesoro
        print("Buscando tesoro...")
        self.tesoro.encontrar_tesoro(self.heroe)

