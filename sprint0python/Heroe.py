# La clase Heroe representa al héroe controlado por el jugador

class Heroe:

    # Constructor de la clase Heroe. Debe recibir el nombre por parámetros a la hora de construirlo (instanciarlo)
    def __init__(self, nombre_heroe):
        # Atributos de la clase Heroe
        # Todos se inicializan con un valor en el constructor de la clase, excepto nombre que es introducido por teclado por el jugador
        self.nombre_heroe = nombre_heroe
        self.ataque = 15
        self.defensa = 8
        self.salud = 100
        self.salud_maxima = 100
        # self.defensa_temporal = 0


    # MÉTODOS DE LA CLASE HÉROE:

    # Atacar recibe por parámetros el enemigo al que se enfrenta
    def atacar(self, enemigo):
        print(f"Héroe ataca a {enemigo.nombre}.") # Información para el jugador
        danho = self.ataque - enemigo.defensa # El daño será el resultado del ataque del héroe menos la defensa del enemigo

        # Si el enemigo sufre algún daño, se restará dicho daño de su salud
        # Se muestra por pantalla el daño recibido por el enemigo, no la salud restante del mismo
        if danho > 0:
            enemigo.salud -= danho
            print(f"El enemigo {enemigo.nombre} ha recibido {danho} puntos de daño.")
        else:
            print("El enemigo ha bloqueado el ataque.")

    def curarse(self):
        salud_recuperada = 25  # Cantidad fija de salud recuperada
        self.salud += salud_recuperada # A la salud se le suma la cantidad de salud recuperada
        if self.salud > self.salud_maxima: # La salud nunca debe superar a la salud máxima
            self.salud = self.salud_maxima
        print(f"Héroe se ha curado. Salud actual: {self.salud}")

    def defenderse(self): # La defensa del héroe aumenta temporalmente (en el ámbito de este método) en 5 puntos
        # self.defensa_temporal = 5
        # self.defensa += self.defensa_temporal
        self.defensa += 5
        print(f"Héroe se defiende. Defensa aumentada temporalmente a {self.defensa}.")

    def reset_defensa(self): # La defensa del héroe vuelve al valor que tenía antes de haber usado los 5 pts extra del método "defenderse"
        # self.defensa -= self.defensa_temporal
        # self.defensa_temporal = 0
        self.defensa -= 5
        print(f"La defensa de {self.nombre_heroe} vuelve a la normalidad.")

    def esta_vivo(self): # Devuelve un booleano: true si la salud es >0 (héroe vivo) y false si salud<0 (héroe muerto)
        return self.salud > 0


